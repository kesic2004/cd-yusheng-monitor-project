package com.thirdinf.client3;

public class MyResponseData3 {
   private String code;
   private String msg;

   public String getCode() {
      return this.code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getMsg() {
      return this.msg;
   }

   public void setMsg(String msg) {
      this.msg = msg;
   }

   public String toString() {
      return "MyResponseData3{code='" + this.code + '\'' + ", msg='" + this.msg + '\'' + '}';
   }
}