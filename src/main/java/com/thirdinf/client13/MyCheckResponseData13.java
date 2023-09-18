package com.thirdinf.client13;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MyCheckResponseData13 {
   @JsonProperty("CallBackID")
   private int callBackID;
   @JsonProperty("RetCode")
   private String retCode;
   @JsonProperty("RetMsg")
   private String retMsg;
   @JsonProperty("DataIdentity")
   private String dataIdentity;
   @JsonProperty("Body")
   private Body body;

   public int getCallBackID() {
      return this.callBackID;
   }

   public void setCallBackID(int callBackID) {
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

   public String getDataIdentity() {
      return this.dataIdentity;
   }

   public void setDataIdentity(String dataIdentity) {
      this.dataIdentity = dataIdentity;
   }

   public Body getBody() {
      return this.body;
   }

   public void setBody(Body body) {
      this.body = body;
   }

   public String toString() {
      return "MyCheckResponseData13{callBackID=" + this.callBackID + ", retCode='" + this.retCode + '\'' + ", retMsg='" + this.retMsg + '\'' + ", dataIdentity='" + this.dataIdentity + '\'' + ", body=" + this.body + '}';
   }
}
