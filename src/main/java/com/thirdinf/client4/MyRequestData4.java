package com.thirdinf.client4;

public class MyRequestData4 {
   private String token;
   private String stationCode;
   private int temperature;
   private int balance;
   private String barcode;
   private String begin_date;
   private String end_date;
   private String upload_date;
   private double fill_weight;
   private double start_weight;
   private double end_weight;
   private int opt_press;
   private String operate_code;
   private String timestamp;

   public String getTimestamp() {
      return this.timestamp;
   }

   public void setTimestamp(String timestamp) {
      this.timestamp = timestamp;
   }

   public int getTemperature() {
      return this.temperature;
   }

   public void setTemperature(int temperature) {
      this.temperature = temperature;
   }

   public int getOpt_press() {
      return this.opt_press;
   }

   public void setOpt_press(int opt_press) {
      this.opt_press = opt_press;
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

   public int getBalance() {
      return this.balance;
   }

   public void setBalance(int balance) {
      this.balance = balance;
   }

   public String getBegin_date() {
      return this.begin_date;
   }

   public void setBegin_date(String begin_date) {
      this.begin_date = begin_date;
   }

   public String getEnd_date() {
      return this.end_date;
   }

   public void setEnd_date(String end_date) {
      this.end_date = end_date;
   }

   public String getUpload_date() {
      return this.upload_date;
   }

   public void setUpload_date(String upload_date) {
      this.upload_date = upload_date;
   }

   public String getToken() {
      return this.token;
   }

   public void setToken(String token) {
      this.token = token;
   }

   public double getFill_weight() {
      return this.fill_weight;
   }

   public void setFill_weight(double fill_weight) {
      this.fill_weight = fill_weight;
   }

   public String getOperate_code() {
      return this.operate_code;
   }

   public void setOperate_code(String operate_code) {
      this.operate_code = operate_code;
   }

   public double getStart_weight() {
      return this.start_weight;
   }

   public void setStart_weight(double start_weight) {
      this.start_weight = start_weight;
   }

   public double getEnd_weight() {
      return this.end_weight;
   }

   public void setEnd_weight(double end_weight) {
      this.end_weight = end_weight;
   }

   public String toString() {
      return "MyRequestData16{token='" + this.token + '\'' + ", stationCode='" + this.stationCode + '\'' + ", temperature=" + this.temperature + ", balance=" + this.balance + ", barcode='" + this.barcode + '\'' + ", begin_date='" + this.begin_date + '\'' + ", end_date='" + this.end_date + '\'' + ", upload_date='" + this.upload_date + '\'' + ", fill_weight=" + this.fill_weight + ", start_weight=" + this.start_weight + ", end_weight=" + this.end_weight + ", opt_press=" + this.opt_press + ", operate_code='" + this.operate_code + '\'' + ", timestamp='" + this.timestamp + '\'' + '}';
   }
}
