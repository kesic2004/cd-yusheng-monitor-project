package com.model.fj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.model.fj.entity.FjBottleBak;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface FjBottleBakMapper extends BaseMapper<FjBottleBak> {
   @Update({"truncate table fj_bottle_bak"})
   void deleteBottle();

   @Select({"select max(updatedate) from fj_bottle_bak Where did = #{did}"})
   String getMaxUpdateDateByDid(@Param("did") Integer did);
}
