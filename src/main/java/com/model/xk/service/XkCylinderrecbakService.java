package com.model.xk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.xk.entity.XkCylinderrecbak;
import java.util.Date;
import org.apache.ibatis.annotations.Param;

public interface XkCylinderrecbakService extends IService<XkCylinderrecbak> {
   void deleteCylinderrecbak();

   void deleteCylinderrecbakByStationCode(String stationCode);

   XkCylinderrecbak findcClinderRecbak(@Param("stationCode") String stationCode, @Param("barcode") String barcode);

   Date getMaxInternalTime();

   Date getMaxInternalTimeByDid(Integer did);

   Date getMaxInternalTimeByStationCode(String stationCode);

   void deleteCylinderrecbakByDid(Integer did);
}
