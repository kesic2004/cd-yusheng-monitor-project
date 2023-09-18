package com.model.fj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

public class FjBottleBak extends Model<FjBottleBak> {
   private static final long serialVersionUID = 1L;
   @TableId(
      value = "id",
      type = IdType.AUTO
   )
   private Integer id;
   private String barcode;
   private String printno;
   private String style;
   private Integer valve;
   private String productdate;
   private String discarddate;
   private String checkdate;
   private String nextcheckdate;
   private String updatedate;
   private Integer state;
   private Integer systemid;
   private Integer factoryid;
   private String factoryname;
   private Integer did;

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getBarcode() {
      return this.barcode;
   }

   public void setBarcode(String barcode) {
      this.barcode = barcode;
   }

   public String getPrintno() {
      return this.printno;
   }

   public void setPrintno(String printno) {
      this.printno = printno;
   }

   public String getStyle() {
      return this.style;
   }

   public void setStyle(String style) {
      this.style = style;
   }

   public Integer getValve() {
      return this.valve;
   }

   public void setValve(Integer valve) {
      this.valve = valve;
   }

   public String getProductdate() {
      return this.productdate;
   }

   public void setProductdate(String productdate) {
      this.productdate = productdate;
   }

   public String getDiscarddate() {
      return this.discarddate;
   }

   public void setDiscarddate(String discarddate) {
      this.discarddate = discarddate;
   }

   public String getCheckdate() {
      return this.checkdate;
   }

   public void setCheckdate(String checkdate) {
      this.checkdate = checkdate;
   }

   public String getNextcheckdate() {
      return this.nextcheckdate;
   }

   public void setNextcheckdate(String nextcheckdate) {
      this.nextcheckdate = nextcheckdate;
   }

   public String getUpdatedate() {
      return this.updatedate;
   }

   public void setUpdatedate(String updatedate) {
      this.updatedate = updatedate;
   }

   public Integer getState() {
      return this.state;
   }

   public void setState(Integer state) {
      this.state = state;
   }

   public Integer getSystemid() {
      return this.systemid;
   }

   public void setSystemid(Integer systemid) {
      this.systemid = systemid;
   }

   public Integer getFactoryid() {
      return this.factoryid;
   }

   public void setFactoryid(Integer factoryid) {
      this.factoryid = factoryid;
   }

   public String getFactoryname() {
      return this.factoryname;
   }

   public void setFactoryname(String factoryname) {
      this.factoryname = factoryname;
   }

   public Integer getDid() {
      return this.did;
   }

   public void setDid(Integer did) {
      this.did = did;
   }

   protected Serializable pkVal() {
      return this.id;
   }

   public String toString() {
      return "FjBottleBak{id=" + this.id + ", barcode=" + this.barcode + ", printno=" + this.printno + ", style=" + this.style + ", valve=" + this.valve + ", productdate=" + this.productdate + ", discarddate=" + this.discarddate + ", checkdate=" + this.checkdate + ", nextcheckdate=" + this.nextcheckdate + ", updatedate=" + this.updatedate + ", state=" + this.state + ", systemid=" + this.systemid + ", factoryid=" + this.factoryid + ", factoryname=" + this.factoryname + ", did=" + this.did + "}";
   }
}
