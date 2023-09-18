package com.thirdinf.client13;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Body {
   @JsonProperty("ErrCode")
   private String errCode;
   @JsonProperty("ErrMsg")
   private String errMsg;
   @JsonProperty("qrcode")
   private String qrcode;
   @JsonProperty("bottle_code")
   private String bottle_code;
   @JsonProperty("chipid")
   private String chipid;

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

   public void setQrcode(String qrcode) {
      this.qrcode = qrcode;
   }

   public String getQrcode() {
      return this.qrcode;
   }

   public void setBottle_code(String bottle_code) {
      this.bottle_code = bottle_code;
   }

   public String getBottle_code() {
      return this.bottle_code;
   }

   public void setChipid(String chipid) {
      this.chipid = chipid;
   }

   public String getChipid() {
      return this.chipid;
   }

   public String toString() {
      return "Body{ErrCode='" + this.errCode + '\'' + ", ErrMsg='" + this.errMsg + '\'' + ", qrcode='" + this.qrcode + '\'' + ", bottle_code='" + this.bottle_code + '\'' + ", chipid='" + this.chipid + '\'' + '}';
   }
}
