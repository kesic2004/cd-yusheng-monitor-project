package com.model.tbltoken.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.tbltoken.entity.Tbltoken;
import com.model.tbltoken.mapper.TbltokenMapper;
import com.model.tbltoken.service.TbltokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbltokenServiceImpl extends ServiceImpl<TbltokenMapper, Tbltoken> implements TbltokenService {

   public Tbltoken findTokenByDevicecode(String devicecode) {
      return super.baseMapper.findTokenByDevicecode(devicecode);
   }

   public void delTokenByDevicecode(String devicecode) {
      super.baseMapper.delCurrentuser(devicecode);
   }
}
