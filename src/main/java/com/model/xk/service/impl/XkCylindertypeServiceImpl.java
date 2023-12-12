package com.model.xk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.xk.entity.XkCylindertype;
import com.model.xk.mapper.XkCylindertypeMapper;
import com.model.xk.service.XkCylindertypeService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XkCylindertypeServiceImpl extends ServiceImpl<XkCylindertypeMapper, XkCylindertype> implements XkCylindertypeService {

   public void deleteCylindertype() {
      super.baseMapper.deleteCylindertype();
   }

   public Date getMaxInternalTime() {
      return super.baseMapper.getMaxInternalTime();
   }
}
