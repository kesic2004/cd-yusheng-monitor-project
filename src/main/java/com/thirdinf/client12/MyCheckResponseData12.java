package com.thirdinf.client12;

public class MyCheckResponseData12 {
   private String code;
   private String status;

   public String toString() {
      return "MyCheckResponseData12{code='" + this.code + '\'' + ", status='" + this.status + '\'' + '}';
   }

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
}
