package com.model.tbldata.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;

public class Tbldata extends Model<Tbldata> {
   private static final long serialVersionUID = 1L;
   @TableId(
      value = "id",
      type = IdType.AUTO
   )
   private Integer id;
   private Integer provincecode;
   private Integer citycode;
   private Integer stationcode;
   private Integer addresscode;
   private Integer command;
   private Integer nextnumber;
   private Integer cardnum1;
   private Integer cardnum2;
   private Integer cardnum3;
   private Integer cardnum4;
   private Integer stamp;
   private Integer endweight;
   private Integer grossweight;
   private Integer settingweight;
   private Integer clientid;
   private Integer operatorid;
   private Integer gxcode;
   private Integer fscode;
   private Integer frameid;
   private Date curtime;
   private Integer usedtime;
   private Integer ticket;
   private Date recordtime;
   private Integer machineno;
   private String qrcode;
   @TableField(
      exist = false
   )
   private String token;
   @TableField(
      exist = false
   )
   private byte gxcode2;

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Integer getProvincecode() {
      return this.provincecode;
   }

   public void setProvincecode(Integer provincecode) {
      this.provincecode = provincecode;
   }

   public Integer getCitycode() {
      return this.citycode;
   }

   public void setCitycode(Integer citycode) {
      this.citycode = citycode;
   }

   public Integer getStationcode() {
      return this.stationcode;
   }

   public void setStationcode(Integer stationcode) {
      this.stationcode = stationcode;
   }

   public Integer getAddresscode() {
      return this.addresscode;
   }

   public void setAddresscode(Integer addresscode) {
      this.addresscode = addresscode;
   }

   public Integer getCommand() {
      return this.command;
   }

   public void setCommand(Integer command) {
      this.command = command;
   }

   public Integer getNextnumber() {
      return this.nextnumber;
   }

   public void setNextnumber(Integer nextnumber) {
      this.nextnumber = nextnumber;
   }

   public Integer getCardnum1() {
      return this.cardnum1;
   }

   public void setCardnum1(Integer cardnum1) {
      this.cardnum1 = cardnum1;
   }

   public Integer getCardnum2() {
      return this.cardnum2;
   }

   public void setCardnum2(Integer cardnum2) {
      this.cardnum2 = cardnum2;
   }

   public Integer getCardnum3() {
      return this.cardnum3;
   }

   public void setCardnum3(Integer cardnum3) {
      this.cardnum3 = cardnum3;
   }

   public Integer getCardnum4() {
      return this.cardnum4;
   }

   public void setCardnum4(Integer cardnum4) {
      this.cardnum4 = cardnum4;
   }

   public Integer getStamp() {
      return this.stamp;
   }

   public void setStamp(Integer stamp) {
      this.stamp = stamp;
   }

   public Integer getEndweight() {
      return this.endweight;
   }

   public void setEndweight(Integer endweight) {
      this.endweight = endweight;
   }

   public Integer getGrossweight() {
      return this.grossweight;
   }

   public void setGrossweight(Integer grossweight) {
      this.grossweight = grossweight;
   }

   public Integer getSettingweight() {
      return this.settingweight;
   }

   public void setSettingweight(Integer settingweight) {
      this.settingweight = settingweight;
   }

   public Integer getClientid() {
      return this.clientid;
   }

   public void setClientid(Integer clientid) {
      this.clientid = clientid;
   }

   public Integer getOperatorid() {
      return this.operatorid;
   }

   public void setOperatorid(Integer operatorid) {
      this.operatorid = operatorid;
   }

   public Integer getGxcode() {
      return this.gxcode;
   }

   public void setGxcode(Integer gxcode) {
      this.gxcode = gxcode;
   }

   public Integer getFscode() {
      return this.fscode;
   }

   public void setFscode(Integer fscode) {
      this.fscode = fscode;
   }

   public Integer getFrameid() {
      return this.frameid;
   }

   public void setFrameid(Integer frameid) {
      this.frameid = frameid;
   }

   public Date getCurtime() {
      return this.curtime;
   }

   public void setCurtime(Date curtime) {
      this.curtime = curtime;
   }

   public Integer getUsedtime() {
      return this.usedtime;
   }

   public void setUsedtime(Integer usedtime) {
      this.usedtime = usedtime;
   }

   public Integer getTicket() {
      return this.ticket;
   }

   public void setTicket(Integer ticket) {
      this.ticket = ticket;
   }

   protected Serializable pkVal() {
      return this.id;
   }

   public String toString() {
      return "Tbldata{id=" + this.id + ", provinceid=" + this.provincecode + ", cityid=" + this.citycode + ", stationid=" + this.stationcode + ", addresscode=" + this.addresscode + ", command=" + this.command + ", nextnumber=" + this.nextnumber + ", cardnum1=" + this.cardnum1 + ", cardnum2=" + this.cardnum2 + ", cardnum3=" + this.cardnum3 + ", cardnum4=" + this.cardnum4 + ", stamp=" + this.stamp + ", endweight=" + this.endweight + ", grossweight=" + this.grossweight + ", settingweight=" + this.settingweight + ", clientid=" + this.clientid + ", operatorid=" + this.operatorid + ", gxcode=" + this.gxcode + ", gxcode2=" + this.gxcode2 + ", fscode=" + this.fscode + ", frameid=" + this.frameid + ", curtime=" + this.curtime + ", usedtime=" + this.usedtime + ", ticket=" + this.ticket + ", qrcode=" + this.qrcode + ", token=" + this.token + ", recordtime=" + this.recordtime + ", machineno =" + this.machineno + "}";
   }

   public Date getRecordtime() {
      return this.recordtime;
   }

   public void setRecordtime(Date recordtime) {
      this.recordtime = recordtime;
   }

   public String getQrcode() {
      return this.qrcode;
   }

   public void setQrcode(String qrcode) {
      this.qrcode = qrcode;
   }

   public byte getGxcode2() {
      return this.gxcode2;
   }

   public void setGxcode2(byte gxcode2) {
      this.gxcode2 = gxcode2;
   }

   public String getToken() {
      return this.token;
   }

   public void setToken(String token) {
      this.token = token;
   }

   public Integer getMachineno() {
      return this.machineno;
   }

   public void setMachineno(Integer machineno) {
      this.machineno = machineno;
   }
}
