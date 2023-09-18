package com.thirdinf.client13;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class MyRequestDataEx13 {
   @JsonProperty("DataIdentity")
   private String dataIdentity;
   @JsonProperty("CommandID")
   private String commandID;
   @JsonProperty("Data")
   private List<Data2> data;
   @JsonProperty("Sign")
   private String sign;

   public void setDataIdentity(String dataIdentity) {
      this.dataIdentity = dataIdentity;
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

   public void setData(List<Data2> data) {
      this.data = data;
   }

   public List<Data2> getData() {
      return this.data;
   }

   public String toString() {
      return "MyRequestDataEx13{dataIdentity='" + this.dataIdentity + '\'' + ", sign='" + this.sign + '\'' + ", commandID='" + this.commandID + '\'' + ", data=" + this.data + '}';
   }
}
