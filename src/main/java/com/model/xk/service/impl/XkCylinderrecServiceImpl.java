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

   public void deleteCylinderrec() {
      super.baseMapper.deleteCylinderrec();
   }

   public void deleteCylinderrecByStationCode(String stationCode) {
      super.baseMapper.deleteCylinderrecByStationCode(stationCode);
   }

   public XkCylinderrec findcClinderRecEx(String stationCode, String barcode) {
      return super.baseMapper.findcClinderRecEx(stationCode, barcode);
   }

   public XkCylinderrec findcClinderRec(String barcode) {
      return super.baseMapper.findcClinderRec(barcode);
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

   public void deleteCylinderrecByDid(Integer did) {
      super.baseMapper.deleteCylinderrecByDid(did);
   }
}
