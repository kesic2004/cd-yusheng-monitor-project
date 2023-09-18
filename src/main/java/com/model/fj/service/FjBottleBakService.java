package com.model.fj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.fj.entity.FjBottleBak;

public interface FjBottleBakService extends IService<FjBottleBak> {
   void deleteBottleBak();

   String getMaxUpdateDateByDid(Integer did);
}
