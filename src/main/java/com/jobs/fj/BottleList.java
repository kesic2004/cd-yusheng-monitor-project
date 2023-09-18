package com.jobs.fj;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class BottleList {
   @JsonProperty("PlanCount")
   private int PlanCount;
   @JsonProperty("Items")
   private List<Items> Items;

   public void setPlanCount(int PlanCount) {
      this.PlanCount = PlanCount;
   }

   public int getPlanCount() {
      return this.PlanCount;
   }

   public void setItems(List<Items> Items) {
      this.Items = Items;
   }

   public List<Items> getItems() {
      return this.Items;
   }
}
