package com.thirdinf.client1;

public class MyResponseData1 {
   private String code;
   private String msg;

   public void setCode(String code) {
      this.code = code;
   }

   public String getCode() {
      return this.code;
   }

   public String getMsg() {
      return this.msg;
   }

   public void setMsg(String msg) {
      this.msg = msg;
   }

   public String toString() {
      return "MyResponseData1{code='" + this.code + '\'' + ", msg='" + this.msg + '\'' + '}';
   }
}
