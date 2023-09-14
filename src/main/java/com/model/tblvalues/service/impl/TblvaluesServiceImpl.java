package com.model.tblvalues.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.tblvalues.entity.Tblvalues;
import com.model.tblvalues.mapper.TblvaluesMapper;
import com.model.tblvalues.service.TblvaluesService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TblvaluesServiceImpl extends ServiceImpl<TblvaluesMapper, Tblvalues> implements TblvaluesService {
   @Autowired
   TblvaluesMapper tblvaluesMapper;

   public Tblvalues findValues(String addresscode) {
      return this.tblvaluesMapper.findValues(addresscode);
   }

   public void updateValues(String addresscode, String temperature, String pressure, Date recordtime) {
      this.tblvaluesMapper.updateValues(addresscode, temperature, pressure, recordtime);
   }
}
