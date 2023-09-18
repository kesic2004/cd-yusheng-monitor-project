package com.thirdinf.client14;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MyResponseData14 {
   @JsonProperty("CallBackID")
   private String callBackID;
   @JsonProperty("RetCode")
   private String retCode;
   @JsonProperty("RetMsg")
   private String retMsg;
   @JsonProperty("DataIdentity")
   private String dataIdentity;
   @JsonProperty("Body")
   private Body2 body;

   public String getCallBackID() {
      return this.callBackID;
   }

   public void setCallBackID(String callBackID) {
      this.callBackID = callBackID;
   }

   public String toString() {
      return "MyResponseData14{callBackID='" + this.callBackID + '\'' + ", dataIdentity='" + this.dataIdentity + '\'' + ", retCode='" + this.retCode + '\'' + ", retMsg='" + this.retMsg + '\'' + ", body=" + this.body + '}';
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
