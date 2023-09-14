package com.model.errinfo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.errinfo.entity.Errinfo;
import com.model.errinfo.mapper.ErrinfoMapper;
import com.model.errinfo.service.ErrinfoService;
import org.springframework.stereotype.Service;

@Service
public class ErrinfoServiceImpl extends ServiceImpl<ErrinfoMapper, Errinfo> implements ErrinfoService {
}
