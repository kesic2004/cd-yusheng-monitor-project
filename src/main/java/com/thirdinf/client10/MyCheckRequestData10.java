package com.thirdinf.client10;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MyCheckRequestData10 {
   @JsonProperty("DataIdentity")
   private String dataIdentity;
   @JsonProperty("CommandID")
   private String commandID;
   @JsonProperty("Data")
   private Data data;

   public void setDataIdentity(String DataIdentity) {
      this.dataIdentity = DataIdentity;
   }

   public String getDataIdentity() {
      return this.dataIdentity;
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
      return "MyCheckRequestData10{dataIdentity='" + this.dataIdentity + '\'' + ", commandID='" + this.commandID + '\'' + ", data=" + this.data + '}';
   }
}
