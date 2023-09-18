package com.model.fj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.fj.entity.FjWorker;

public interface FjWorkerService extends IService<FjWorker> {
   FjWorker findWorker(String stationcode, Integer operatorid);
}
