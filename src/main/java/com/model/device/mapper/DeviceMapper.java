package com.model.device.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.model.device.entity.Device;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface DeviceMapper extends BaseMapper<Device> {
   @Select({"SELECT d.id as id, d.sid as sid, d.clientid as clientid, d.name as name, d.addresscode as addresscode, d.detail as detail, d.state as state, d.machineno as machineno, d.qrtype as qrtype FROM device d, province p, city c, station s  WHERE  p.id = c.pid And c.id = s.cid And s.id = d.sid And p.code = #{province} And c.code = #{city} And s.code = #{station} And d.addresscode = #{addresscode} And d.machineno = #{machineno} LIMIT 1 "})
   Device findDevice(@Param("addresscode") Integer addresscode, @Param("province") Integer province, @Param("city") Integer city, @Param("station") Integer station, @Param("machineno") Integer machineno);

   @Select({"SELECT d.id as id, d.sid as sid, d.clientid as clientid, d.name as name, d.addresscode as addresscode, d.detail as detail, d.state as state, d.machineno as machineno, d.qrtype as qrtype FROM device d, province p, city c, station s  WHERE  p.id = c.pid And c.id = s.cid And s.id = d.sid And p.code = #{province} And c.code = #{city} And s.code = #{station} And d.addresscode = #{addresscode} AND d.clientid = #{clientid} LIMIT 1 "})
   Device findDeviceByClientIdAndAddress(@Param("clientid") Integer clientid, @Param("addresscode") Integer addresscode, @Param("province") Integer province, @Param("city") Integer city, @Param("station") Integer station);
}
