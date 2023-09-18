package com.thirdinf.client4;

public class MyResponseData4 {
   private String state;
   private String message;

   public String getState() {
      return this.state;
   }

   public void setState(String state) {
      this.state = state;
   }

   public String getMessage() {
      return this.message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public String toString() {
      return "MyResponseData16{state='" + this.state + '\'' + ", message='" + this.message + '\'' + '}';
   }
}
