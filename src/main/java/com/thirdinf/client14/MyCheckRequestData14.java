package com.thirdinf.client14;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MyCheckRequestData14 {
   @JsonProperty("DataIdentity")
   private String dataIdentity;
   @JsonProperty("CommandID")
   private String commandID;
   @JsonProperty("Data")
   private Data data;

   public String getDataIdentity() {
      return this.dataIdentity;
   }

   public void setDataIdentity(String dataIdentity) {
      this.dataIdentity = dataIdentity;
   }

   public String getCommandID() {
      return this.commandID;
   }

   public void setCommandID(String commandID) {
      this.commandID = commandID;
   }

   public Data getData() {
      return this.data;
   }

   public void setData(Data data) {
      this.data = data;
   }

   public String toString() {
      return "MyCheckRequestData14{dataIdentity='" + this.dataIdentity + '\'' + ", commandID='" + this.commandID + '\'' + ", data=" + this.data + '}';
   }
}
