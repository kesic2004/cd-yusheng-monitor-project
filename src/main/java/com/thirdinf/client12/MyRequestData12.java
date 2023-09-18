package com.thirdinf.client12;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class MyRequestData12 {
   @JsonProperty("Items")
   private List<Items> items;
   @JsonProperty("Man")
   private Man man;
   @JsonProperty("TraceDate")
   private String traceDate;

   public void setItems(List<Items> items) {
      this.items = items;
   }

   public List<Items> getItems() {
      return this.items;
   }

   public void setMan(Man man) {
      this.man = man;
   }

   public Man getMan() {
      return this.man;
   }

   public void setTraceDate(String traceDate) {
      this.traceDate = traceDate;
   }

   public String getTraceDate() {
      return this.traceDate;
   }
}
