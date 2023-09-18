package com.thirdinf.client12;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Items {
   @JsonProperty("Barcode")
   private String barcode;
   @JsonProperty("NetWeight")
   private double netWeight;
   @JsonProperty("IdleWeight")
   private double idleWeight;
   @JsonProperty("GrossWeight")
   private double grossWeight;
   @JsonProperty("BeginDate")
   private String beginDate;
   @JsonProperty("EndDate")
   private String endDate;
   @JsonProperty("Machine")
   private int machine;
   @JsonProperty("Temperature")
   private double temperature;
   @JsonProperty("Pressure")
   private double pressure;
   @JsonProperty("CustomerNo")
   private String customerNo;
   @JsonProperty("ThirdId")
   private int thirdId;

   public String getBarcode() {
      return this.barcode;
   }

   public void setBarcode(String barcode) {
      this.barcode = barcode;
   }

   public double getNetWeight() {
      return this.netWeight;
   }

   public void setNetWeight(double netWeight) {
      this.netWeight = netWeight;
   }

   public double getIdleWeight() {
      return this.idleWeight;
   }

   public void setIdleWeight(double idleWeight) {
      this.idleWeight = idleWeight;
   }

   public double getGrossWeight() {
      return this.grossWeight;
   }

   public void setGrossWeight(double grossWeight) {
      this.grossWeight = grossWeight;
   }

   public String getBeginDate() {
      return this.beginDate;
   }

   public void setBeginDate(String beginDate) {
      this.beginDate = beginDate;
   }

   public String getEndDate() {
      return this.endDate;
   }

   public void setEndDate(String endDate) {
      this.endDate = endDate;
   }

   public int getMachine() {
      return this.machine;
   }

   public void setMachine(int machine) {
      this.machine = machine;
   }

   public double getTemperature() {
      return this.temperature;
   }

   public void setTemperature(double temperature) {
      this.temperature = temperature;
   }

   public String getCustomerNo() {
      return this.customerNo;
   }

   public void setCustomerNo(String customerNo) {
      this.customerNo = customerNo;
   }

   public int getThirdId() {
      return this.thirdId;
   }

   public void setThirdId(int thirdId) {
      this.thirdId = thirdId;
   }

   public String toString() {
      return "Items{barcode='" + this.barcode + '\'' + ", netWeight=" + this.netWeight + ", idleWeight=" + this.idleWeight + ", grossWeight=" + this.grossWeight + ", beginDate='" + this.beginDate + '\'' + ", endDate='" + this.endDate + '\'' + ", machine=" + this.machine + ", temperature=" + this.temperature + ", pressure=" + this.pressure + ", customerNo='" + this.customerNo + '\'' + ", thirdId=" + this.thirdId + '}';
   }

   public double getPressure() {
      return this.pressure;
   }

   public void setPressure(double pressure) {
      this.pressure = pressure;
   }
}
