package com.model.currentuser.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;

public class Currentuser extends Model<Currentuser> {
   private static final long serialVersionUID = 1L;
   @TableId(
      value = "id",
      type = IdType.AUTO
   )
   private Integer id;
   private Integer province;
   private Integer city;
   private Integer station;
   private Integer address;
   private String qrcode;
   private Date recordtime;
   private Integer machineno;

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Integer getProvince() {
      return this.province;
   }

   public void setProvince(Integer province) {
      this.province = province;
   }

   public Integer getCity() {
      return this.city;
   }

   public void setCity(Integer city) {
      this.city = city;
   }

   public Integer getStation() {
      return this.station;
   }

   public void setStation(Integer station) {
      this.station = station;
   }

   public Integer getAddress() {
      return this.address;
   }

   public void setAddress(Integer address) {
      this.address = address;
   }

   public String getQrcode() {
      return this.qrcode;
   }

   public void setQrcode(String qrcode) {
      this.qrcode = qrcode;
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
      return "Currentuser{id=" + this.id + ", province=" + this.province + ", city=" + this.city + ", station=" + this.station + ", address=" + this.address + ", qrcode=" + this.qrcode + ", recordtime=" + this.recordtime + ", machineno=" + this.machineno + "}";
   }

   public Integer getMachineno() {
      return this.machineno;
   }

   public void setMachineno(Integer machineno) {
      this.machineno = machineno;
   }
}
