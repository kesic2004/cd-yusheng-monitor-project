package com.model.device.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.device.entity.Device;
import com.model.device.mapper.DeviceMapper;
import com.model.device.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceService {
    @Autowired
    DeviceMapper deviceMapper;

    public Device findDevice(Integer addresscode, Integer province, Integer city, Integer station, Integer machineno) {
        /*
         * SELECT
         *     d.id as id,
         *     d.sid as sid,
         *     d.clientid as clientid,
         *     d.name as name,
         *     d.addresscode as addresscode,
         *     d.detail as detail,
         *     d.state as state,
         *     d.machineno as machineno,
         *     d.qrtype as qrtype
         * FROM
         *     device d,
         *     province p,
         *     city c,
         *     station s
         * WHERE
         *     p.id = c.pid
         *     And c.id = s.cid
         *     And s.id = d.sid
         *     And p.code = #{province}
         *     And c.code = #{city}
         *     And s.code = #{station}
         *     And d.addresscode = #{addresscode}
         *     And d.machineno = #{machineno}
         * LIMIT 1
         */
         return this.deviceMapper.findDevice(addresscode, province, city, station, machineno);
    }

    public Device findDeviceByClientIdAndAddress(Integer clientid, Integer addresscode, Integer province, Integer city, Integer station) {
        /*
         * SELECT
         *     d.id as id,
         *     d.sid as sid,
         *     d.clientid as clientid,
         *     d.name as name,
         *     d.addresscode as addresscode,
         *     d.detail as detail,
         *     d.state as state,
         *     d.machineno as machineno,
         *     d.qrtype as qrtype
         * FROM
         *     device d,
         *     province p,
         *     city c,
         *     station s
         * WHERE
         *     p.id = c.pid
         *     And c.id = s.cid
         *     And s.id = d.sid
         *     And p.code = #{province}
         *     And c.code = #{city}
         *     And s.code = #{station}
         *     And d.addresscode = #{addresscode}
         *     AND d.clientid = #{clientid}
         * LIMIT 1
         */
         return this.deviceMapper.findDeviceByClientIdAndAddress(clientid, addresscode, province, city, station);
    }
}
