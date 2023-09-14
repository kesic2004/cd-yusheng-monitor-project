package com.model.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.user.entity.User;
import com.model.user.entity.UserEx;

public interface UserService extends IService<User> {
   User findUser(String qrcode);

   User findUserEx(User user);

   UserEx findUserJoinCity(Integer addresscode, User user);

   UserEx findUserJoinStation(Integer addresscode, Integer province, Integer city, Integer station, User user);

   Integer findUserCountJoinStation(Integer addresscode, User user);

   User selectUser(int id);
}
