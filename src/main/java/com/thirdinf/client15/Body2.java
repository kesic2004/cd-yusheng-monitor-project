package com.thirdinf.client15;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Body2 {
   @JsonProperty("ErrCode")
   private String errCode;
   @JsonProperty("ErrMsg")
   private String errMsg;

   public void setErrCode(String errCode) {
      this.errCode = errCode;
   }

   public String getErrCode() {
      return this.errCode;
   }

   public void setErrMsg(String errMsg) {
      this.errMsg = errMsg;
   }

   public String getErrMsg() {
      return this.errMsg;
   }

   public String toString() {
      return "Body2{ErrCode='" + this.errCode + '\'' + ", ErrMsg='" + this.errMsg + '\'' + '}';
   }
}
