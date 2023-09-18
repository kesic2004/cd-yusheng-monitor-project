package com.model.xk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.model.xk.entity.XkCylindertype;
import java.util.Date;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface XkCylindertypeMapper extends BaseMapper<XkCylindertype> {
   @Update({"truncate table xk_cylindertype"})
   void deleteCylindertype();

   @Select({"select max(internalTime) from xk_cylindertype"})
   Date getMaxInternalTime();
}
