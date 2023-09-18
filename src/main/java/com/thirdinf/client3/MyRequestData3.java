package com.thirdinf.client3;

public class MyRequestData3 {
   private String qrcode;
   private String number;
   private String start_time;
   private String end_time;
   private String record_voucher;
   private String jz;
   private String pz;
   private String zz;
   private String sz;
   private String fs;
   private String gx;
   private String peop_num;
   private String status;

   public String getQrcode() {
      return this.qrcode;
   }

   public void setQrcode(String qrcode) {
      this.qrcode = qrcode;
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

   public String getRecord_voucher() {
      return this.record_voucher;
   }

   public void setRecord_voucher(String record_voucher) {
      this.record_voucher = record_voucher;
   }

   public String getJz() {
      return this.jz;
   }

   public void setJz(String jz) {
      this.jz = jz;
   }

   public String getGx() {
      return this.gx;
   }

   public void setGx(String gx) {
      this.gx = gx;
   }

   public String getSz() {
      return this.sz;
   }

   public void setSz(String sz) {
      this.sz = sz;
   }

   public String getPz() {
      return this.pz;
   }

   public void setPz(String pz) {
      this.pz = pz;
   }

   public String getZz() {
      return this.zz;
   }

   public void setZz(String zz) {
      this.zz = zz;
   }

   public String getFs() {
      return this.fs;
   }

   public void setFs(String fs) {
      this.fs = fs;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String toString() {
      return "MyRequestData1{qrcode='" + this.qrcode + '\'' + ", number='" + this.number + '\'' + ", start_time='" + this.start_time + '\'' + ", end_time='" + this.end_time + '\'' + ", record_voucher='" + this.record_voucher + '\'' + ", jz='" + this.jz + '\'' + ", sz='" + this.sz + '\'' + ", pz='" + this.pz + '\'' + ", zz='" + this.zz + '\'' + ", fs='" + this.fs + '\'' + ", gx='" + this.gx + '\'' + ", status='" + this.status + '\'' + '}';
   }

   public String getPeop_num() {
      return this.peop_num;
   }

   public void setPeop_num(String peop_num) {
      this.peop_num = peop_num;
   }
}
