package com.model.tbldata.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

@TableName("tbldata")
public class TbldataRequltA  extends Model<TbldataRequltA> {
    private static final long    serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO
    )
    private              Integer id;
    private              Integer seq;
    private              Integer provincecode; /* 省编码 */
    private              Integer citycode; /* 市编码 */
    private              Integer stationcode; /* 站点编码 */
    private Integer addresscode; /* 地址码 */
    private Integer command; /* 命令字 */
    private Integer nextnumber; /* 后续数 */
    private Integer cardnum1;
    private Integer cardnum2;
    private Integer cardnum3;
    private Integer cardnum4;
    private Integer stamp;
    private Integer endweight;
    private Integer grossweight;
    private Integer settingweight;
    private Integer clientid;
    private Integer operatorid;
    private Integer gxcode;
    private Integer fscode;
    private              Integer frameid;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private              Date    curtime;
    private              Integer usedtime;
    private Integer ticket;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date recordtime;
    private Integer machineno;
    private String qrcode;

    public Integer getId() {
        return id;
    }

    public Integer getSeq() {
        return seq;
    }

    public Integer getProvincecode() {
        return provincecode;
    }

    public Integer getCitycode() {
        return citycode;
    }

    public Integer getStationcode() {
        return stationcode;
    }

    public Integer getAddresscode() {
        return addresscode;
    }

    public Integer getCommand() {
        return command;
    }

    public Integer getNextnumber() {
        return nextnumber;
    }

    public Integer getCardnum1() {
        return cardnum1;
    }

    public Integer getCardnum2() {
        return cardnum2;
    }

    public Integer getCardnum3() {
        return cardnum3;
    }

    public Integer getCardnum4() {
        return cardnum4;
    }

    public Integer getStamp() {
        return stamp;
    }

    public Integer getEndweight() {
        return endweight;
    }

    public Integer getGrossweight() {
        return grossweight;
    }

    public Integer getSettingweight() {
        return settingweight;
    }

    public Integer getClientid() {
        return clientid;
    }

    public Integer getOperatorid() {
        return operatorid;
    }

    public Integer getGxcode() {
        return gxcode;
    }

    public Integer getFscode() {
        return fscode;
    }

    public Integer getFrameid() {
        return frameid;
    }

    public Date getCurtime() {
        return curtime;
    }

    public Integer getUsedtime() {
        return usedtime;
    }

    public Integer getTicket() {
        return ticket;
    }

    public Date getRecordtime() {
        return recordtime;
    }

    public Integer getMachineno() {
        return machineno;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public void setProvincecode(Integer provincecode) {
        this.provincecode = provincecode;
    }

    public void setCitycode(Integer citycode) {
        this.citycode = citycode;
    }

    public void setStationcode(Integer stationcode) {
        this.stationcode = stationcode;
    }

    public void setAddresscode(Integer addresscode) {
        this.addresscode = addresscode;
    }

    public void setCommand(Integer command) {
        this.command = command;
    }

    public void setNextnumber(Integer nextnumber) {
        this.nextnumber = nextnumber;
    }

    public void setCardnum1(Integer cardnum1) {
        this.cardnum1 = cardnum1;
    }

    public void setCardnum2(Integer cardnum2) {
        this.cardnum2 = cardnum2;
    }

    public void setCardnum3(Integer cardnum3) {
        this.cardnum3 = cardnum3;
    }

    public void setCardnum4(Integer cardnum4) {
        this.cardnum4 = cardnum4;
    }

    public void setStamp(Integer stamp) {
        this.stamp = stamp;
    }

    public void setEndweight(Integer endweight) {
        this.endweight = endweight;
    }

    public void setGrossweight(Integer grossweight) {
        this.grossweight = grossweight;
    }

    public void setSettingweight(Integer settingweight) {
        this.settingweight = settingweight;
    }

    public void setClientid(Integer clientid) {
        this.clientid = clientid;
    }

    public void setOperatorid(Integer operatorid) {
        this.operatorid = operatorid;
    }

    public void setGxcode(Integer gxcode) {
        this.gxcode = gxcode;
    }

    public void setFscode(Integer fscode) {
        this.fscode = fscode;
    }

    public void setFrameid(Integer frameid) {
        this.frameid = frameid;
    }

    public void setCurtime(Date curtime) {
        this.curtime = curtime;
    }

    public void setUsedtime(Integer usedtime) {
        this.usedtime = usedtime;
    }

    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }

    public void setRecordtime(Date recordtime) {
        this.recordtime = recordtime;
    }

    public void setMachineno(Integer machineno) {
        this.machineno = machineno;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }
}
