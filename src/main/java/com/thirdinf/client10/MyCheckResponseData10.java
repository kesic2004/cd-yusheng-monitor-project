package com.thirdinf.client10;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MyCheckResponseData10 {
   @JsonProperty("CallBackID")
   private int callBackID;
   @JsonProperty("DataIdentity")
   private String dataIdentity;
   @JsonProperty("RetCode")
   private String retCode;
   @JsonProperty("RetMsg")
   private String retMsg;
   @JsonProperty("Body")
   private Body body;

   public int getCallBackID() {
      return this.callBackID;
   }

   public void setCallBackID(int callBackID) {
   }

   public void setRetCode(String retCode) {
      this.retCode = retCode;
   }

   public String getRetCode() {
      return this.retCode;
   }

   public void setRetMsg(String retMsg) {
      this.retMsg = retMsg;
   }

   public String getRetMsg() {
      return this.retMsg;
   }

   public void setDataIdentity(String dataIdentity) {
      this.dataIdentity = dataIdentity;
   }

   public String getDataIdentity() {
      return this.dataIdentity;
   }

   public void setBody(Body body) {
      this.body = body;
   }

   public Body getBody() {
      return this.body;
   }

   public String toString() {
      return "MyCheckResponseData10{callBackID=" + this.callBackID + ", retCode='" + this.retCode + '\'' + ", retMsg='" + this.retMsg + '\'' + ", dataIdentity='" + this.dataIdentity + '\'' + ", body=" + this.body + '}';
   }
}
