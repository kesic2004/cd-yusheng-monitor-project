package com.thirdinf.client4;

public class MyCheckRequestData4 {
   private String stationCode;
   private String token;
   private String barcode;
   private String timestamp;

   public String getToken() {
      return this.token;
   }

   public void setToken(String token) {
      this.token = token;
   }

   public String getStationCode() {
      return this.stationCode;
   }

   public void setStationCode(String stationCode) {
      this.stationCode = stationCode;
   }

   public String getBarcode() {
      return this.barcode;
   }

   public void setBarcode(String barcode) {
      this.barcode = barcode;
   }

   public String getTimestamp() {
      return this.timestamp;
   }

   public void setTimestamp(String timestamp) {
      this.timestamp = timestamp;
   }

   public String toString() {
      return "MyCheckRequestData16{token='" + this.token + '\'' + ", stationCode='" + this.stationCode + '\'' + ", barcode='" + this.barcode + '\'' + ", timestamp='" + this.timestamp + '\'' + '}';
   }
}
