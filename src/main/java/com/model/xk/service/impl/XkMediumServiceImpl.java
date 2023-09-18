package com.model.xk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.xk.entity.XkMedium;
import com.model.xk.mapper.XkMediumMapper;
import com.model.xk.service.XkMediumService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XkMediumServiceImpl extends ServiceImpl<XkMediumMapper, XkMedium> implements XkMediumService {
   @Autowired
   XkMediumMapper xkMediumMapper;

   public void deleteMedium() {
      this.xkMediumMapper.deleteMedium();
   }

   public Date getMaxInternalTime() {
      return this.xkMediumMapper.getMaxInternalTime();
   }
}