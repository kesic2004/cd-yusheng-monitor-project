package com.thirdinf.client4;

public class MyLoginResponse4 {
   private String state;
   private String message;
   private String stationCode;
   private String token;

   public String getStationCode() {
      return this.stationCode;
   }

   public void setStationCode(String stationCode) {
      this.stationCode = stationCode;
   }

   public String getToken() {
      return this.token;
   }

   public void setToken(String token) {
      this.token = token;
   }

   public String getState() {
      return this.state;
   }

   public void setState(String state) {
      this.state = state;
   }

   public String getMessage() {
      return this.message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public String toString() {
      return "MyLoginResponse16{state='" + this.state + '\'' + ", message='" + this.message + '\'' + ", stationCode='" + this.stationCode + '\'' + ", token='" + this.token + '\'' + '}';
   }
}
