package com.model.xk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.xk.entity.XkCylinderrec;
import java.util.Date;
import org.apache.ibatis.annotations.Param;

public interface XkCylinderrecService extends IService<XkCylinderrec> {
   void deleteCylinderrec();

   void deleteCylinderrecByStationCode(String stationCode);

   XkCylinderrec findcClinderRecEx(@Param("stationCode") String stationCode, @Param("barcode") String barcode);

   XkCylinderrec findcClinderRec(@Param("barcode") String barcode);

   Date getMaxInternalTime();

   Date getMaxInternalTimeByDid(Integer did);

   Date getMaxInternalTimeByStationCode(String stationCode);

   void deleteCylinderrecByDid(Integer did);
}
