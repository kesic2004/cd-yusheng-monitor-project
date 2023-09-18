package com.model.xk.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

public class XkDw extends Model<XkDw> {
   private static final long serialVersionUID = 1L;
   @TableId(
      value = "id",
      type = IdType.AUTO
   )
   private Integer id;
   @TableField("stationCode")
   private String stationCode;
   @TableField("name")
   private String name;
   @TableField("userCode")
   private String userCode;
   @TableField("userPass")
   private String userPass;
   @TableField("bz")
   private String bz;
   @TableField("updateAtonce")
   private Integer updateAtonce;

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getStationCode() {
      return this.stationCode;
   }

   public void setStationCode(String stationCode) {
      this.stationCode = stationCode;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getBz() {
      return this.bz;
   }

   public void setBz(String bz) {
      this.bz = bz;
   }

   protected Serializable pkVal() {
      return this.id;
   }

   public String getUserCode() {
      return this.userCode;
   }

   public void setUserCode(String userCode) {
      this.userCode = userCode;
   }

   public String getUserPass() {
      return this.userPass;
   }

   public void setUserPass(String userPass) {
      this.userPass = userPass;
   }

   public Integer getUpdateAtonce() {
      return this.updateAtonce;
   }

   public void setUpdateAtonce(Integer updateAtonce) {
      this.updateAtonce = updateAtonce;
   }

   public String toString() {
      return "XkDw{id=" + this.id + ", stationCode='" + this.stationCode + '\'' + ", name='" + this.name + '\'' + ", userCode='" + this.userCode + '\'' + ", userPass='" + this.userPass + '\'' + ", bz='" + this.bz + '\'' + ", updateAtonce=" + this.updateAtonce + '}';
   }
}
