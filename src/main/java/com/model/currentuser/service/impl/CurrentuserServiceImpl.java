package com.model.currentuser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.currentuser.entity.Currentuser;
import com.model.currentuser.mapper.CurrentuserMapper;
import com.model.currentuser.service.CurrentuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrentuserServiceImpl extends ServiceImpl<CurrentuserMapper, Currentuser> implements CurrentuserService {

   public Currentuser findCurrentuser(Integer province, Integer city, Integer station, Integer address, Integer machineno) {
      return super.baseMapper.findCurrentuser(province, city, station, address, machineno);
   }

   public void delCurrentuser(Integer province, Integer city, Integer station, Integer address, Integer machineno) {
      super.baseMapper.delCurrentuser(province, city, station, address, machineno);
   }
}
