package com.model.xk.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

@TableName("xk_medium")
public class XkMedium extends Model<XkMedium> {
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
   @TableField("color")
   private String color;
   @TableField("inspectPeriod")
   private Integer inspectPeriod;
   @TableField("scrappedPeriod")
   private Integer scrappedPeriod;
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

   public String getColor() {
      return this.color;
   }

   public void setColor(String color) {
      this.color = color;
   }

   public Integer getInspectPeriod() {
      return this.inspectPeriod;
   }

   public void setInspectPeriod(Integer inspectPeriod) {
      this.inspectPeriod = inspectPeriod;
   }

   public Integer getScrappedPeriod() {
      return this.scrappedPeriod;
   }

   public void setScrappedPeriod(Integer scrappedPeriod) {
      this.scrappedPeriod = scrappedPeriod;
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
      return "XkMedium{id=" + this.id + ", code=" + this.code + ", name=" + this.name + ", color=" + this.color + ", inspectPeriod=" + this.inspectPeriod + ", scrappedPeriod=" + this.scrappedPeriod + ", internalState=" + this.internalState + ", internalTime=" + this.internalTime + "}";
   }
}
