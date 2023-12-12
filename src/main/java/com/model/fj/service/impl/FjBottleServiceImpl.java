package com.model.fj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.fj.entity.FjBottle;
import com.model.fj.mapper.FjBottleMapper;
import com.model.fj.service.FjBottleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FjBottleServiceImpl extends ServiceImpl<FjBottleMapper, FjBottle> implements FjBottleService {

   public FjBottle findBottle(int did, String barcode) {
      return super.baseMapper.findBottle(did, barcode);
   }

   public void deleteBottle() {
      super.baseMapper.deleteBottle();
   }

   public String getMaxUpdateDateByDid(Integer did) {
      return super.baseMapper.getMaxUpdateDateByDid(did);
   }
}
