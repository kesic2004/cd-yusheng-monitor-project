package com.model.fj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.model.fj.entity.FjBottle;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface FjBottleMapper extends BaseMapper<FjBottle> {
   @Select({"SELECT id, barcode, printno, style, valve, productdate, discarddate, checkdate, nextcheckdate, updatedate, state, systemid, factoryid, factoryname, did FROM fj_bottle  WHERE  did = #{did} And barcode = #{barcode} LIMIT 1"})
   FjBottle findBottle(@Param("did") int did, @Param("barcode") String barcode);

   @Update({"truncate table fj_bottle"})
   void deleteBottle();

   @Select({"select max(updatedate) from fj_bottle Where did = #{did}"})
   String getMaxUpdateDateByDid(@Param("did") Integer did);
}
