package com.model.xk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.xk.entity.XkMedium;
import java.util.Date;

public interface XkMediumService extends IService<XkMedium> {
   void deleteMedium();

   Date getMaxInternalTime();
}
