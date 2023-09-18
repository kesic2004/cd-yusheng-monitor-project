package com.model.tbldata.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.model.tbldata.entity.Tbldata;
import org.apache.ibatis.annotations.Param;

public interface TbldataMapper extends BaseMapper<Tbldata> {
   int queryCountByDateTime(@Param("starttime") String starttime, @Param("endtime") String endtime);

   int insertData(Tbldata tbldata);
}
