package com.model.xk.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

public class XkManufacturer extends Model<XkManufacturer> {
   private static final long serialVersionUID = 1L;
   @TableId(
      value = "id",
      type = IdType.AUTO
   )
   private Integer id;
   @TableField("code")
   private String code;
   @TableField("name")
   private String name;
   @TableField("exName")
   private String exName;
   @TableField("fastKey")
   private String fastKey;
   @TableField("internalState")
   private Integer internalState;
   @TableField("internalTime")
   private String internalTime;

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getExName() {
      return this.exName;
   }

   public void setExName(String exName) {
      this.exName = exName;
   }

   public String getFastKey() {
      return this.fastKey;
   }

   public void setFastKey(String fastKey) {
      this.fastKey = fastKey;
   }

   public Integer getInternalState() {
      return this.internalState;
   }

   public void setInternalState(Integer internalState) {
      this.internalState = internalState;
   }

   public String getInternalTime() {
      return this.internalTime;
   }

   public void setInternalTime(String internalTime) {
      this.internalTime = internalTime;
   }

   protected Serializable pkVal() {
      return this.id;
   }

   public String toString() {
      return "XkManufacturer{id=" + this.id + ", code=" + this.code + ", name=" + this.name + ", exName=" + this.exName + ", fastKey=" + this.fastKey + ", internalState=" + this.internalState + ", internalTime=" + this.internalTime + "}";
   }
}
