package com.unicom.eos.codebuysync.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.unicom.eos.codebuysync.mapper.AreaMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 省市县信息缓存
 * 
 * @author zhangjiashuai
 * @date 2019/05/13
 */
@Slf4j
@Component
public class AreaCache {
    
    @Autowired
    private AreaMapper areaMapper;


    private final LoadingCache<String, String> DISTRICT_NAME_CACHE = CacheBuilder.newBuilder()
        .expireAfterAccess(356, TimeUnit.DAYS).softValues().build(new CacheLoader<String, String>() {
            @Override
            public String load(String districtCode) {
                return areaMapper.findDistrictNameByDistrictCode(districtCode);
            }
        });
    
    private final LoadingCache<String, String> CITY_NAME_CACHE = CacheBuilder.newBuilder()
        .expireAfterAccess(356, TimeUnit.DAYS).softValues().build(new CacheLoader<String, String>() {
            @Override
            public String load(String cityCode) {
                return areaMapper.findCityNameByCityCode(cityCode);
            }
        });
    
    private final LoadingCache<String, String> PROVINCE_NAME_CACHE = CacheBuilder.newBuilder()
        .expireAfterAccess(356, TimeUnit.DAYS).softValues().build(new CacheLoader<String, String>() {
            @Override
            public String load(String provinceCode) {
                return areaMapper.findProvinceNameByCityCode(provinceCode);
            }
        });
    
    public Optional<String> findDistrictNameByDistrictCode(String districtCode) {
        if(districtCode == null){
            return Optional.empty();
        }
        try {
            return Optional.of(DISTRICT_NAME_CACHE.get(districtCode));
        } catch (Exception e) {
            log.error(String.format("区县名称转换失败，districtCode:[%s]", districtCode), e);
            return Optional.empty();
        }
    }
    
    public Optional<String> findCityNameByCityCode(String cityCode) {
        if(cityCode == null){
            return Optional.empty();
        }
        try {
            return Optional.of(CITY_NAME_CACHE.get(cityCode));
        } catch (Exception e) {
            log.error(String.format("地市名称转换失败，cityCode:[%s]", cityCode), e);
            return Optional.empty();
        }
    }
    
    public Optional<String> findProvinceNameByProvinceCode(String provinceCode) {
        if(provinceCode == null){
            return Optional.empty();
        }
        try {
            return Optional.of(PROVINCE_NAME_CACHE.get(provinceCode));
        } catch (Exception e) {
            log.error(String.format("省份名称转换失败，provinceCode:[%s]", provinceCode), e);
            return Optional.empty();
        }
    }


}
