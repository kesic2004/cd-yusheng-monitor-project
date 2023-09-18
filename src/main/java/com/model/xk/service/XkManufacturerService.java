package com.model.xk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.xk.entity.XkManufacturer;
import java.util.Date;

public interface XkManufacturerService extends IService<XkManufacturer> {
   void deleteManufacturer();

   Date getMaxInternalTime();
}
