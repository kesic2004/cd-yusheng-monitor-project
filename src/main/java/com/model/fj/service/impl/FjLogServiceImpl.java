package com.model.fj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.fj.entity.FjLog;
import com.model.fj.mapper.FjLogMapper;
import com.model.fj.service.FjLogService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FjLogServiceImpl extends ServiceImpl<FjLogMapper, FjLog> implements FjLogService {

   public void newLog(String tag, String detail) {
      FjLog fjLog = new FjLog();
      fjLog.setTag(tag);
      fjLog.setDetail(detail);
      fjLog.setRq(new Date());
      super.baseMapper.insert(fjLog);
   }
}
