package com.model.special.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

@TableName("special")
public class Special extends Model<Special> {
   private static final long serialVersionUID = 1L;
   @TableId(
      value = "stationcode",
      type = IdType.AUTO
   )
   private String stationcode;
   private String barcode;

   public String getStationcode() {
      return this.stationcode;
   }

   public void setStationcode(String stationcode) {
      this.stationcode = stationcode;
   }

   public String getBarcode() {
      return this.barcode;
   }

   public void setBarcode(String barcode) {
      this.barcode = barcode;
   }

   protected Serializable pkVal() {
      return this.stationcode;
   }

   public String toString() {
      return "Special{stationcode=" + this.stationcode + ", barcode=" + this.barcode + "}";
   }
}
