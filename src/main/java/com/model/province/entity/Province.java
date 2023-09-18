package com.model.province.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

public class Province extends Model<Province> {
   private static final long serialVersionUID = 1L;
   @TableId(
      value = "id",
      type = IdType.AUTO
   )
   private Integer id;
   private Integer code;
   private String name;

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
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
      return "Province{id=" + this.id + ", code=" + this.code + ", name=" + this.name + "}";
   }
}
