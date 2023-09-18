package com.model.tblvalues.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.model.tblvalues.entity.Tblvalues;
import java.util.Date;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TblvaluesMapper extends BaseMapper<Tblvalues> {
   @Select({"SELECT t.id as id, t.addresscode as addresscode, t.temperature as temperature, t.pressure as pressure, t.detail as detail, t.recordtime as  recordtime FROM tblvalues t  WHERE  t.addresscode = #{addresscode} LIMIT 1 "})
   Tblvalues findValues(@Param("addresscode") String addresscode);

   @Update({"UPDATE tblvalues t  set t.temperature = #{temperature}, t.pressure = #{pressure},  t.recordtime = #{recordtime} WHERE  t.addresscode = #{addresscode} "})
   void updateValues(String addresscode, String temperature, String pressure, Date recordtime);
}
