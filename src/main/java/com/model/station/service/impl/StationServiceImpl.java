package com.model.station.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.station.entity.Station;
import com.model.station.entity.StationEx;
import com.model.station.mapper.StationMapper;
import com.model.station.service.StationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationServiceImpl extends ServiceImpl<StationMapper, Station> implements StationService {
   @Autowired
   StationMapper stationMapper;

   public List<StationEx> findStationListByClientId(Integer clientid) {
      return this.stationMapper.findStationListByClientId(clientid);
   }
}
