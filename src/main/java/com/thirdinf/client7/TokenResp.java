package com.thirdinf.client7;

import com.alibaba.fastjson.annotation.JSONField;

public class TokenResp {
   private String token;
   private String customer;
   private String expiryDate;
   private String loginTime;
   private int keepAlive;
   @JSONField(
      name = "SelfGuid"
   )
   private String selfGuid;
   @JSONField(
      name = "SelfType"
   )
   private int selfType;
   @JSONField(
      name = "BelongTo"
   )
   private String belongTo;
   @JSONField(
      name = "BelongToType"
   )
   private int belongToType;
   @JSONField(
      name = "BelongToType"
   )
   private String belongStationCode;

   public String toString() {
      return "TokenResp{token='" + this.token + '\'' + ", customer='" + this.customer + '\'' + ", expiryDate='" + this.expiryDate + '\'' + ", loginTime='" + this.loginTime + '\'' + ", keepAlive=" + this.keepAlive + ", selfGuid='" + this.selfGuid + '\'' + ", selfType=" + this.selfType + ", belongTo='" + this.belongTo + '\'' + ", belongToType=" + this.belongToType + ", belongStationCode='" + this.belongStationCode + '\'' + '}';
   }

   public String getSelfGuid() {
      return this.selfGuid;
   }

   public void setSelfGuid(String selfGuid) {
      this.selfGuid = selfGuid;
   }

   public int getSelfType() {
      return this.selfType;
   }

   public void setSelfType(int selfType) {
      this.selfType = selfType;
   }

   public String getBelongTo() {
      return this.belongTo;
   }

   public void setBelongTo(String belongTo) {
      this.belongTo = belongTo;
   }

   public int getBelongToType() {
      return this.belongToType;
   }

   public void setBelongToType(int belongToType) {
      this.belongToType = belongToType;
   }

   public String getBelongStationCode() {
      return this.belongStationCode;
   }

   public void setBelongStationCode(String belongStationCode) {
      this.belongStationCode = belongStationCode;
   }

   public String getToken() {
      return this.token;
   }

   public void setToken(String token) {
      this.token = token;
   }

   public String getCustomer() {
      return this.customer;
   }

   public void setCustomer(String customer) {
      this.customer = customer;
   }

   public String getExpiryDate() {
      return this.expiryDate;
   }

   public void setExpiryDate(String expiryDate) {
      this.expiryDate = expiryDate;
   }

   public String getLoginTime() {
      return this.loginTime;
   }

   public void setLoginTime(String loginTime) {
      this.loginTime = loginTime;
   }

   public int getKeepAlive() {
      return this.keepAlive;
   }

   public void setKeepAlive(int keepAlive) {
      this.keepAlive = keepAlive;
   }
}
