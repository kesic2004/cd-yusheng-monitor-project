package com.model.xk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.model.xk.entity.XkManufacturer;
import java.util.Date;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface XkManufacturerMapper extends BaseMapper<XkManufacturer> {
   @Update({"truncate table xk_manufacturer"})
   void deleteManufacturer();

   @Select({"select max(internalTime) from xk_manufacturer"})
   Date getMaxInternalTime();
}
