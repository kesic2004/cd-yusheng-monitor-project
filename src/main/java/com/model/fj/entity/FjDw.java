package com.model.fj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

@TableName("fj_dw")
public class FjDw extends Model<FjDw> {
   private static final long serialVersionUID = 1L;
   @TableId(
      value = "id",
      type = IdType.AUTO
   )
   private Integer id;
   private String stationname;
   private String platformurl;
   private String token;
   private String jblstationcode;
   private String bz;

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getStationname() {
      return this.stationname;
   }

   public void setStationname(String stationname) {
      this.stationname = stationname;
   }

   public String getPlatformurl() {
      return this.platformurl;
   }

   public void setPlatformurl(String platformurl) {
      this.platformurl = platformurl;
   }

   public String getToken() {
      return this.token;
   }

   public void setToken(String token) {
      this.token = token;
   }

   public String getJblstationcode() {
      return this.jblstationcode;
   }

   public void setJblstationcode(String jblstationcode) {
      this.jblstationcode = jblstationcode;
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

   public String toString() {
      return "FjDw{id=" + this.id + ", stationname=" + this.stationname + ", platformurl=" + this.platformurl + ", token=" + this.token + ", jblstationcode=" + this.jblstationcode + ", bz=" + this.bz + "}";
   }
}
