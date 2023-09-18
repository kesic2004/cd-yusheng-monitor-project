package com.thirdinf.client12;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MyResponseData12 {
   @JsonProperty("State")
   private int state;
   @JsonProperty("Value")
   private String value;

   public int getState() {
      return this.state;
   }

   public void setState(int state) {
      this.state = state;
   }

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String toString() {
      return "MyResponseData12{state=" + this.state + ", value='" + this.value + '\'' + '}';
   }
}
