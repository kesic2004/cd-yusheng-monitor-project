package com.model.station.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.station.entity.Station;
import com.model.station.mapper.StationMapper;
import com.model.station.service.StationService;
import org.springframework.stereotype.Service;

@Service
public class StationServiceImpl extends ServiceImpl<StationMapper, Station> implements StationService {
}
