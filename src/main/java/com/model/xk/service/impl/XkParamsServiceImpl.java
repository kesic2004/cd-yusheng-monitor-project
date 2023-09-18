package com.model.xk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.xk.entity.XkParams;
import com.model.xk.mapper.XkParamsMapper;
import com.model.xk.service.XkParamsService;
import org.springframework.stereotype.Service;

@Service
public class XkParamsServiceImpl extends ServiceImpl<XkParamsMapper, XkParams> implements XkParamsService {
}
