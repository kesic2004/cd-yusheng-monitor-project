package com.model.city.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.city.entity.City;
import com.model.city.mapper.CityMapper;
import com.model.city.service.CityService;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CityService {
}
