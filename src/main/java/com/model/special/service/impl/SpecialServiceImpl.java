package com.model.special.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.special.entity.Special;
import com.model.special.mapper.SpecialMapper;
import com.model.special.service.SpecialService;
import org.springframework.stereotype.Service;

@Service
public class SpecialServiceImpl extends ServiceImpl<SpecialMapper, Special> implements SpecialService {
}
