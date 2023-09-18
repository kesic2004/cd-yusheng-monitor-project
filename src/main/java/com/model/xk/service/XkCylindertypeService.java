package com.model.xk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.xk.entity.XkCylindertype;
import java.util.Date;

public interface XkCylindertypeService extends IService<XkCylindertype> {
   void deleteCylindertype();

   Date getMaxInternalTime();
}
