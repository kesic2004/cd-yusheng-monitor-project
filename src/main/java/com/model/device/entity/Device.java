package com.model.device.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

@TableName("device")
public class Device extends Model<Device> {
   private static final long serialVersionUID = 1L;
   @TableId(
      value = "id",
      type = IdType.AUTO
   )
   private Integer id;
   private Integer sid;
   private Integer clientid;
   private String name;
   private Integer addresscode;
   private String detail;
   private Integer state;
   private Integer machineno;
   private Integer qrtype;

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Integer getCid() {
      return this.sid;
   }

   public void setCid(Integer cid) {
      this.sid = cid;
   }

   public Integer getClientid() {
      return this.clientid;
   }

   public void setClientid(Integer clientid) {
      this.clientid = clientid;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Integer getAddresscode() {
      return this.addresscode;
   }

   public void setAddresscode(Integer addresscode) {
      this.addresscode = addresscode;
   }

   public String getDetail() {
      return this.detail;
   }

   public void setDetail(String detail) {
      this.detail = detail;
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

   public Integer getMachineno() {
      return this.machineno;
   }

   public void setMachineno(Integer machineno) {
      this.machineno = machineno;
   }

   public Integer getQrtype() {
      return this.qrtype;
   }

   public void setQrtype(Integer qrtype) {
      this.qrtype = qrtype;
   }

   public String toString() {
      return "Device{id=" + this.id + ", sid=" + this.sid + ", clientid=" + this.clientid + ", name='" + this.name + '\'' + ", addresscode=" + this.addresscode + ", detail='" + this.detail + '\'' + ", state=" + this.state + ", machineno=" + this.machineno + ", qrtype=" + this.qrtype + '}';
   }
}
