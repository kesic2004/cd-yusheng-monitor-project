package com.model.xk.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

@TableName("xk_stationmap")
public class XkStationmap extends Model<XkStationmap> {
   private static final long serialVersionUID = 1L;
   @TableId(
      value = "id",
      type = IdType.AUTO
   )
   private Integer id;
   @TableId("stationCodejbl")
   private String stationCodejbl;
   @TableField("did")
   private Integer did;
   @TableField("bz")
   private String bz;

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getStationCodejbl() {
      return this.stationCodejbl;
   }

   public void setStationCodejbl(String stationCodejbl) {
      this.stationCodejbl = stationCodejbl;
   }

   public Integer getDid() {
      return this.did;
   }

   public void setDid(Integer did) {
      this.did = did;
   }

   public String getBz() {
      return this.bz;
   }

   public void setBz(String bz) {
      this.bz = bz;
   }

   public String toString() {
      return "XkStationmap{id=" + this.id + ", stationCodejbl='" + this.stationCodejbl + '\'' + ", did=" + this.did + ", bz='" + this.bz + '\'' + '}';
   }
}
