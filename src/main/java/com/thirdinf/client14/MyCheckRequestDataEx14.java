package com.thirdinf.client14;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MyCheckRequestDataEx14 {
   @JsonProperty("DataIdentity")
   private String dataIdentity;
   @JsonProperty("CommandID")
   private String commandID;
   @JsonProperty("Data")
   private Data data;
   @JsonProperty("Sign")
   private String sign;

   public void setDataIdentity(String DataIdentity) {
      this.dataIdentity = DataIdentity;
   }

   public String getDataIdentity() {
      return this.dataIdentity;
   }

   public void setSign(String sign) {
      this.sign = sign;
   }

   public String getSign() {
      return this.sign;
   }

   public void setCommandID(String commandID) {
      this.commandID = commandID;
   }

   public String getCommandID() {
      return this.commandID;
   }

   public void setData(Data data) {
      this.data = data;
   }

   public Data getData() {
      return this.data;
   }

   public String toString() {
      return "MyCheckRequestDataEx14{dataIdentity='" + this.dataIdentity + '\'' + ", sign='" + this.sign + '\'' + ", commandID='" + this.commandID + '\'' + ", data=" + this.data + '}';
   }
}
