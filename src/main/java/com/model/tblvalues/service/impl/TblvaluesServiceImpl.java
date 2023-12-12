package com.model.tblvalues.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.tblvalues.entity.Tblvalues;
import com.model.tblvalues.mapper.TblvaluesMapper;
import com.model.tblvalues.service.TblvaluesService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@DS("slave") /* 使用slave数据源 */
public class TblvaluesServiceImpl extends ServiceImpl<TblvaluesMapper, Tblvalues> implements TblvaluesService {

   public Tblvalues findValues(String addresscode) {
      return super.baseMapper.findValues(addresscode);
   }

   public void updateValues(String addresscode, String temperature, String pressure, Date recordtime) {
      super.baseMapper.updateValues(addresscode, temperature, pressure, recordtime);
   }
}
