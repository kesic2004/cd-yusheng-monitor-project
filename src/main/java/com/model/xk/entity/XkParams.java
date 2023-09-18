package com.model.xk.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

public class XkParams extends Model<XkParams> {
   private static final long serialVersionUID = 1L;
   @TableId(
      value = "id",
      type = IdType.AUTO
   )
   private Integer id;
   private Integer code;
   private String name;
   private String value;
   private String bz;
   private Integer state;

   public Integer getState() {
      return this.state;
   }

   public void setState(Integer state) {
      this.state = state;
   }

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

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
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
      return "XkParams{id=" + this.id + ", code=" + this.code + ", name='" + this.name + '\'' + ", value='" + this.value + '\'' + ", bz='" + this.bz + '\'' + ", state=" + this.state + '}';
   }
}
