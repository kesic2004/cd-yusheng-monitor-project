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

   public void deleteCylinderrecbak() {
      super.baseMapper.deleteCylinderrec();
   }

   public void deleteCylinderrecbakByStationCode(String stationCode) {
      super.baseMapper.deleteCylinderrecByStationCode(stationCode);
   }

   public XkCylinderrecbak findcClinderRecbak(String stationCode, String barcode) {
      return super.baseMapper.findcClinderRec(stationCode, barcode);
   }

   public Date getMaxInternalTime() {
      return super.baseMapper.getMaxInternalTime();
   }

   public Date getMaxInternalTimeByDid(Integer did) {
      return super.baseMapper.getMaxInternalTimeByDid(did);
   }

   public Date getMaxInternalTimeByStationCode(String stationCode) {
      return super.baseMapper.getMaxInternalTimeByStationCode(stationCode);
   }

   public void deleteCylinderrecbakByDid(Integer did) {
      super.baseMapper.deleteCylinderrecByDid(did);
   }
}
