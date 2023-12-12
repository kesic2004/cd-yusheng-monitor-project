package com.model.xk.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

@TableName("xk_assocstation")
public class XkAssocstation extends Model<XkAssocstation> {
   private static final long serialVersionUID = 1L;
   @TableId(
      value = "id",
      type = IdType.AUTO
   )
   private Integer id;
   @TableField("stationCode")
   private String stationCode;
   @TableField("barcodePrefix")
   private String barcodePrefix;
   @TableField("associateCode")
   private String associateCode;
   @TableField("associateName")
   private String associateName;
   @TableField("did")
   private Integer did;

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getStationCode() {
      return this.stationCode;
   }

   public void setStationCode(String stationCode) {
      this.stationCode = stationCode;
   }

   public String getBarcodePrefix() {
      return this.barcodePrefix;
   }

   public void setBarcodePrefix(String barcodePrefix) {
      this.barcodePrefix = barcodePrefix;
   }

   public String getAssociateCode() {
      return this.associateCode;
   }

   public void setAssociateCode(String associateCode) {
      this.associateCode = associateCode;
   }

   public String getAssociateName() {
      return this.associateName;
   }

   public void setAssociateName(String associateName) {
      this.associateName = associateName;
   }

   protected Serializable pkVal() {
      return this.id;
   }

   public Integer getDid() {
      return this.did;
   }

   public void setDid(Integer did) {
      this.did = did;
   }

   public String toString() {
      return "XkAssocstation{id=" + this.id + ", stationCode='" + this.stationCode + '\'' + ", barcodePrefix='" + this.barcodePrefix + '\'' + ", associateCode='" + this.associateCode + '\'' + ", associateName='" + this.associateName + '\'' + ", did=" + this.did + '}';
   }
}
