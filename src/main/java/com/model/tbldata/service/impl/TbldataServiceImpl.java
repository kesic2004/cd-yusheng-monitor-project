package com.model.tbldata.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.authorize.entity.Login;
import com.model.city.entity.City;
import com.model.province.entity.Province;
import com.model.station.entity.Station;
import com.model.tbldata.entity.Tbldata;
import com.model.tbldata.entity.TbldataQueryA;
import com.model.tbldata.entity.TbldataRequltA;
import com.model.tbldata.entity.TbldataSumWeightA;
import com.model.tbldata.mapper.TbldataMapper;
import com.model.tbldata.service.TbldataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

@Service
public class TbldataServiceImpl extends ServiceImpl<TbldataMapper, Tbldata> implements TbldataService {

    private static final Consumer<TbldataRequltA> TBLDATA_REQULT_A_CONSUMER = new TbldataServiceImplTbldataRequltAConsumer();

    public int queryCountByDateTime(String starttime, String endtime) {
        return super.baseMapper.queryCountByDateTime(starttime, endtime);
    }

    public int insertData(Tbldata tbldata) {
        return super.baseMapper.insertData(tbldata);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public boolean dataIsNotExists(Integer provincecode, Integer citycode, Integer stationcode, Integer machineno, Date curTime, String remoteTime, Integer frameid) {
        /**
         * 对于相同帧序号的时间范围暂时定为当前自然日的范围内
         */
        final Date curTimeBegin = new Date(curTime.getYear(), curTime.getMonth(), curTime.getDate(), 0, 0, 0);
        final Date curTimeEnd   = new Date(curTimeBegin.getTime() + 86_400_000L);
        return super.baseMapper.countByProvincecodeCitycodeStationcodeMachinenoCurrenttimeFrameid(provincecode, citycode, stationcode, machineno, curTimeBegin, curTimeEnd, remoteTime, frameid) == 0;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public JSONObject queryByPage(Province[] provinces, City[] cities, Station[] stations, Login login, TbldataQueryA query, Page page) {
        final long       current = page.getCurrent();
        final long       size    = page.getSize();
        final JSONObject json    = new JSONObject();
        json.put("pageIndex", current);
        json.put("pageSize", size);
        json.put("hasPrevPages", current != 1);
        if (stations.length == 1) {
            final List<TbldataRequltA> list = super.baseMapper.queryByPageSingleStation(query, provinces[0].getCode(), cities[0].getCode(), stations[0].getCode(), size * (current - 1), size);
            final TbldataSumWeightA sum        = super.baseMapper.countByPageSingleStation(query, provinces[0].getCode(), cities[0].getCode(), stations[0].getCode());
            list.forEach(TBLDATA_REQULT_A_CONSUMER);
            long                    count      = sum.getMyCount();
            final long              totalPages = count / size + (count % size == 0L ? 0L : 1L);
            json.put("items", list); /* 不可查询时json.put("items", Collections.EMPTY_LIST); */
            json.put("totalCount", count); /* 不可查询时json.put("totalCount", 0); */
            json.put("endweightCount", sum.getEndweightCount()); /* 不可查询时 json.put("endweightCount", 0); */
            json.put("grossweightCount", sum.getGrossweightCount()); /* 不可查询时 json.put("grossweightCount", 0); */
            json.put("settingweightCount", sum.getSettingweightCount()); /* 不可查询时 json.put("settingweightCount", 0); */
            json.put("totalPages", totalPages); /* 不可查询时json.put("totalPages", 0); */
            json.put("hasNextPages", current < totalPages); /* 不可查询时json.put("hasNextPages", Boolean.FALSE); */
        }
        return json;
    }

    private static Integer[] getStationCodes(Station[] station, Integer stationcode) {
        if (stationcode == null) {
            Integer[] result = new Integer[station.length];
            for (int i = 0; i < station.length; ++i) {
                result[i] = station[i].getCode();
            }
            return result;
        } else {
            for (int i = 0; i < station.length; ++i) {
                final Integer code = station[i].getCode();
                if (code.intValue() == stationcode.intValue()) {
                    return new Integer[]{code};
                }
            }
            return null;
        }
    }
}

class TbldataServiceImplTbldataRequltAConsumer implements Consumer<TbldataRequltA> {
    @Override
    public void accept(TbldataRequltA item) {
        /*
         * 处理灌型码
         */
        final Integer gxcode = item.getGxcode();
        if (gxcode != null) {
            item.setGxcode(new Integer((gxcode.intValue() >> 4) & 0xF)); // 协议规定低四位目前未用
        }
    }
}
