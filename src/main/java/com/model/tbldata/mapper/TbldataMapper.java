package com.model.tbldata.mapper;

import com.model.city.entity.City;
import com.model.province.entity.Province;
import com.model.station.entity.Station;
import com.model.tbldata.entity.Tbldata;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.model.tbldata.entity.TbldataQueryA;
import com.model.tbldata.entity.TbldataRequltA;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface TbldataMapper extends BaseMapper<Tbldata> {
   int queryCountByDateTime(@Param("starttime") String starttime, @Param("endtime") String endtime);

   int insertData(Tbldata tbldata);


   /**
    * 根据省份编号, 地市州编号, 气站编号, 机器编号, 操作时间, 当前帧序号纺计该条记录在数据库中的表tbldata中的条数
    * @param provincecode 省份编号
    * @param citycode     地市州编号
    * @param stationcode  气站编号
    * @param machineno    机器编号
    * @param curTimeBegin 操作时间起
    * @param curTimeEnd   操作时间起
    * @param remoteTime   下位机时间
    * @param frameid      当前帧序号
    * @return 条数
    */
   @Select("select count(id) from tbldata where provincecode = #{provincecode} and citycode = #{citycode} and stationcode = #{stationcode} and machineno = #{machineno} and (curTime between #{curTimeBegin} and #{curTimeEnd}) and remote_time = #{remoteTime} and frameid = #{frameid}")
   int countByProvincecodeCitycodeStationcodeMachinenoCurrenttimeFrameid(
           @Param("provincecode") Integer provincecode,
           @Param("citycode")Integer citycode,
           @Param("stationcode")Integer stationcode,
           @Param("machineno")Integer machineno,
           @Param("curTimeBegin") Date curTimeBegin,
           @Param("curTimeEnd") Date curTimeEnd,
           @Param("remoteTime") String remoteTime,
           @Param("frameid") Integer frameid);

   /**
    * 分页查询
    * @param query 查询参数
    * @param begin 数据条数起
    * @param offset 数据条数偏移量
    * @param sCode 站点编号
    * @return 查询到的数据行
    */
   List<TbldataRequltA> queryByPageSingleStation(@Param("query") TbldataQueryA query, @Param("pCode") Integer pCode, @Param("cCode") Integer cCode, @Param("sCode") Integer sCode, @Param("begin") long begin, @Param("offset") long offset);

   /**
    * 查询总记录条数
    * @param query 查询参数
    * @param sCode 站点编号
    * @return 总记录条数
    */
   Integer countByPageSingleStation(@Param("query") TbldataQueryA query, @Param("pCode") Integer pCode, @Param("cCode") Integer cCode, @Param("sCode") Integer sCode);
}
