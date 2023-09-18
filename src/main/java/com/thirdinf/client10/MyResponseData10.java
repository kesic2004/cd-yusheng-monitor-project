package com.thirdinf.client10;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MyResponseData10 {
   @JsonProperty("CallBackID")
   private String callBackID;
   @JsonProperty("DataIdentity")
   private String dataIdentity;
   @JsonProperty("RetCode")
   private String retCode;
   @JsonProperty("RetMsg")
   private String retMsg;
   @JsonProperty("Body")
   private Body2 body;

   public String getCallBackID() {
      return this.callBackID;
   }

   public void setCallBackID(String callBackID) {
      this.callBackID = callBackID;
   }

   public String toString() {
      return "MyResponseData10{callBackID='" + this.callBackID + '\'' + ", dataIdentity='" + this.dataIdentity + '\'' + ", retCode='" + this.retCode + '\'' + ", retMsg='" + this.retMsg + '\'' + ", body=" + this.body + '}';
   }

   public String getDataIdentity() {
      return this.dataIdentity;
   }

   public void setDataIdentity(String dataIdentity) {
      this.dataIdentity = dataIdentity;
   }

   public String getRetCode() {
      return this.retCode;
   }

   public void setRetCode(String retCode) {
      this.retCode = retCode;
   }

   public String getRetMsg() {
      return this.retMsg;
   }

   public void setRetMsg(String retMsg) {
      this.retMsg = retMsg;
   }

   public Body2 getBody() {
      return this.body;
   }

   public void setBody(Body2 body) {
      this.body = body;
   }
}
