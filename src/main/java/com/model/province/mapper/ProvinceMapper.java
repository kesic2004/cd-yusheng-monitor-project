package com.model.province.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.model.province.entity.Province;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ProvinceMapper extends BaseMapper<Province> {
    @Select("select id from province where code = #{code} order by id desc limit 1")
    Integer getIdByCode(@Param("code") int provinceCode);
}
