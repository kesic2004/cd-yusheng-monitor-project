package com.myserver;

import java.util.ArrayList;

public class DataPackageEx {
   private String stationCode;
   private int devNum;
   private String machineNo;
   ArrayList<Point> data = new ArrayList();

   public String getStationCode() {
      return this.stationCode;
   }

   public void setStationCode(String stationCode) {
      this.stationCode = stationCode;
   }

   public int getDevNum() {
      return this.devNum;
   }

   public void setDevNum(int devNum) {
      this.devNum = devNum;
   }

   public ArrayList<Point> getData() {
      return this.data;
   }

   public void setData(ArrayList<Point> data) {
      this.data = data;
   }

   public String getMachineNo() {
      return this.machineNo;
   }

   public void setMachineNo(String machineNo) {
      this.machineNo = machineNo;
   }

   public String toString() {
      return "DataPackageEx{stationCode='" + this.stationCode + '\'' + ", devNum=" + this.devNum + ", machineNo=" + this.machineNo + ", data=" + this.data + '}';
   }
}
