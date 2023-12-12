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

   public User findUser(String qrcode) {
      return super.baseMapper.findUser(qrcode);
   }

   public User findUserEx(User user) {
      return super.baseMapper.findUserEx(user);
   }

   public UserEx findUserJoinCity(Integer addresscode, User user) {
      return super.baseMapper.findUserJoinCity(addresscode, user);
   }

   public UserEx findUserJoinStation(Integer addresscode, Integer province, Integer city, Integer station, User user) {
      return super.baseMapper.findUserJoinStation(addresscode, province, city, station, user);
   }

   public Integer findUserCountJoinStation(Integer addresscode, User user) {
      return super.baseMapper.findUserCountJoinStation(addresscode, user);
   }

   public User selectUser(int id) {
      return super.baseMapper.selectUser(id);
   }
}
