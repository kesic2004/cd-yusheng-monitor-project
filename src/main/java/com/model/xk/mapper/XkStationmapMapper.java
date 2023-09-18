package com.model.xk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.model.xk.entity.XkStationmap;
import com.thirdinf.client7.UserManager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface XkStationmapMapper extends BaseMapper<XkStationmap> {
   @Select({"Select a.stationCode from xk_dw a, xk_stationmap b where a.id=b.did And b.stationCodejbl=#{jblCode} limit 1"})
   String findStationCodeByJblCode(@Param("jblCode") String jblCode);

   @Select({"Select a.userCode, a.userPass from xk_dw a, xk_stationmap b where a.id=b.did And b.stationCodejbl=#{jblCode} limit 1"})
   UserManager findUserCodeAndUserPassByJblCode(String jblCode);
}
