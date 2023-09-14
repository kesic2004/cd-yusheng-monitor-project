package com.thirdinf.client1;

public class Result {
   private String status;

   public void setStatus(String status) {
      this.status = status;
   }

   public String getStatus() {
      return this.status;
   }

   public String toString() {
      return "Result{status='" + this.status + '\'' + '}';
   }
}
