package com.model.xk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.xk.entity.XkAssocstation;
import com.model.xk.mapper.XkAssocstationMapper;
import com.model.xk.service.XkAssocstationService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XkAssocstationServiceImpl extends ServiceImpl<XkAssocstationMapper, XkAssocstation> implements XkAssocstationService {

   public void deleteAssocstation() {
      super.baseMapper.deleteAssocstation();
   }

   public void deleteAssocstationByDid(Integer did) {
      super.baseMapper.deleteAssocstationByDid(did);
   }

   public Date getMaxInternalTime() {
      return null;
   }
}
