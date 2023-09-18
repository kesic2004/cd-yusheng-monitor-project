package com.thirdinf.client11;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MyRequestData11 {
   @JsonProperty("KSSJ")
   private String start_time;
   @JsonProperty("JSSJ")
   private String end_time;
   @JsonProperty("BQBH")
   private String qrcode;
   @JsonProperty("ZDBH")
   private String station_num;
   @JsonProperty("CZG")
   private String peop_num;
   @JsonProperty("CZDW")
   private String dw = "";
   @JsonProperty("QH")
   private String number;
   @JsonProperty("SDJZ")
   private Float sz;
   @JsonProperty("SJJZ")
   private Float jz;
   @JsonProperty("PIZ")
   private Float pz;
   @JsonProperty("WCZL")
   private Float zz;
   @JsonProperty("BJ")
   private int BJ = 1;
   @JsonProperty("FHGD")
   private int FHGD = 1;
   @JsonProperty("YS")
   private int YS = 1;
   @JsonProperty("WG")
   private int WG = 1;
   @JsonProperty("YY")
   private int YY = 1;
   @JsonProperty("CZKHZH")
   private int CZKHZH = 1;
   @JsonProperty("AQFJ")
   private int AQFJ = 1;
   @JsonProperty("XL")
   private int XL = 1;
   @JsonProperty("WGJC")
   private int WGJC = 1;
   @JsonProperty("PTWD")
   private int PTWD = 1;
   @JsonProperty("FJBQ")
   private int FJBQ = 1;
   @JsonProperty("BZ")
   private String BZ = "";

   public String getQrcode() {
      return this.qrcode;
   }

   public void setQrcode(String qrcode) {
      this.qrcode = qrcode;
   }

   public String getDw() {
      return this.dw;
   }

   public void setDw(String dw) {
      this.dw = dw;
   }

   public int getBJ() {
      return this.BJ;
   }

   public void setBJ(int BJ) {
      this.BJ = BJ;
   }

   public int getFHGD() {
      return this.FHGD;
   }

   public void setFHGD(int FHGD) {
      this.FHGD = FHGD;
   }

   public int getYS() {
      return this.YS;
   }

   public void setYS(int YS) {
      this.YS = YS;
   }

   public int getWG() {
      return this.WG;
   }

   public void setWG(int WG) {
      this.WG = WG;
   }

   public int getYY() {
      return this.YY;
   }

   public void setYY(int YY) {
      this.YY = YY;
   }

   public int getCZKHZH() {
      return this.CZKHZH;
   }

   public void setCZKHZH(int CZKHZH) {
      this.CZKHZH = CZKHZH;
   }

   public int getAQFJ() {
      return this.AQFJ;
   }

   public void setAQFJ(int AQFJ) {
      this.AQFJ = AQFJ;
   }

   public int getXL() {
      return this.XL;
   }

   public void setXL(int XL) {
      this.XL = XL;
   }

   public int getWGJC() {
      return this.WGJC;
   }

   public void setWGJC(int WGJC) {
      this.WGJC = WGJC;
   }

   public int getPTWD() {
      return this.PTWD;
   }

   public void setPTWD(int PTWD) {
      this.PTWD = PTWD;
   }

   public int getFJBQ() {
      return this.FJBQ;
   }

   public void setFJBQ(int FJBQ) {
      this.FJBQ = FJBQ;
   }

   public String getBZ() {
      return this.BZ;
   }

   public void setBZ(String BZ) {
      this.BZ = BZ;
   }

   public String getStation_num() {
      return this.station_num;
   }

   public void setStation_num(String station_num) {
      this.station_num = station_num;
   }

   public String getNumber() {
      return this.number;
   }

   public void setNumber(String number) {
      this.number = number;
   }

   public String getStart_time() {
      return this.start_time;
   }

   public void setStart_time(String start_time) {
      this.start_time = start_time;
   }

   public String getEnd_time() {
      return this.end_time;
   }

   public void setEnd_time(String end_time) {
      this.end_time = end_time;
   }

   public Float getJz() {
      return this.jz;
   }

   public void setJz(Float jz) {
      this.jz = jz;
   }

   public Float getPz() {
      return this.pz;
   }

   public void setPz(Float pz) {
      this.pz = pz;
   }

   public Float getZz() {
      return this.zz;
   }

   public void setZz(Float zz) {
      this.zz = zz;
   }

   public Float getSz() {
      return this.sz;
   }

   public void setSz(Float sz) {
      this.sz = sz;
   }

   public String getPeop_num() {
      return this.peop_num;
   }

   public void setPeop_num(String peop_num) {
      this.peop_num = peop_num;
   }

   public String toString() {
      return "MyRequestData11{start_time='" + this.start_time + '\'' + ", end_time='" + this.end_time + '\'' + ", qrcode='" + this.qrcode + '\'' + ", station_num='" + this.station_num + '\'' + ", peop_num='" + this.peop_num + '\'' + ", dw='" + this.dw + '\'' + ", number='" + this.number + '\'' + ", sz='" + this.sz + '\'' + ", jz='" + this.jz + '\'' + ", pz='" + this.pz + '\'' + ", zz='" + this.zz + '\'' + ", BJ=" + this.BJ + ", FHGD=" + this.FHGD + ", YS=" + this.YS + ", WG=" + this.WG + ", YY=" + this.YY + ", CZKHZH=" + this.CZKHZH + ", AQFJ=" + this.AQFJ + ", XL=" + this.XL + ", WGJC=" + this.WGJC + ", PTWD=" + this.PTWD + ", FJBQ=" + this.FJBQ + ", BZ='" + this.BZ + '\'' + '}';
   }
}
