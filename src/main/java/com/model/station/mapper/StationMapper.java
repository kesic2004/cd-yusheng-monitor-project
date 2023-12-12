package com.model.station.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.model.station.entity.Station;
import com.model.station.entity.StationEx;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface StationMapper extends BaseMapper<Station> {
   @Select({"SELECT p.code as pcode, c.code as ccode, s.code as scode FROM device d, province p, city c, station s  WHERE  p.id = c.pid And c.id = s.cid And s.id = d.sid And d.clientid = #{clientid} "})
   List<StationEx> findStationListByClientId(@Param("clientid") Integer clientid);

    @Select("select id from station where cid = #{cid} and i_code = #{code} order by id desc limit 1")
    Integer getIdByCode(@Param("cid") int cityId, @Param("code") int stationCode);
}
