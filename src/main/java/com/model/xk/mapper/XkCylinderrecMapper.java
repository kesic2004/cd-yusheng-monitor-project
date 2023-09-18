package com.model.xk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.model.xk.entity.XkCylinderrec;
import java.util.Date;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface XkCylinderrecMapper extends BaseMapper<XkCylinderrec> {
   @Update({"truncate table xk_cylinderrec"})
   void deleteCylinderrec();

   @Delete({"Delete From xk_cylinderrec Where stationCode = #{stationCode}"})
   void deleteCylinderrecByStationCode(@Param("stationCode") String stationCode);

   @Select({"SELECT id, stationCode, code, factoryCode, factoryName, factoryNumber, cylinderTypeCode, cylinderTypeName, mediumCode, mediumName, mediumInspectPeriod, mediumColor, mediumScrappedPeriod, factoryDate, lastInspectDate, nextInspectDate, scrappedDate, registerCode, nominalPressure, cylinderVolume, designThickness, status, barcode, memo, proofMaterial, ownNumber, actualMediumName, enterDate, recordDate, specification, recordStatus, lastInspectNumber, lastRegisterNumber, data01, data02, data03, data04, data05, data06, data07, data08, data09, data10, isNewCylinder, auditAgency, auditPerson, auditDate, delayIssuing, recordFlag, senderCode, virtualUser, backReason, recordTime, lastUpdateTime, internalState, internalTime FROM xk_cylinderrec  WHERE  stationCode = #{stationCode} And barcode = #{barcode} LIMIT 1"})
   XkCylinderrec findcClinderRecEx(@Param("stationCode") String stationCode, @Param("barcode") String barcode);

   @Select({"SELECT id, stationCode, code, factoryCode, factoryName, factoryNumber, cylinderTypeCode, cylinderTypeName, mediumCode, mediumName, mediumInspectPeriod, mediumColor, mediumScrappedPeriod, factoryDate, lastInspectDate, nextInspectDate, scrappedDate, registerCode, nominalPressure, cylinderVolume, designThickness, status, barcode, memo, proofMaterial, ownNumber, actualMediumName, enterDate, recordDate, specification, recordStatus, lastInspectNumber, lastRegisterNumber, data01, data02, data03, data04, data05, data06, data07, data08, data09, data10, isNewCylinder, auditAgency, auditPerson, auditDate, delayIssuing, recordFlag, senderCode, virtualUser, backReason, recordTime, lastUpdateTime, internalState, internalTime FROM xk_cylinderrec  WHERE  barcode = #{barcode} LIMIT 1"})
   XkCylinderrec findcClinderRec(@Param("barcode") String barcode);

   @Select({"select max(internalTime) from xk_cylinderrec"})
   Date getMaxInternalTime();

   @Select({"select max(internalTime) from xk_cylinderrec Where did = #{did}"})
   Date getMaxInternalTimeByDid(@Param("did") Integer did);

   @Select({"select max(internalTime) from xk_cylinderrec Where stationCode = #{stationCode}"})
   Date getMaxInternalTimeByStationCode(@Param("stationCode") String stationCode);

   @Delete({"Delete From xk_cylinderrec Where did = #{did}"})
   void deleteCylinderrecByDid(@Param("did") Integer did);
}
