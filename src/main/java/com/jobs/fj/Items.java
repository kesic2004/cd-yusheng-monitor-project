package com.jobs.fj;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Items {
   @JsonProperty("Barcode")
   private String Barcode;
   @JsonProperty("Printno")
   private String Printno;
   @JsonProperty("Style")
   private String Style;
   @JsonProperty("Valve")
   private int Valve;
   @JsonProperty("ProductDate")
   private String ProductDate;
   @JsonProperty("DiscardDate")
   private String DiscardDate;
   @JsonProperty("CheckDate")
   private String CheckDate;
   @JsonProperty("NextCheckDate")
   private String NextCheckDate;
   @JsonProperty("UpdateDate")
   private String UpdateDate;
   @JsonProperty("State")
   private int State;
   @JsonProperty("Id")
   private int Id;
   @JsonProperty("Factory")
   private Factory Factory;

   public void setBarcode(String Barcode) {
      this.Barcode = Barcode;
   }

   public String getBarcode() {
      return this.Barcode;
   }

   public void setPrintno(String Printno) {
      this.Printno = Printno;
   }

   public String getPrintno() {
      return this.Printno;
   }

   public void setStyle(String Style) {
      this.Style = Style;
   }

   public String getStyle() {
      return this.Style;
   }

   public void setValve(int Valve) {
      this.Valve = Valve;
   }

   public int getValve() {
      return this.Valve;
   }

   public void setProductDate(String ProductDate) {
      this.ProductDate = ProductDate;
   }

   public String getProductDate() {
      return this.ProductDate;
   }

   public void setDiscardDate(String DiscardDate) {
      this.DiscardDate = DiscardDate;
   }

   public String getDiscardDate() {
      return this.DiscardDate;
   }

   public void setCheckDate(String CheckDate) {
      this.CheckDate = CheckDate;
   }

   public String getCheckDate() {
      return this.CheckDate;
   }

   public void setNextCheckDate(String NextCheckDate) {
      this.NextCheckDate = NextCheckDate;
   }

   public String getNextCheckDate() {
      return this.NextCheckDate;
   }

   public void setUpdateDate(String UpdateDate) {
      this.UpdateDate = UpdateDate;
   }

   public String getUpdateDate() {
      return this.UpdateDate;
   }

   public void setState(int State) {
      this.State = State;
   }

   public int getState() {
      return this.State;
   }

   public void setId(int Id) {
      this.Id = Id;
   }

   public int getId() {
      return this.Id;
   }

   public void setFactory(Factory Factory) {
      this.Factory = Factory;
   }

   public Factory getFactory() {
      return this.Factory;
   }
}
