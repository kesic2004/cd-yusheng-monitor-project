package com.thirdinf.client5;

public class MyCheckResponseData5 {
   private String code;
   private String status;
   private String record_voucher;
   private String message;

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

   public String getMessage() {
      return this.message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public String toString() {
      return "MyCheckResponseData5{code='" + this.code + '\'' + ", status='" + this.status + '\'' + ", record_voucher='" + this.record_voucher + '\'' + ", message='" + this.message + '\'' + '}';
   }
}
