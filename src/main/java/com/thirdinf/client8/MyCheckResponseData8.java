package com.thirdinf.client8;

public class MyCheckResponseData8 {
   private String code;
   private String status;
   private String record_voucher;
   private String msg;

   public String getCode() {
      return this.code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getRecord_voucher() {
      return this.record_voucher;
   }

   public void setRecord_voucher(String record_voucher) {
      this.record_voucher = record_voucher;
   }

   public String getMsg() {
      return this.msg;
   }

   public void setMsg(String msg) {
      this.msg = msg;
   }

   public String toString() {
      return "MyCheckResponseData8{code='" + this.code + '\'' + ", status='" + this.status + '\'' + ", record_voucher='" + this.record_voucher + '\'' + ", msg='" + this.msg + '\'' + '}';
   }
}
