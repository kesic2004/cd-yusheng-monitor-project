package com.model.xk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.xk.entity.XkCylinderrec;
import com.model.xk.mapper.XkCylinderrecMapper;
import com.model.xk.service.XkCylinderrecService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XkCylinderrecServiceImpl extends ServiceImpl<XkCylinderrecMapper, XkCylinderrec> implements XkCylinderrecService {
   @Autowired
   XkCylinderrecMapper xkCylinderrecMapper;

   public void deleteCylinderrec() {
      this.xkCylinderrecMapper.deleteCylinderrec();
   }

   public void deleteCylinderrecByStationCode(String stationCode) {
      this.xkCylinderrecMapper.deleteCylinderrecByStationCode(stationCode);
   }

   public XkCylinderrec findcClinderRecEx(String stationCode, String barcode) {
      return this.xkCylinderrecMapper.findcClinderRecEx(stationCode, barcode);
   }

   public XkCylinderrec findcClinderRec(String barcode) {
      return this.xkCylinderrecMapper.findcClinderRec(barcode);
   }

   public Date getMaxInternalTime() {
      return this.xkCylinderrecMapper.getMaxInternalTime();
   }

   public Date getMaxInternalTimeByDid(Integer did) {
      return this.xkCylinderrecMapper.getMaxInternalTimeByDid(did);
   }

   public Date getMaxInternalTimeByStationCode(String stationCode) {
      return this.xkCylinderrecMapper.getMaxInternalTimeByStationCode(stationCode);
   }

   public void deleteCylinderrecByDid(Integer did) {
      this.xkCylinderrecMapper.deleteCylinderrecByDid(did);
   }
}
