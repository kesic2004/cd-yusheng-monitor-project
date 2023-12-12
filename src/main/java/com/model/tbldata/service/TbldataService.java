package com.model.tbldata.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.model.authorize.entity.Login;
import com.model.city.entity.City;
import com.model.province.entity.Province;
import com.model.station.entity.Station;
import com.model.tbldata.entity.Tbldata;
import com.model.tbldata.entity.TbldataQueryA;

import java.util.Date;

public interface TbldataService extends IService<Tbldata> {
   int queryCountByDateTime(String starttime, String endtime);

   int insertData(Tbldata tbldata);

   /**
    * 根据省份编号, 地市州编号, 气站编号, 机器编号, 操作时间, 当前帧序号确认该条记录在数据库中的表tbldata中是否存在
    * @param provincecode 省份编号
    * @param citycode     地市州编号
    * @param stationcode  气站编号
    * @param machineno    机器编号
    * @param curTime      操作时间
    * @param remoteTime   下位机时间
    * @param frameid      当前帧序号
    * @return true 不存在, false 存在
    */
   boolean dataIsNotExists(Integer provincecode, Integer citycode, Integer stationcode, Integer machineno, Date curTime, String remoteTime, Integer frameid);

   /**
    * 用于查询灌装记录
    * @param provinces 省份
    * @param cities     地市州
    * @param stations  气站
    * @param login 和户登录信息
    * @param query 表单
    * @param pageObject 分页信息
    * @return 根据表单和分页信息的灌装记录
    */
   JSONObject queryByPage(Province[] provinces, City[] cities, Station[] stations, Login login, TbldataQueryA query, Page pageObject);
}
