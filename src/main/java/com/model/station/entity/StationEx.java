package com.model.station.entity;

public class StationEx {
   private String pcode;
   private String ccode;
   private String scode;

   public String toString() {
      return "StationEx{pcode='" + this.pcode + '\'' + ", ccode='" + this.ccode + '\'' + ", scode='" + this.scode + '\'' + '}';
   }

   public String getPcode() {
      return this.pcode;
   }

   public void setPcode(String pcode) {
      this.pcode = pcode;
   }

   public String getCcode() {
      return this.ccode;
   }

   public void setCcode(String ccode) {
      this.ccode = ccode;
   }

   public String getScode() {
      return this.scode;
   }

   public void setScode(String scode) {
      this.scode = scode;
   }
}
