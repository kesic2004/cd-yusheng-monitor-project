package com.model.xk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.model.xk.entity.XkMedium;
import java.util.Date;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface XkMediumMapper extends BaseMapper<XkMedium> {
   @Update({"truncate table xk_medium"})
   void deleteMedium();

   @Select({"select max(internalTime) from xk_medium"})
   Date getMaxInternalTime();
}
