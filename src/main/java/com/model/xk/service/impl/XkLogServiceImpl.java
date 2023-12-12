package com.model.xk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.xk.entity.XkLog;
import com.model.xk.mapper.XkLogMapper;
import com.model.xk.service.XkLogService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XkLogServiceImpl extends ServiceImpl<XkLogMapper, XkLog> implements XkLogService {

   public void newLog(String tag, String detail) {
      XkLog xkLog = new XkLog();
      xkLog.setTag(tag);
      xkLog.setDetail(detail);
      xkLog.setRq(new Date());
      super.baseMapper.insert(xkLog);
   }
}
