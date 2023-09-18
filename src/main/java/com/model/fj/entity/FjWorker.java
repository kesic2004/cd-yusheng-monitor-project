package com.model.fj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

public class FjWorker extends Model<FjWorker> {
   private static final long serialVersionUID = 1L;
   @TableId(
      value = "${field.annotationColumnName}",
      type = IdType.AUTO
   )
   private Integer id;
   private Integer operatorid;
   private String operatorname;
   private String stationcode;

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Integer getOperatorid() {
      return this.operatorid;
   }

   public void setOperatorid(Integer operatorid) {
      this.operatorid = operatorid;
   }

   public String getOperatorname() {
      return this.operatorname;
   }

   public void setOperatorname(String operatorname) {
      this.operatorname = operatorname;
   }

   public String getStationcode() {
      return this.stationcode;
   }

   public void setStationcode(String stationcode) {
      this.stationcode = stationcode;
   }

   protected Serializable pkVal() {
      return this.id;
   }

   public String toString() {
      return "FjWorker{id=" + this.id + ", operatorid=" + this.operatorid + ", operatorname=" + this.operatorname + ", stationcode=" + this.stationcode + "}";
   }
}
