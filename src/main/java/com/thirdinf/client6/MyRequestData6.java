package com.thirdinf.client6;

public class MyRequestData6 {
   private String qrcode;
   private String sq_code;
   private String chenNum;
   private String startTime;
   private String endTime;
   private String netWeight;
   private String codeWeight;
   private String fillAfterWeight;
   private String workcard;
   private String fillStatus;

   public String getSq_code() {
      return this.sq_code;
   }

   public void setSq_code(String sq_code) {
      this.sq_code = sq_code;
   }

   public String getQrcode() {
      return this.qrcode;
   }

   public void setQrcode(String qrcode) {
      this.qrcode = qrcode;
   }

   public String getChenNum() {
      return this.chenNum;
   }

   public void setChenNum(String chenNum) {
      this.chenNum = chenNum;
   }

   public String getStartTime() {
      return this.startTime;
   }

   public void setStartTime(String startTime) {
      this.startTime = startTime;
   }

   public String getEndTime() {
      return this.endTime;
   }

   public void setEndTime(String endTime) {
      this.endTime = endTime;
   }

   public String getNetWeight() {
      return this.netWeight;
   }

   public void setNetWeight(String netWeight) {
      this.netWeight = netWeight;
   }

   public String getCodeWeight() {
      return this.codeWeight;
   }

   public void setCodeWeight(String codeWeight) {
      this.codeWeight = codeWeight;
   }

   public String getFillAfterWeight() {
      return this.fillAfterWeight;
   }

   public void setFillAfterWeight(String fillAfterWeight) {
      this.fillAfterWeight = fillAfterWeight;
   }

   public String getWorkcard() {
      return this.workcard;
   }

   public void setWorkcard(String workcard) {
      this.workcard = workcard;
   }

   public String getFillStatus() {
      return this.fillStatus;
   }

   public void setFillStatus(String fillStatus) {
      this.fillStatus = fillStatus;
   }

   public String toString() {
      return "MyRequestData12{qrcode='" + this.qrcode + '\'' + ", sq_code='" + this.sq_code + '\'' + ", chenNum='" + this.chenNum + '\'' + ", startTime='" + this.startTime + '\'' + ", endTime='" + this.endTime + '\'' + ", netWeight='" + this.netWeight + '\'' + ", codeWeight='" + this.codeWeight + '\'' + ", fillAfterWeight='" + this.fillAfterWeight + '\'' + ", workcard='" + this.workcard + '\'' + ", fillStatus='" + this.fillStatus + '\'' + '}';
   }
}
