package com.model.fj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.fj.entity.FjDw;
import com.model.fj.mapper.FjDwMapper;
import com.model.fj.service.FjDwService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FjDwServiceImpl extends ServiceImpl<FjDwMapper, FjDw> implements FjDwService {

   public FjDw findDwByJblCode(String jblCode) {
      /*
       * Select id, stationname, platformurl, token, jblstationcode, bz from fj_dw where jblstationcode=#{jblCode} limit 1
       */
      return super.baseMapper.findDwByJblCode(jblCode);
   }
}
