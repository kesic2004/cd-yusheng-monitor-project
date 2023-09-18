package com.jobs.fj;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Factory {
   @JsonProperty("Id")
   private int Id;
   @JsonProperty("Name")
   private String Name;

   public void setId(int Id) {
      this.Id = Id;
   }

   public int getId() {
      return this.Id;
   }

   public void setName(String Name) {
      this.Name = Name;
   }

   public String getName() {
      return this.Name;
   }
}
