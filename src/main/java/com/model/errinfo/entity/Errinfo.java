package com.model.errinfo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

@TableName("errinfo")
public class Errinfo extends Model<Errinfo> {
   private static final long serialVersionUID = 1L;
   private Integer id;
   private String name;

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

   protected Serializable pkVal() {
      return null;
   }

   public String toString() {
      return "Errinfo{id=" + this.id + ", name=" + this.name + "}";
   }
}
