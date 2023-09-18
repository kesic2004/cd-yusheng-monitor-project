package com.thirdinf.client4;

public class MyCheckResponseData4 {
   private String state;
   private String data;
   private String message;

   public String getState() {
      return this.state;
   }

   public void setState(String state) {
      this.state = state;
   }

   public String getData() {
      return this.data;
   }

   public void setData(String data) {
      this.data = data;
   }

   public String getMessage() {
      return this.message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public String toString() {
      return "MyCheckResponseData16{state='" + this.state + '\'' + ", data='" + this.data + '\'' + ", message='" + this.message + '\'' + '}';
   }
}
