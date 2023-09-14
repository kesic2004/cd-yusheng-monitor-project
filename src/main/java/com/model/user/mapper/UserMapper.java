package com.model.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.model.user.entity.User;
import com.model.user.entity.UserEx;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {
   @Select({"SELECT id, username, password, realname, sid, sidflag, qrcode,starttime, endtime, state FROM user  WHERE qrcode = #{qrcode} "})
   User findUser(@Param("String") String qrcode);

   @Select({"SELECT id, username, password, realname, sid, sidflag, qrcode,starttime, endtime, state FROM user  WHERE  qrcode = #{qrcode} "})
   User findUserEx(User user);

   @Select({"SELECT u.id as id, u.username as username, u.password as password, u.realname as realname, u.sid as sid, u.sidflag as sidflag, u.qrcode as qrcode,u.starttime as starttime, u.endtime as endtime, u.state as state, d.id as deviceid FROM user u, city c, station s, device d WHERE  d.addresscode = #{addresscode}  And d.sid = s.id  And s.cid = c.id  And c.id = u.sid And  u.qrcode = #{user.qrcode} "})
   UserEx findUserJoinCity(@Param("addresscode") Integer addresscode, @Param("user") User user);

   @Select({"SELECT u.id as id, u.username as username, u.password as password, u.realname as realname, u.sid as sid, u.sidflag as sidflag, u.qrcode as qrcode,u.starttime as starttime, u.endtime as endtime, u.state as state, d.id as deviceid FROM user u, province p, city c, station s, device d WHERE  p.id = c.pid And c.id = s.cid And s.id = d.sid And s.id = u.sid And p.code = #{province} And c.code = #{city} And s.code = #{station} And d.addresscode = #{addresscode} And  u.qrcode = #{user.qrcode}"})
   UserEx findUserJoinStation(@Param("addresscode") Integer addresscode, @Param("province") Integer province, @Param("city") Integer city, @Param("station") Integer station, @Param("user") User user);

   @Select({"SELECT count(*) FROM user u, station s, device d WHERE  d.addresscode = #{addresscode}  And d.sid = s.id  And s.id = u.sid And  u.qrcode = #{user.qrcode}"})
   Integer findUserCountJoinStation(@Param("addresscode") Integer addresscode, @Param("user") User user);

   User selectUser(int id);
}
