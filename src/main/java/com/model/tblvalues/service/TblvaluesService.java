package com.model.tblvalues.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.tblvalues.entity.Tblvalues;
import java.util.Date;

public interface TblvaluesService extends IService<Tblvalues> {
   Tblvalues findValues(String addresscode);

   void updateValues(String addresscode, String temperature, String pressure, Date recordtime);
}
