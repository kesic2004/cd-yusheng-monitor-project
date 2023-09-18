package com.model.xk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.xk.entity.XkAssocstation;
import java.util.Date;

public interface XkAssocstationService extends IService<XkAssocstation> {
   void deleteAssocstation();

   void deleteAssocstationByDid(Integer did);

   Date getMaxInternalTime();
}
