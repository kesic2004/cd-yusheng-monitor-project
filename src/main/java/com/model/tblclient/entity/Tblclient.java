package com.model.tblclient.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

public class Tblclient extends Model<Tblclient> {
   private static final long serialVersionUID = 1L;
   @TableId(
      value = "id",
      type = IdType.AUTO
   )
   private Integer id;
   private String name;
   private String tel;
   private String address;
   private String unit;
   private String post;
   private String detail;
   private String checkurl;
   private String posturl;
   private Integer state;

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getTel() {
      return this.tel;
   }

   public void setTel(String tel) {
      this.tel = tel;
   }

   public String getAddress() {
      return this.address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getUnit() {
      return this.unit;
   }

   public void setUnit(String unit) {
      this.unit = unit;
   }

   public String getPost() {
      return this.post;
   }

   public void setPost(String post) {
      this.post = post;
   }

   public String getDetail() {
      return this.detail;
   }

   public void setDetail(String detail) {
      this.detail = detail;
   }

   public String getCheckurl() {
      return this.checkurl;
   }

   public void setCheckurl(String checkurl) {
      this.checkurl = checkurl;
   }

   public String getPosturl() {
      return this.posturl;
   }

   public void setPosturl(String posturl) {
      this.posturl = posturl;
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
      return "Tblclient{id=" + this.id + ", name=" + this.name + ", tel=" + this.tel + ", address=" + this.address + ", unit=" + this.unit + ", post=" + this.post + ", detail=" + this.detail + ", checkurl=" + this.checkurl + ", posturl=" + this.posturl + ", state=" + this.state + "}";
   }
}
