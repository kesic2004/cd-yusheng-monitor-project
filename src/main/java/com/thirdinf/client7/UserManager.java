package com.thirdinf.client7;

public class UserManager {
   private String userCode;
   private String userPass;

   public String getUserCode() {
      return this.userCode;
   }

   public void setUserCode(String userCode) {
      this.userCode = userCode;
   }

   public String getUserPass() {
      return this.userPass;
   }

   public void setUserPass(String userPass) {
      this.userPass = userPass;
   }

   public String toString() {
      return "UserManager{userCode='" + this.userCode + '\'' + ", userPass='" + this.userPass + '\'' + '}';
   }
}
