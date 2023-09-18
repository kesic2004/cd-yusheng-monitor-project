package com.model.xk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.xk.entity.XkCylinderrecbak;
import com.model.xk.mapper.XkCylinderrecbakMapper;
import com.model.xk.service.XkCylinderrecbakService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XkCylinderrecbakServiceImpl extends ServiceImpl<XkCylinderrecbakMapper, XkCylinderrecbak> implements XkCylinderrecbakService {
   @Autowired
   XkCylinderrecbakMapper xkCylinderrecbakMapper;

   public void deleteCylinderrecbak() {
      this.xkCylinderrecbakMapper.deleteCylinderrec();
   }

   public void deleteCylinderrecbakByStationCode(String stationCode) {
      this.xkCylinderrecbakMapper.deleteCylinderrecByStationCode(stationCode);
   }

   public XkCylinderrecbak findcClinderRecbak(String stationCode, String barcode) {
      return this.xkCylinderrecbakMapper.findcClinderRec(stationCode, barcode);
   }

   public Date getMaxInternalTime() {
      return this.xkCylinderrecbakMapper.getMaxInternalTime();
   }

   public Date getMaxInternalTimeByDid(Integer did) {
      return this.xkCylinderrecbakMapper.getMaxInternalTimeByDid(did);
   }

   public Date getMaxInternalTimeByStationCode(String stationCode) {
      return this.xkCylinderrecbakMapper.getMaxInternalTimeByStationCode(stationCode);
   }

   public void deleteCylinderrecbakByDid(Integer did) {
      this.xkCylinderrecbakMapper.deleteCylinderrecByDid(did);
   }
}
