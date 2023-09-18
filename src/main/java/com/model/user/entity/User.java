package com.model.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;

public class User extends Model<User> {
   private static final long serialVersionUID = 1L;
   @TableId(
      value = "id",
      type = IdType.AUTO
   )
   private Integer id;
   private String username;
   private String password;
   private String realname;
   private Integer sid;
   private Integer sidflag;
   private String qrcode;
   private Date starttime;
   private Date endtime;
   private Integer state;

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getUsername() {
      return this.username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getRealname() {
      return this.realname;
   }

   public void setRealname(String realname) {
      this.realname = realname;
   }

   public Integer getSid() {
      return this.sid;
   }

   public void setSid(Integer sid) {
      this.sid = sid;
   }

   public Integer getSidflag() {
      return this.sidflag;
   }

   public void setSidflag(Integer sidflag) {
      this.sidflag = sidflag;
   }

   public Date getStarttime() {
      return this.starttime;
   }

   public void setStarttime(Date starttime) {
      this.starttime = starttime;
   }

   public Date getEndtime() {
      return this.endtime;
   }

   public void setEndtime(Date endtime) {
      this.endtime = endtime;
   }

   public Integer getState() {
      return this.state;
   }

   public void setState(Integer state) {
      this.state = state;
   }

   protected Serializable pkVal() {
      return this.id;
   }

   public String toString() {
      return "User{id=" + this.id + ", username=" + this.username + ", password=" + this.password + ", realname=" + this.realname + ", sid=" + this.sid + ", sidflag=" + this.sidflag + ", qrcode=" + this.qrcode + ", starttime=" + this.starttime + ", endtime=" + this.endtime + ", state=" + this.state + "}";
   }

   public String getQrcode() {
      return this.qrcode;
   }

   public void setQrcode(String qrcode) {
      this.qrcode = qrcode;
   }
}
