package com.unicom.eos.codebuysync.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AreaMapper {
    
    @Select("SELECT F.DISTRICT_NAME districtName FROM tf_m_web_district F WHERE F.DISTRICT_CODE = #{districtCode}")
    String findDistrictNameByDistrictCode(@Param("districtCode") String districtCode);
    
    @Select("SELECT T.CITY_NAME cityName FROM tf_m_web_city T WHERE  T.CITY_CODE = #{cityCode}")
    String findCityNameByCityCode(@Param("cityCode") String cityCode);
    
    @Select("select province_name from tf_m_web_province where province_code = #{provinceCode}")
    String findProvinceNameByCityCode(String provinceCode);
    
}
