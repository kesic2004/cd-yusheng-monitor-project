package com.model.tbldevicelog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.tbldevicelog.entity.Tbldevicelog;
import com.model.tbldevicelog.mapper.TbldevicelogMapper;
import com.model.tbldevicelog.service.TbldevicelogService;
import org.springframework.stereotype.Service;

@Service
public class TbldevicelogServiceImpl extends ServiceImpl<TbldevicelogMapper, Tbldevicelog> implements TbldevicelogService {
}
