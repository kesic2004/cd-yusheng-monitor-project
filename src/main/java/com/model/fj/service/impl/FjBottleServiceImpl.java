package com.model.fj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.fj.entity.FjBottle;
import com.model.fj.mapper.FjBottleMapper;
import com.model.fj.service.FjBottleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FjBottleServiceImpl extends ServiceImpl<FjBottleMapper, FjBottle> implements FjBottleService {
   @Autowired
   FjBottleMapper fjBottleMapper;

   public FjBottle findBottle(int did, String barcode) {
      return this.fjBottleMapper.findBottle(did, barcode);
   }

   public void deleteBottle() {
      this.fjBottleMapper.deleteBottle();
   }

   public String getMaxUpdateDateByDid(Integer did) {
      return this.fjBottleMapper.getMaxUpdateDateByDid(did);
   }
}
