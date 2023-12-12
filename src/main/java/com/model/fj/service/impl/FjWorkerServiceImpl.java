package com.model.fj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.fj.entity.FjWorker;
import com.model.fj.mapper.FjWorkerMapper;
import com.model.fj.service.FjWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FjWorkerServiceImpl extends ServiceImpl<FjWorkerMapper, FjWorker> implements FjWorkerService {

   public FjWorker findWorker(String stationcode, Integer operatorid) {
      return super.baseMapper.findWorker(stationcode, operatorid);
   }
}
