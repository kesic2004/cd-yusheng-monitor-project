package com.model.xk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.xk.entity.XkLog;

public interface XkLogService extends IService<XkLog> {
   void newLog(String tag, String detail);
}
