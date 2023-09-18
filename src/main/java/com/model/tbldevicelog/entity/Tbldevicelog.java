package com.model.tbldevicelog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;

public class Tbldevicelog extends Model<Tbldevicelog> {
   private static final long serialVersionUID = 1L;
   @TableId(
      value = "id",
      type = IdType.AUTO
   )
   private Integer id;
   private Integer addresscode;
   private Integer provincecode;
   private Integer citycode;
   private Integer stationcode;
   private String qrcode;
   private Date recordtime;
   private Integer state;
   private Integer machineno;

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Integer getAddresscode() {
      return this.addresscode;
   }

   public void setAddresscode(Integer addresscode) {
      this.addresscode = addresscode;
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
      return "Tbldevicelog{id=" + this.id + ", addresscode=" + this.addresscode + ", provinceid=" + this.provincecode + ", cityid=" + this.citycode + ", stationid=" + this.stationcode + ", qrcode=" + this.qrcode + ", state=" + this.state + ", recordtime=" + this.recordtime + "}";
   }

   public Integer getState() {
      return this.state;
   }

   public void setState(Integer state) {
      this.state = state;
   }

   public String getQrcode() {
      return this.qrcode;
   }

   public void setQrcode(String qrcode) {
      this.qrcode = qrcode;
   }

   public Integer getProvincecode() {
      return this.provincecode;
   }

   public void setProvincecode(Integer provincecode) {
      this.provincecode = provincecode;
   }

   public Integer getCitycode() {
      return this.citycode;
   }

   public void setCitycode(Integer citycode) {
      this.citycode = citycode;
   }

   public Integer getStationcode() {
      return this.stationcode;
   }

   public void setStationcode(Integer stationcode) {
      this.stationcode = stationcode;
   }

   public Integer getMachineno() {
      return this.machineno;
   }

   public void setMachineno(Integer machineno) {
      this.machineno = machineno;
   }
}
