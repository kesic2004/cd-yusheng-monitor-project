package com.thirdinf.client1;

public class MyResponseData1 {
   private String code;
   private int count;
   private String message;
   private boolean result;
   private int status;

   public void setCode(String code) {
      this.code = code;
   }

   public String getCode() {
      return this.code;
   }

   public void setCount(int count) {
      this.count = count;
   }

   public int getCount() {
      return this.count;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public String getMessage() {
      return this.message;
   }

   public void setResult(boolean result) {
      this.result = result;
   }

   public boolean getResult() {
      return this.result;
   }

   public void setStatus(int status) {
      this.status = status;
   }

   public int getStatus() {
      return this.status;
   }

   public String toString() {
      return "MyResponseData1{code='" + this.code + '\'' + ", count=" + this.count + ", message='" + this.message + '\'' + ", result=" + this.result + ", status=" + this.status + '}';
   }
}
