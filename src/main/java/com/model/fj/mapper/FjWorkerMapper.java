package com.model.fj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.model.fj.entity.FjWorker;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface FjWorkerMapper extends BaseMapper<FjWorker> {
   @Select({"SELECT t.id as id, t.operatorid as operatorid, t.operatorname as operatorname, t.stationcode as stationcode FROM fj_worker t  WHERE  t.stationcode = #{stationcode} And t.operatorid = #{operatorid} LIMIT 1 "})
   FjWorker findWorker(@Param("stationcode") String stationcode, @Param("operatorid") Integer operatorid);
}
