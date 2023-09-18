package com.model.currentuser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.currentuser.entity.Currentuser;

public interface CurrentuserService extends IService<Currentuser> {
   Currentuser findCurrentuser(Integer province, Integer city, Integer station, Integer address, Integer machineno);

   void delCurrentuser(Integer province, Integer city, Integer station, Integer address, Integer machineno);
}
