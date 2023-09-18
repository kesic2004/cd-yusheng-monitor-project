package com.model.tbldata.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.tbldata.entity.Tbldata;
import com.model.tbldata.mapper.TbldataMapper;
import com.model.tbldata.service.TbldataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbldataServiceImpl extends ServiceImpl<TbldataMapper, Tbldata> implements TbldataService {
   @Autowired
   TbldataMapper tbldataMapper;

   public int queryCountByDateTime(String starttime, String endtime) {
      return this.tbldataMapper.queryCountByDateTime(starttime, endtime);
   }

   public int insertData(Tbldata tbldata) {
      return this.tbldataMapper.insertData(tbldata);
   }
}
