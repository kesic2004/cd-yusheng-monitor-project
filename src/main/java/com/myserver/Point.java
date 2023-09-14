package com.myserver;

public class Point {
   private int channel;
   private String type;
   private String value;

   public int getChannel() {
      return this.channel;
   }

   public void setChannel(int channel) {
      this.channel = channel;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String toString() {
      return "Point{channel=" + this.channel + ", type='" + this.type + '\'' + ", value='" + this.value + '\'' + '}';
   }
}
