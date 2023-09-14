package com.thirdinf.client12;

public class MyResponseData12 {
   private String code;
   private String error;

   public String getError() {
      return this.error;
   }

   public void setError(String error) {
      this.error = error;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getCode() {
      return this.code;
   }

   public String toString() {
      return "MyResponseData12{code='" + this.code + '\'' + ", error='" + this.error + '\'' + '}';
   }
}
