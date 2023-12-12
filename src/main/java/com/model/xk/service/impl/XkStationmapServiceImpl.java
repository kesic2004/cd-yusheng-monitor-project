package com.model.xk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.xk.entity.XkStationmap;
import com.model.xk.mapper.XkStationmapMapper;
import com.model.xk.service.XkStationmapService;
import com.thirdinf.client7.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XkStationmapServiceImpl extends ServiceImpl<XkStationmapMapper, XkStationmap> implements XkStationmapService {

   public String findStationCodeByJblCode(String jblCode) {
      return super.baseMapper.findStationCodeByJblCode(jblCode);
   }

   public UserManager findUserCodeAndUserPassByJblCode(String jblCode) {
      return super.baseMapper.findUserCodeAndUserPassByJblCode(jblCode);
   }
}
