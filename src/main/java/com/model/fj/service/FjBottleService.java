package com.model.fj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.fj.entity.FjBottle;
import org.apache.ibatis.annotations.Param;

public interface FjBottleService extends IService<FjBottle> {
   FjBottle findBottle(@Param("did") int did, @Param("barcode") String barcode);

   void deleteBottle();

   String getMaxUpdateDateByDid(Integer did);
}
