package com.myserver;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.ArrayList;

public class DataPackage extends Model<DataPackage> {
   private static final long serialVersionUID = 1L;
   private Integer provincecode;
   private Integer citycode;
   private Integer stationcode;
   private Integer addresscode;
   private Integer poins;
   private Integer machineno;
   private ArrayList<Integer> l = new ArrayList();

   public Integer getProvincecode() {
      return this.provincecode;
   }

   public void setProvincecode(Integer provincecode) {
      this.provincecode = provincecode;
   }

   public Integer getCitycode() {
      return this.citycode;
   }

   public void setCitycode(Integer citycode) {
      this.citycode = citycode;
   }

   public Integer getStationcode() {
      return this.stationcode;
   }

   public void setStationcode(Integer stationcode) {
      this.stationcode = stationcode;
   }

   public Integer getAddresscode() {
      return this.addresscode;
   }

   public void setAddresscode(Integer addresscode) {
      this.addresscode = addresscode;
   }

   public Integer getPoins() {
      return this.poins;
   }

   public void setPoins(Integer poins) {
      this.poins = poins;
   }

   public Integer getMachineno() {
      return this.machineno;
   }

   public void setMachineno(Integer machineno) {
      this.machineno = machineno;
   }

   public ArrayList<Integer> getL() {
      return this.l;
   }

   public void setL(ArrayList<Integer> l) {
      this.l = l;
   }

   public String toString() {
      return "DataPackage{provincecode=" + this.provincecode + ", citycode=" + this.citycode + ", stationcode=" + this.stationcode + ", addresscode=" + this.addresscode + ", poins=" + this.poins + ", machineno=" + this.machineno + ", l=" + this.l + '}';
   }
}
