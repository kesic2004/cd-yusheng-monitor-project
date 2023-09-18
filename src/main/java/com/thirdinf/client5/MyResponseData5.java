package com.thirdinf.client5;

public class MyResponseData5 {
   private String code;
   private String message;

   public String getCode() {
      return this.code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getMessage() {
      return this.message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public String toString() {
      return "MyResponseData5{code='" + this.code + '\'' + ", message='" + this.message + '\'' + '}';
   }
}
