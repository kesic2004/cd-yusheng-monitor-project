package com.model.license.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

@TableName("license")
public class License extends Model<License> {
   private static final long serialVersionUID = 1L;
   @TableId(
      value = "id",
      type = IdType.AUTO
   )
   private Integer id;
   private String counter;
   private String doomday;
   private Integer state;

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getCounter() {
      return this.counter;
   }

   public void setCounter(String counter) {
      this.counter = counter;
   }

   public String getDoomday() {
      return this.doomday;
   }

   public void setDoomday(String doomday) {
      this.doomday = doomday;
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
      return "License{id=" + this.id + ", counter=" + this.counter + ", doomday=" + this.doomday + ", state=" + this.state + "}";
   }
}
