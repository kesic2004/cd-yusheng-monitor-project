package com.model.city.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

@TableName("city")
public class City extends Model<City> {
   private static final long serialVersionUID = 1L;
   @TableId(
      value = "id",
      type = IdType.AUTO
   )
   private Integer id;
   private Integer pid;
   private Integer code;
   private String name;

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Integer getPid() {
      return this.pid;
   }

   public void setPid(Integer pid) {
      this.pid = pid;
   }

   public Integer getCode() {
      return this.code;
   }

   public void setCode(Integer code) {
      this.code = code;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   protected Serializable pkVal() {
      return this.id;
   }

   public String toString() {
      return "City{id=" + this.id + ", pid=" + this.pid + ", code=" + this.code + ", name=" + this.name + "}";
   }
}
