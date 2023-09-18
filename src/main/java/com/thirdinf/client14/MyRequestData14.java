package com.thirdinf.client14;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class MyRequestData14 {
   @JsonProperty("DataIdentity")
   private String dataIdentity;
   @JsonProperty("CommandID")
   private String commandID;
   @JsonProperty("Data")
   private List<Data2> data;

   public void setDataIdentity(String dataIdentity) {
      this.dataIdentity = dataIdentity;
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

   public void setData(List<Data2> data) {
      this.data = data;
   }

   public List<Data2> getData() {
      return this.data;
   }

   public String toString() {
      return "MyRequestData14{dataIdentity='" + this.dataIdentity + '\'' + ", commandID='" + this.commandID + '\'' + ", data=" + this.data + '}';
   }
}