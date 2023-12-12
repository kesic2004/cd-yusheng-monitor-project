package com.model.xk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.xk.entity.XkManufacturer;
import com.model.xk.mapper.XkManufacturerMapper;
import com.model.xk.service.XkManufacturerService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XkManufacturerServiceImpl extends ServiceImpl<XkManufacturerMapper, XkManufacturer> implements XkManufacturerService {

   public void deleteManufacturer() {
      super.baseMapper.deleteManufacturer();
   }

   public Date getMaxInternalTime() {
      return super.baseMapper.getMaxInternalTime();
   }
}
