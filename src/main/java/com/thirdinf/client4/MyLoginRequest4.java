package com.thirdinf.client4;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MyLoginRequest4 {
   @JsonProperty("UserCode")
   private String userCode;
   @JsonProperty("UserPass")
   private String userPass;
   @JsonProperty("DeviceCode")
   private String deviceCode;

   public String getUserCode() {
      return this.userCode;
   }

   public void setUserCode(String userCode) {
      this.userCode = userCode;
   }

   public String getUserPass() {
      return this.userPass;
   }

   public void setUserPass(String userPass) {
      this.userPass = userPass;
   }

   public String getDeviceCode() {
      return this.deviceCode;
   }

   public void setDeviceCode(String deviceCode) {
      this.deviceCode = deviceCode;
   }

   public String toString() {
      return "MyLoginRequest16{userCode='" + this.userCode + '\'' + ", userPass='" + this.userPass + '\'' + ", deviceCode='" + this.deviceCode + '\'' + '}';
   }
}
