package com.model.tbldata.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.tbldata.entity.Tbldata;

public interface TbldataService extends IService<Tbldata> {
   int queryCountByDateTime(String starttime, String endtime);

   int insertData(Tbldata tbldata);
}
