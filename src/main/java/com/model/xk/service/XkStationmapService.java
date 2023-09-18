package com.model.xk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.xk.entity.XkStationmap;
import com.thirdinf.client7.UserManager;

public interface XkStationmapService extends IService<XkStationmap> {
   String findStationCodeByJblCode(String jblCode);

   UserManager findUserCodeAndUserPassByJblCode(String jblCode);
}
