package com.model.tbldata.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;

public class TbldataQueryA extends Model<TbldataQueryA> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 站点编号
     */
    private Integer  selStationcode;
    /**
     * 客户名
     */
    private Integer selClientid;
    /**
     * 操作员
     */
    private Integer selOperatorid;
    /**
     * 秤编号
     */
    private String  addresscode;
    /**
     * 罐装方式
     */
    private Integer fscodeType;
    /**
     * 罐型
     */
    private Integer gxCodeType;
    /**
     * 完成状态
     */
    private Integer command;
    /**
     * 时间范围起
     */
    private Date    curtimeForm;
    /**
     * 时间范围止
     */
    private Date    curtimeTo;
    /**
     * 设重
     */
    private Integer settingweight;
    /**
     * 条码
     */
    private String qrcode;

    public Integer getId() {
        return id;
    }

    public Integer getSelStationcode() {
        return selStationcode;
    }

    public Integer getSelClientid() {
        return selClientid;
    }

    public Integer getSelOperatorid() {
        return selOperatorid;
    }

    public String getAddresscode() {
        return addresscode;
    }

    public Integer getFscodeType() {
        return fscodeType;
    }

    public Integer getGxCodeType() {
        return gxCodeType;
    }

    public Integer getCommand() {
        return command;
    }

    public Date getCurtimeForm() {
        return curtimeForm;
    }

    public Date getCurtimeTo() {
        return curtimeTo;
    }

    public Integer getSettingweight() {
        return settingweight;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSelStationcode(Integer selStationcode) {
        this.selStationcode = selStationcode;
    }

    public void setSelClientid(Integer selClientid) {
        this.selClientid = selClientid;
    }

    public void setSelOperatorid(Integer selOperatorid) {
        this.selOperatorid = selOperatorid;
    }

    public void setAddresscode(String addresscode) {
        this.addresscode = processString(addresscode);
    }

    public void setFscodeType(Integer fscodeType) {
        this.fscodeType = fscodeType;
    }

    public void setGxCodeType(Integer gxCodeType) {
        this.gxCodeType = gxCodeType;
    }

    public void setCommand(Integer command) {
        this.command = command;
    }

    public void setCurtimeForm(Date curtimeForm) {
        this.curtimeForm = curtimeForm;
    }

    public void setCurtimeTo(Date curtimeTo) {
        this.curtimeTo = curtimeTo;
    }

    public void setSettingweight(Integer settingweight) {
        this.settingweight = settingweight;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = processString(qrcode);
    }

    private static String processString(String string) {
        return string == null ? null : (string.trim().length() == 0 ? null : string.trim());
    }

    public void processCurtimeExtent() {
        Date[] array = processLocalDateExtent(this.curtimeForm, this.curtimeTo);
        this.curtimeForm = array[0];
        this.curtimeTo = array[1];
    }
    private final static Date[] processLocalDateExtent(Date from, Date to) {
        switch ((to == null ? 0 : 2) + (from == null ? 0 : 1)) {
            case 0: /* 全传mull */
                return new Date[]{null, null};
            case 1: /* array[1]传null */
                return new Date[]{getFirst(from), null};
            case 2: /* array[0]传null */
                return new Date[]{null, getLast(to)};
            case 3: /* 均不为null */
                return (from.getTime() <= to.getTime()) ? new Date[]{getFirst(from), getLast(to)} : new Date[]{getFirst(to), getLast(from)};
            default:
                return new Date[]{null, null};
        }
    }

    private final static Date getFirst(Date date) {
        return new Date(date.getYear(), date.getMonth(), date.getDate(), 0, 0, 0);
    }

    private final static Date getLast(Date date) {
        Date      temp  = new Date(date.getYear(), date.getMonth(), date.getDate(), 0, 0, 0);
        return new Date(temp.getTime() + 86_399_999);
    }
}
