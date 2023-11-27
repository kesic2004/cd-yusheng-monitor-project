package com.model.tbldata.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
/*import com.model.authorize.entity.Login;*/
import com.model.authorize.entity.Register;
import com.model.authorize.entity.StationUtil;
/*import com.model.city.entity.City;*/
/*import com.model.clientuser.entity.Clientuser;*/
import com.model.clientuser.service.IClientuserService;
import com.model.businessInformation.service.IOperatorService;
/*import com.model.province.entity.Province;*/
import com.model.station.entity.Station;
import com.model.tbldata.entity.TbldataQueryA;
import com.model.tbldata.service.TbldataService;
import com.web.GasHandlerInterceptorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
/*import java.util.List;*/
import java.util.stream.Collectors;

@RestController
@RequestMapping({"/tbldata/tbldata"})
public class TbldataController {

    @Autowired
    private TbldataService tbldataService;

    @Autowired
    private IClientuserService clientuserService;

    @Autowired
    private IOperatorService operatorService;

    @RequestMapping(value = "/queryByPage", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryByPage(HttpSession session, @RequestParam("form") String form, @RequestParam("page") String page) {
        if (form == null || form.trim().length() == 0 || page == null || page.trim().length() == 0) {
            throw new NullPointerException();
        }
        final TbldataQueryA query      = JSONObject.parseObject(form, TbldataQueryA.class);
        final Page          pageObject = JSONObject.parseObject(page, Page.class);
        if (pageObject.getCurrent() < 1L || pageObject.getSize() < 1L) {
            throw new IllegalArgumentException();
        }
        query.processCurtimeExtent();
        final Register register = (Register) session.getAttribute(GasHandlerInterceptorAdapter.USUAL_THINGS);
        return tbldataService.queryByPage(
                register.getProvinces(),
                register.getCities(),
                register.getStations(),
                register.getLogin(),
                query,
                pageObject
        );
    }

    @RequestMapping(value = "/queryPageElSelect", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryPageElSelect(HttpSession session) {
        final Register   register      = (Register) session.getAttribute(GasHandlerInterceptorAdapter.USUAL_THINGS);
        final Station[]    stations       = register.getStations();
        final JSONObject result        = new JSONObject();
        final JSONObject[] stationObject = new JSONObject[stations.length];
        Integer[] stationIds = StationUtil.getStationIds(stations, stationObject, (item, json) -> {
            json.put("value", item.getCode());
            json.put("label", item.getName());
        }, () -> new JSONObject());
        result.put("station", stationObject);
        result.put("clientUsers", clientuserService.queryByStationIdsAndParentId(stationIds, 0).stream().map(item -> {
            JSONObject clientUserObject = new JSONObject();
            clientUserObject.put("value", item.getId());
            clientUserObject.put("label", item.getClientname());
            return clientUserObject;
        }).collect(Collectors.toList()));
        result.put("operators", operatorService.queryByStaionIds(stationIds).stream().map(item -> {
            final JSONObject operatorObject = new JSONObject();
            operatorObject.put("value", item.getCode());
            operatorObject.put("label", item.getName());
            return operatorObject;
        }).collect(Collectors.toList()));
        return result;
    }

}
