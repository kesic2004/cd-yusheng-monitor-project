package com.model.province.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.province.entity.Province;
import com.model.province.mapper.ProvinceMapper;
import com.model.province.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, Province> implements ProvinceService {

    @Override
    public Integer getIdByCode(int provinceCode) {
        return super.baseMapper.getIdByCode(provinceCode);
    }
}
