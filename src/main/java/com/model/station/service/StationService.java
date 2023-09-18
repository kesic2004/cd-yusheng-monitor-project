package com.model.station.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.station.entity.Station;
import com.model.station.entity.StationEx;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StationService extends IService<Station> {
   List<StationEx> findStationListByClientId(@Param("clientid") Integer clientid);
}
