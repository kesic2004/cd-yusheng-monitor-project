package com.model.station.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

@TableName("station")
public class Station extends Model<Station> {
   private static final long serialVersionUID = 1L;
   @TableId(
      value = "id",
      type = IdType.AUTO
   )
   private Integer id;
   private Integer cid;
   @TableField("i_code")
   private Integer code;
   private Integer actualCityCode;
   private String name;
   private String address;
   private String detail;

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Integer getCid() {
      return this.cid;
   }

   public void setCid(Integer cid) {
      this.cid = cid;
   }

   public Integer getCode() {
      return this.code;
   }

   public void setCode(Integer code) {
      this.code = code;
   }

   public Integer getActualCityCode() {
      return actualCityCode;
   }

   public void setActualCityCode(Integer actualCityCode) {
      this.actualCityCode = actualCityCode;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAddress() {
      return this.address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getDetail() {
      return this.detail;
   }

   public void setDetail(String detail) {
      this.detail = detail;
   }

   protected Serializable pkVal() {
      return this.id;
   }

   public String toString() {
      return "Station{id=" + this.id + ", cid=" + this.cid + ", code=" + this.code + ", name=" + this.name + ", address=" + this.address + ", detail=" + this.detail + "}";
   }
}
