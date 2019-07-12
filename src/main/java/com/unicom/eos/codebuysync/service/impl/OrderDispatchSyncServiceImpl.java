package com.unicom.eos.codebuysync.service.impl;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.unicom.eos.codebuysync.config.ErrorFileConfig;
import com.unicom.eos.codebuysync.entity.Order;
import com.unicom.eos.codebuysync.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.unicom.eos.codebuysync.cache.AreaCache;
import com.unicom.eos.codebuysync.entity.OrderDispatch;
import com.unicom.eos.codebuysync.entity.OrderIndex;
import com.unicom.eos.codebuysync.mapper.OrderDispatchMapper;
import com.unicom.eos.codebuysync.service.OrderDispatchSyncService;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangjiashuai
 * @date 2019/06/18
 */
@Slf4j
@Service
public class OrderDispatchSyncServiceImpl implements OrderDispatchSyncService {

    @Autowired
    private OrderDispatchMapper orderDispatchMapper;
    @Autowired
    private AreaCache areaCache;
    @Value("${import.debugMode:false}")
    private boolean debugMode;

    private static final AtomicInteger SAVE_SUCCESS_COUNTER = new AtomicInteger();

    /**
     * 订单状态 待派单
     */
    private static final String ORDER_STATE_BP = "BP";
    /**
     * 订单状态 已派单
     */
    private static final String ORDER_STATE_CM = "CM";

    @Override
    public void completeAndSave(OrderDispatch orderDispatch) {
        try {
            // 设置 dn 和 logDn
            Long orderId = orderDispatch.getOrderId();
            if (orderId == null) {
                // 空行或错误数据
                return;
            }
            if (orderDispatch.getPoolTag() == null) {
                // 通过判断这一后位非空字段是否存在，确定是否有换行导致的数据不完成
                log.error("orderId:【】数据不完整，跳过");
                return;
            }
            OrderIndex orderIndex = null;
            if (debugMode) {
                orderIndex = new OrderIndex(1001, 10001);
            } else {
                orderIndex = orderDispatchMapper.findOrderIndexByOrderId(orderId, orderDispatch.getOrderProvinceCode());
                if (orderIndex == null) {
                    log.error("orderId：【{}】无法查询到关联的orderIndex", orderId);
                    if (ErrorFileConfig.enabled_) {
                        String path = ErrorFileConfig.noIndexFile_;
                        String line = new StringBuilder("order_id = ").append(orderId).append(", and province_code")
                                .append(orderDispatch.getOrderProvinceCode() == null ? " is null" : (" = " +orderDispatch.getOrderProvinceCode())).toString();
                        FileUtil.append(path, line);
                    }
                    return;
                }
            }
            orderDispatch.setDn(orderIndex.getDn());
            orderDispatch.setLogDn(orderIndex.getLogDn());
            // 设置 crossCityTag. 商城库：1：是 2：否; 4.0库：0：本地 1：跨地市 2：跨省。暂时对应为 1->1,2->0
            if (orderDispatch.getCrossCityTag() != null && orderDispatch.getCrossCityTag() == 2) {
                orderDispatch.setCrossCityTag(0);
            }
            // 设置省市县名称
            String provinceCode = orderDispatch.getProvinceCodePost();
            String cityCode = orderDispatch.getCityCodePost();
            String districtCode = orderDispatch.getDistrictCodePost();
            Optional<String> provinceName = areaCache.findProvinceNameByProvinceCode(provinceCode);
            orderDispatch.setProvinceName(provinceName.orElse(null));
            Optional<String> cityName = areaCache.findCityNameByCityCode(cityCode);
            orderDispatch.setCityName(cityName.orElse(null));
            Optional<String> districtName = areaCache.findDistrictNameByDistrictCode(districtCode);
            orderDispatch.setDistrictName(districtName.orElse(null));
            if(orderDispatch.getOrderProvinceCode() == null){
                orderDispatch.setOrderProvinceCode(orderIndex.getProvinceCode());
            }
            if(orderDispatch.getOrderCityCode() == null){
                orderDispatch.setOrderCityCode(orderIndex.getCityCode());
            }
            // 拼接地址
            String postAddrDetail = orderIndex.getPostAddr();
            orderDispatch.setPostAddrDetail(postAddrDetail);
            String postAddr = new StringBuilder(provinceName.orElse(EMPTY)).append(cityName.orElse(EMPTY)).append(districtName.orElse(EMPTY)).append(postAddrDetail).toString();
            orderDispatch.setPostAddr(postAddr);
            // 设置同步标识, 默认 未同步码上购
            orderDispatch.setSyncTag(0);
            // 派送状态: 1首次派单，2  再次派单,默认首次派送
            if (orderDispatch.getDispatchTag() == null) {
                orderDispatch.setDispatchTag(1);
            }
            // 意向单
            if(orderDispatch.getRsyncTag() != null && orderDispatch.getRsyncTag() == 2){
                dealIntentionOrder(orderDispatch);
            }
            // 持久化
            log.info("orderId：【{}】处理后数据准备入库", orderDispatch.getOrderId());
            orderDispatchMapper.insertSelective(orderDispatch);
            log.info("orderId：【{}】入库完成", orderDispatch.getOrderId());
            SAVE_SUCCESS_COUNTER.incrementAndGet();
        } catch (DuplicateKeyException e) {
            log.info("orderId：【{}】主键重复", orderDispatch.getOrderId());
            if (ErrorFileConfig.enabled_) {
                String path = ErrorFileConfig.duplicateKeyFile_;
                String line = orderDispatch.getOrderId().toString();
                FileUtil.append(path, line);
            }
        } catch (Exception e) {
            log.error(String.format("数据：【%d】保存失败", orderDispatch.toString()), e);
            if (ErrorFileConfig.enabled_) {
                String path = ErrorFileConfig.saveErrorFile_;
                String line = orderDispatch.getOrderId().toString();
                FileUtil.append(path, line);
            }
        }
    }

    /**
     * 处理意向单
     * @param orderDispatch
     */
    private void dealIntentionOrder(OrderDispatch orderDispatch){
        Integer dispatchState = orderDispatch.getDispatchState();
        if(dispatchState == null){
            log.error("orderId:【{}】 dispatchState is null", orderDispatch.getOrderId());
            return;
        }
        Order order = new Order();
        order.setDn(orderDispatch.getDn());
        order.setOrderId(orderDispatch.getOrderId());
        if(dispatchState == 0){
            order.setOrderState(ORDER_STATE_BP);
        } else if (dispatchState == 1){
            order.setOrderState(ORDER_STATE_CM);
        }
        orderDispatchMapper.updateOrderState(order);
    }

    @Override
    public int getSuccessCount(){
        return SAVE_SUCCESS_COUNTER.get();
    }
}
