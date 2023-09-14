package com.model.tblvalues.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;

public class Tblvalues extends Model<Tblvalues> {
   private static final long serialVersionUID = 1L;
   @TableId(
      value = "id",
      type = IdType.AUTO
   )
   private Integer id;
   private String addresscode;
   private String temperature;
   private String pressure;
   private String detail;
   private Date recordtime;

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getAddresscode() {
      return this.addresscode;
   }

   public void setAddresscode(String addresscode) {
      this.addresscode = addresscode;
   }

   public String getTemperature() {
      return this.temperature;
   }

   public void setTemperature(String temperature) {
      this.temperature = temperature;
   }

   public String getPressure() {
      return this.pressure;
   }

   public void setPressure(String pressure) {
      this.pressure = pressure;
   }

   public String getDetail() {
      return this.detail;
   }

   public void setDetail(String detail) {
      this.detail = detail;
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
      return "Tblvalues{id=" + this.id + ", addresscode=" + this.addresscode + ", temperature=" + this.temperature + ", pressure=" + this.pressure + ", detail=" + this.detail + ", recordtime=" + this.recordtime + "}";
   }
}
