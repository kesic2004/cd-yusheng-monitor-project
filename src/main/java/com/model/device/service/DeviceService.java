package com.model.device.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.device.entity.Device;

public interface DeviceService extends IService<Device> {
   Device findDevice(Integer addresscode, Integer province, Integer city, Integer station, Integer machineno);

   Device findDeviceByClientIdAndAddress(Integer clientid, Integer addresscode, Integer province, Integer city, Integer station);
}
