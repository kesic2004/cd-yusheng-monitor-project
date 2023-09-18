package com.thirdinf.client11;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MyCheckRequestData11 {
   @JsonProperty("ZDBH")
   private Integer station_num;
   @JsonProperty("TMH")
   private String qrcode;

   public Integer getStation_num() {
      return this.station_num;
   }

   public void setStation_num(Integer station_num) {
      this.station_num = station_num;
   }

   public String getQrcode() {
      return this.qrcode;
   }

   public void setQrcode(String qrcode) {
      this.qrcode = qrcode;
   }
}
