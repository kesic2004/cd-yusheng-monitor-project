package com.model.tbltoken.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;

@TableName("tbltoken")
public class Tbltoken extends Model<Tbltoken> {
   private static final long serialVersionUID = 1L;
   @TableId(
      value = "id",
      type = IdType.AUTO
   )
   private Integer id;
   private String devicecode;
   private String stationcode;
   private String token;
   private Date recordtime;

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getDevicecode() {
      return this.devicecode;
   }

   public void setDevicecode(String devicecode) {
      this.devicecode = devicecode;
   }

   public String getStationcode() {
      return this.stationcode;
   }

   public void setStationcode(String stationcode) {
      this.stationcode = stationcode;
   }

   public String getToken() {
      return this.token;
   }

   public void setToken(String token) {
      this.token = token;
   }

   public Date getRecordtime() {
      return this.recordtime;
   }

   public void setRecordtime(Date recordtime) {
      this.recordtime = recordtime;
   }

   protected Serializable pkVal() {
      return this.id;
   }

   public String toString() {
      return "Tbltoken{id=" + this.id + ", devicecode=" + this.devicecode + ", stationcode=" + this.stationcode + ", token=" + this.token + ", recordtime=" + this.recordtime + "}";
   }
}
