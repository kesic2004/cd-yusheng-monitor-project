package com.thirdinf.client2;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MyRequestData2 {
   @JsonProperty("machineNo")
   private String machineno;
   @JsonProperty("temperature")
   private String temp;
   @JsonProperty("pressure")
   private String press;

   public String getMachineno() {
      return this.machineno;
   }

   public void setMachineno(String machineno) {
      this.machineno = machineno;
   }

   public String getTemp() {
      return this.temp;
   }

   public void setTemp(String temp) {
      this.temp = temp;
   }

   public String getPress() {
      return this.press;
   }

   public void setPress(String press) {
      this.press = press;
   }

   public String toString() {
      return "MyRequestData12{machineno='" + this.machineno + '\'' + ", temp='" + this.temp + '\'' + ", press='" + this.press + '\'' + '}';
   }
}
