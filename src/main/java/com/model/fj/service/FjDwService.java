package com.model.fj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.fj.entity.FjDw;

public interface FjDwService extends IService<FjDw> {
   FjDw findDwByJblCode(String jblCode);
}
