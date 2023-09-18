package com.model.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.user.entity.User;
import com.model.user.entity.UserEx;
import com.model.user.mapper.UserMapper;
import com.model.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
   @Autowired
   UserMapper userMapper;

   public User findUser(String qrcode) {
      return this.userMapper.findUser(qrcode);
   }

   public User findUserEx(User user) {
      return this.userMapper.findUserEx(user);
   }

   public UserEx findUserJoinCity(Integer addresscode, User user) {
      return this.userMapper.findUserJoinCity(addresscode, user);
   }

   public UserEx findUserJoinStation(Integer addresscode, Integer province, Integer city, Integer station, User user) {
      return this.userMapper.findUserJoinStation(addresscode, province, city, station, user);
   }

   public Integer findUserCountJoinStation(Integer addresscode, User user) {
      return this.userMapper.findUserCountJoinStation(addresscode, user);
   }

   public User selectUser(int id) {
      return this.userMapper.selectUser(id);
   }
}
