package com.model.fj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.fj.entity.FjLog;

public interface FjLogService extends IService<FjLog> {
   void newLog(String tag, String detail);
}
