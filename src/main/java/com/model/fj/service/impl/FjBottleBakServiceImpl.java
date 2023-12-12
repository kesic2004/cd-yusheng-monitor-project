package com.model.fj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.fj.entity.FjBottleBak;
import com.model.fj.mapper.FjBottleBakMapper;
import com.model.fj.service.FjBottleBakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FjBottleBakServiceImpl extends ServiceImpl<FjBottleBakMapper, FjBottleBak> implements FjBottleBakService {

   public void deleteBottleBak() {
      super.baseMapper.deleteBottle();
   }

   public String getMaxUpdateDateByDid(Integer did) {
      return super.baseMapper.getMaxUpdateDateByDid(did);
   }
}
