package com.model.city.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.model.city.entity.City;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CityMapper extends BaseMapper<City> {
    @Select("select id from city where pid = #{pid} and code = #{code} order by id desc limit 1")
    Integer getIdByCode(@Param("pid") int provinceId, @Param("code") int cityCode);
}
