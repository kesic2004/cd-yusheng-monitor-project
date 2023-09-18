package com.model.xk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.model.xk.entity.XkAssocstation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;

public interface XkAssocstationMapper extends BaseMapper<XkAssocstation> {
   @Update({"truncate table xk_assocstation"})
   void deleteAssocstation();

   @Delete({"Delete from xk_assocstation Where did = #{did}"})
   void deleteAssocstationByDid(Integer did);
}
