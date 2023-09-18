package com.thirdinf.client12;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Factory {
   @JsonProperty("Id")
   private int id;
   @JsonProperty("Name")
   private String name;

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String toString() {
      return "Factory{id=" + this.id + ", name='" + this.name + '\'' + '}';
   }
}
