package com.model.xk.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;

@TableName("xk_cylinderrecbak")
public class XkCylinderrecbak extends Model<XkCylinderrecbak> {
   private static final long serialVersionUID = 1L;
   @TableId("id")
   private Integer id;
   @TableField("stationCode")
   private String stationCode;
   @TableField("code")
   private String code;
   @TableField("factoryCode")
   private String factoryCode;
   @TableField("factoryName")
   private String factoryName;
   @TableField("factoryNumber")
   private String factoryNumber;
   @TableField("cylinderTypeCode")
   private String cylinderTypeCode;
   @TableField("cylinderTypeName")
   private String cylinderTypeName;
   @TableField("mediumCode")
   private String mediumCode;
   @TableField("mediumName")
   private String mediumName;
   @TableField("mediumColor")
   private String mediumColor;
   @TableField("mediumInspectPeriod")
   private Integer mediumInspectPeriod;
   @TableField("mediumScrappedPeriod")
   private Integer mediumScrappedPeriod;
   @TableField("factoryDate")
   private String factoryDate;
   @TableField("lastInspectDate")
   private String lastInspectDate;
   @TableField("nextInspectDate")
   private String nextInspectDate;
   @TableField("scrappedDate")
   private String scrappedDate;
   @TableField("registerCode")
   private String registerCode;
   @TableField("nominalPressure")
   private Float nominalPressure;
   @TableField("cylinderVolume")
   private Float cylinderVolume;
   @TableField("designThickness")
   private Float designThickness;
   @TableField("status")
   private String status;
   @TableField("barcode")
   private String barcode;
   @TableField("memo")
   private String memo;
   @TableField("proofMaterial")
   private String proofMaterial;
   @TableField("ownNumber")
   private String ownNumber;
   @TableField("actualMediumName")
   private String actualMediumName;
   @TableField("enterDate")
   private String enterDate;
   @TableField("recordDate")
   private String recordDate;
   @TableField("specification")
   private String specification;
   @TableField("recordStatus")
   private Integer recordStatus;
   @TableField("lastInspectNumber")
   private String lastInspectNumber;
   @TableField("lastRegisterNumber")
   private String lastRegisterNumber;
   @TableField("data01")
   private String data01;
   @TableField("data02")
   private String data02;
   @TableField("data03")
   private String data03;
   @TableField("data04")
   private String data04;
   @TableField("data05")
   private String data05;
   @TableField("data06")
   private String data06;
   @TableField("data07")
   private String data07;
   @TableField("data08")
   private String data08;
   @TableField("data09")
   private String data09;
   @TableField("data10")
   private String data10;
   @TableField("isNewCylinder")
   private Integer isNewCylinder;
   @TableField("auditAgency")
   private String auditAgency;
   @TableField("auditPerson")
   private String auditPerson;
   @TableField("auditDate")
   private String auditDate;
   @TableField("delayIssuing")
   private Integer delayIssuing;
   @TableField("recordFlag")
   private String recordFlag;
   @TableField("senderCode")
   private String senderCode;
   @TableField("virtualUser")
   private String virtualUser;
   @TableField("backReason")
   private String backReason;
   @TableField("recordTime")
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss"
   )
   private String recordTime;
   @TableField("lastUpdateTime")
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss"
   )
   private String lastUpdateTime;
   @TableField("internalState")
   private Integer internalState;
   @TableField("internalTime")
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss.SSS"
   )
   private String internalTime;
   @TableField("did")
   private Integer did;

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getStationCode() {
      return this.stationCode;
   }

   public void setStationCode(String stationCode) {
      this.stationCode = stationCode;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getFactoryCode() {
      return this.factoryCode;
   }

   public void setFactoryCode(String factoryCode) {
      this.factoryCode = factoryCode;
   }

   public String getFactoryName() {
      return this.factoryName;
   }

   public void setFactoryName(String factoryName) {
      this.factoryName = factoryName;
   }

   public String getFactoryNumber() {
      return this.factoryNumber;
   }

   public void setFactoryNumber(String factoryNumber) {
      this.factoryNumber = factoryNumber;
   }

   public String getCylinderTypeCode() {
      return this.cylinderTypeCode;
   }

   public void setCylinderTypeCode(String cylinderTypeCode) {
      this.cylinderTypeCode = cylinderTypeCode;
   }

   public String getCylinderTypeName() {
      return this.cylinderTypeName;
   }

   public void setCylinderTypeName(String cylinderTypeName) {
      this.cylinderTypeName = cylinderTypeName;
   }

   public String getMediumCode() {
      return this.mediumCode;
   }

   public void setMediumCode(String mediumCode) {
      this.mediumCode = mediumCode;
   }

   public String getMediumName() {
      return this.mediumName;
   }

   public void setMediumName(String mediumName) {
      this.mediumName = mediumName;
   }

   public Integer getMediumInspectPeriod() {
      return this.mediumInspectPeriod;
   }

   public void setMediumInspectPeriod(Integer mediumInspectPeriod) {
      this.mediumInspectPeriod = mediumInspectPeriod;
   }

   public Integer getMediumScrappedPeriod() {
      return this.mediumScrappedPeriod;
   }

   public void setMediumScrappedPeriod(Integer mediumScrappedPeriod) {
      this.mediumScrappedPeriod = mediumScrappedPeriod;
   }

   public String getFactoryDate() {
      return this.factoryDate;
   }

   public void setFactoryDate(String factoryDate) {
      this.factoryDate = factoryDate;
   }

   public String getLastInspectDate() {
      return this.lastInspectDate;
   }

   public void setLastInspectDate(String lastInspectDate) {
      this.lastInspectDate = lastInspectDate;
   }

   public String getNextInspectDate() {
      return this.nextInspectDate;
   }

   public void setNextInspectDate(String nextInspectDate) {
      this.nextInspectDate = nextInspectDate;
   }

   public String getScrappedDate() {
      return this.scrappedDate;
   }

   public void setScrappedDate(String scrappedDate) {
      this.scrappedDate = scrappedDate;
   }

   public String getRegisterCode() {
      return this.registerCode;
   }

   public void setRegisterCode(String registerCode) {
      this.registerCode = registerCode;
   }

   public Float getNominalPressure() {
      return this.nominalPressure;
   }

   public void setNominalPressure(Float nominalPressure) {
      this.nominalPressure = nominalPressure;
   }

   public Float getCylinderVolume() {
      return this.cylinderVolume;
   }

   public void setCylinderVolume(Float cylinderVolume) {
      this.cylinderVolume = cylinderVolume;
   }

   public Float getDesignThickness() {
      return this.designThickness;
   }

   public void setDesignThickness(Float designThickness) {
      this.designThickness = designThickness;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getBarcode() {
      return this.barcode;
   }

   public void setBarcode(String barcode) {
      this.barcode = barcode;
   }

   public String getMemo() {
      return this.memo;
   }

   public void setMemo(String memo) {
      this.memo = memo;
   }

   public String getProofMaterial() {
      return this.proofMaterial;
   }

   public void setProofMaterial(String proofMaterial) {
      this.proofMaterial = proofMaterial;
   }

   public String getOwnNumber() {
      return this.ownNumber;
   }

   public void setOwnNumber(String ownNumber) {
      this.ownNumber = ownNumber;
   }

   public String getActualMediumName() {
      return this.actualMediumName;
   }

   public void setActualMediumName(String actualMediumName) {
      this.actualMediumName = actualMediumName;
   }

   public String getEnterDate() {
      return this.enterDate;
   }

   public void setEnterDate(String enterDate) {
      this.enterDate = enterDate;
   }

   public String getRecordDate() {
      return this.recordDate;
   }

   public void setRecordDate(String recordDate) {
      this.recordDate = recordDate;
   }

   public String getSpecification() {
      return this.specification;
   }

   public void setSpecification(String specification) {
      this.specification = specification;
   }

   public Integer getRecordStatus() {
      return this.recordStatus;
   }

   public void setRecordStatus(Integer recordStatus) {
      this.recordStatus = recordStatus;
   }

   public String getLastInspectNumber() {
      return this.lastInspectNumber;
   }

   public void setLastInspectNumber(String lastInspectNumber) {
      this.lastInspectNumber = lastInspectNumber;
   }

   public String getLastRegisterNumber() {
      return this.lastRegisterNumber;
   }

   public void setLastRegisterNumber(String lastRegisterNumber) {
      this.lastRegisterNumber = lastRegisterNumber;
   }

   public String getData01() {
      return this.data01;
   }

   public void setData01(String data01) {
      this.data01 = data01;
   }

   public String getData02() {
      return this.data02;
   }

   public void setData02(String data02) {
      this.data02 = data02;
   }

   public String getData03() {
      return this.data03;
   }

   public void setData03(String data03) {
      this.data03 = data03;
   }

   public String getData04() {
      return this.data04;
   }

   public void setData04(String data04) {
      this.data04 = data04;
   }

   public String getData05() {
      return this.data05;
   }

   public void setData05(String data05) {
      this.data05 = data05;
   }

   public String getData06() {
      return this.data06;
   }

   public void setData06(String data06) {
      this.data06 = data06;
   }

   public String getData07() {
      return this.data07;
   }

   public void setData07(String data07) {
      this.data07 = data07;
   }

   public String getData08() {
      return this.data08;
   }

   public void setData08(String data08) {
      this.data08 = data08;
   }

   public String getData09() {
      return this.data09;
   }

   public void setData09(String data09) {
      this.data09 = data09;
   }

   public String getData10() {
      return this.data10;
   }

   public void setData10(String data10) {
      this.data10 = data10;
   }

   public Integer getIsNewCylinder() {
      return this.isNewCylinder;
   }

   public void setIsNewCylinder(Integer isNewCylinder) {
      this.isNewCylinder = isNewCylinder;
   }

   public String getAuditAgency() {
      return this.auditAgency;
   }

   public void setAuditAgency(String auditAgency) {
      this.auditAgency = auditAgency;
   }

   public String getAuditPerson() {
      return this.auditPerson;
   }

   public void setAuditPerson(String auditPerson) {
      this.auditPerson = auditPerson;
   }

   public String getAuditDate() {
      return this.auditDate;
   }

   public void setAuditDate(String auditDate) {
      this.auditDate = auditDate;
   }

   public Integer getDelayIssuing() {
      return this.delayIssuing;
   }

   public void setDelayIssuing(Integer delayIssuing) {
      this.delayIssuing = delayIssuing;
   }

   public String getRecordFlag() {
      return this.recordFlag;
   }

   public void setRecordFlag(String recordFlag) {
      this.recordFlag = recordFlag;
   }

   public String getSenderCode() {
      return this.senderCode;
   }

   public void setSenderCode(String senderCode) {
      this.senderCode = senderCode;
   }

   public String getVirtualUser() {
      return this.virtualUser;
   }

   public void setVirtualUser(String virtualUser) {
      this.virtualUser = virtualUser;
   }

   public String getBackReason() {
      return this.backReason;
   }

   public void setBackReason(String backReason) {
      this.backReason = backReason;
   }

   public String getRecordTime() {
      return this.recordTime;
   }

   public void setRecordTime(String recordTime) {
      this.recordTime = recordTime;
   }

   public String getLastUpdateTime() {
      return this.lastUpdateTime;
   }

   public void setLastUpdateTime(String lastUpdateTime) {
      this.lastUpdateTime = lastUpdateTime;
   }

   public Integer getInternalState() {
      return this.internalState;
   }

   public void setInternalState(Integer internalState) {
      this.internalState = internalState;
   }

   public String getInternalTime() {
      return this.internalTime;
   }

   public void setInternalTime(String internalTime) {
      this.internalTime = internalTime;
   }

   protected Serializable pkVal() {
      return this.id;
   }

   public Integer getDid() {
      return this.did;
   }

   public void setDid(Integer did) {
      this.did = did;
   }

   public String getMediumColor() {
      return this.mediumColor;
   }

   public void setMediumColor(String mediumColor) {
      this.mediumColor = mediumColor;
   }

   public String toString() {
      return "XkCylinderrec{id=" + this.id + ", stationCode='" + this.stationCode + '\'' + ", code='" + this.code + '\'' + ", factoryCode='" + this.factoryCode + '\'' + ", factoryName='" + this.factoryName + '\'' + ", factoryNumber='" + this.factoryNumber + '\'' + ", cylinderTypeCode='" + this.cylinderTypeCode + '\'' + ", cylinderTypeName='" + this.cylinderTypeName + '\'' + ", mediumCode='" + this.mediumCode + '\'' + ", mediumName='" + this.mediumName + '\'' + ", mediumColor='" + this.mediumColor + '\'' + ", mediumInspectPeriod=" + this.mediumInspectPeriod + ", mediumScrappedPeriod=" + this.mediumScrappedPeriod + ", factoryDate='" + this.factoryDate + '\'' + ", lastInspectDate='" + this.lastInspectDate + '\'' + ", nextInspectDate='" + this.nextInspectDate + '\'' + ", scrappedDate='" + this.scrappedDate + '\'' + ", registerCode='" + this.registerCode + '\'' + ", nominalPressure=" + this.nominalPressure + ", cylinderVolume=" + this.cylinderVolume + ", designThickness=" + this.designThickness + ", status='" + this.status + '\'' + ", barcode='" + this.barcode + '\'' + ", memo='" + this.memo + '\'' + ", proofMaterial='" + this.proofMaterial + '\'' + ", ownNumber='" + this.ownNumber + '\'' + ", actualMediumName='" + this.actualMediumName + '\'' + ", enterDate=" + this.enterDate + ", recordDate=" + this.recordDate + ", specification='" + this.specification + '\'' + ", recordStatus=" + this.recordStatus + ", lastInspectNumber='" + this.lastInspectNumber + '\'' + ", lastRegisterNumber='" + this.lastRegisterNumber + '\'' + ", data01='" + this.data01 + '\'' + ", data02='" + this.data02 + '\'' + ", data03='" + this.data03 + '\'' + ", data04='" + this.data04 + '\'' + ", data05='" + this.data05 + '\'' + ", data06='" + this.data06 + '\'' + ", data07='" + this.data07 + '\'' + ", data08='" + this.data08 + '\'' + ", data09='" + this.data09 + '\'' + ", data10='" + this.data10 + '\'' + ", isNewCylinder=" + this.isNewCylinder + ", auditAgency='" + this.auditAgency + '\'' + ", auditPerson='" + this.auditPerson + '\'' + ", auditDate=" + this.auditDate + ", delayIssuing=" + this.delayIssuing + ", recordFlag='" + this.recordFlag + '\'' + ", senderCode='" + this.senderCode + '\'' + ", virtualUser='" + this.virtualUser + '\'' + ", backReason='" + this.backReason + '\'' + ", recordTime=" + this.recordTime + ", lastUpdateTime=" + this.lastUpdateTime + ", internalState=" + this.internalState + ", internalTime=" + this.internalTime + ", did=" + this.did + '}';
   }
}
