 package com.unicom.eos.codebuysync.service;

import com.unicom.eos.codebuysync.entity.OrderDispatch;

/**
 * @author zhangjiashuai
 * @date 2019/06/18
 */
public interface OrderDispatchSyncService {
    
    /**
     * 补全数据并保存
     * @param orderDispatch
     */
    void completeAndSave(OrderDispatch orderDispatch);

    /**
     * 获取入库成功数量
     * @return
     */
    int getSuccessCount();
    
}
