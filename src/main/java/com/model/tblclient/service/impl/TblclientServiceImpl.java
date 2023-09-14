package com.model.tblclient.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.tblclient.entity.Tblclient;
import com.model.tblclient.mapper.TblclientMapper;
import com.model.tblclient.service.TblclientService;
import org.springframework.stereotype.Service;

@Service
public class TblclientServiceImpl extends ServiceImpl<TblclientMapper, Tblclient> implements TblclientService {
}
