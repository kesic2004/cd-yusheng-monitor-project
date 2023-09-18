package com.model.tbltoken.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.tbltoken.entity.Tbltoken;

public interface TbltokenService extends IService<Tbltoken> {
   Tbltoken findTokenByDevicecode(String devicecode);

   void delTokenByDevicecode(String devicecode);
}
