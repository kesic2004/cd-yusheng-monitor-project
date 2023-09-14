package com.thirdinf.client1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MyRequestData1 {
   @JsonProperty("station_num")
   private String station_num;
   @JsonProperty("sq_code")
   private String sq_code;
   @JsonProperty("temp")
   private String temp;
   @JsonProperty("press")
   private String press;

   public String getStation_num() {
      return this.station_num;
   }

   public void setStation_num(String station_num) {
      this.station_num = station_num;
   }

   public String getSq_code() {
      return this.sq_code;
   }

   public void setSq_code(String sq_code) {
      this.sq_code = sq_code;
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
      return "MyRequestData1{station_num='" + this.station_num + '\'' + ", sq_code='" + this.sq_code + '\'' + ", temp='" + this.temp + '\'' + ", press='" + this.press + '\'' + '}';
   }
}
