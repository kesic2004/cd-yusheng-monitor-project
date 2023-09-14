package com.model.device.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.device.entity.Device;
import com.model.device.entity.DeviceEx;
import com.model.device.mapper.DeviceMapper;
import com.model.device.service.DeviceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceService {
   @Autowired
   DeviceMapper deviceMapper;

   public Device findDevice(Integer addresscode, Integer province, Integer city, Integer station, Integer machineno) {
      return this.deviceMapper.findDevice(addresscode, province, city, station, machineno);
   }

   public Device findDeviceByClientIdAndAddress(Integer clientid, Integer addresscode, Integer province, Integer city, Integer station) {
      return this.deviceMapper.findDeviceByClientIdAndAddress(clientid, addresscode, province, city, station);
   }

   public List<DeviceEx> selectAll() {
      return this.deviceMapper.selectAll();
   }
}
