package com.model.fj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.model.fj.entity.FjDw;
import org.apache.ibatis.annotations.Select;

public interface FjDwMapper extends BaseMapper<FjDw> {
   @Select({"Select id, stationname, platformurl, token, jblstationcode, bz from fj_dw where jblstationcode=#{jblCode} limit 1"})
   FjDw findDwByJblCode(String jblCode);
}
