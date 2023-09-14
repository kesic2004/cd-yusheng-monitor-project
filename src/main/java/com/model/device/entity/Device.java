package com.model.device.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

public class Device extends Model<Device> {
   private static final long serialVersionUID = 1L;
   @TableId(
      value = "id",
      type = IdType.AUTO
   )
   private Integer id;
   private Integer sid;
   private Integer clientid;
   private String name;
   private Integer addresscode;
   private String detail;
   private Integer state;
   private Integer machineno;
   private Integer chan1;
   private Integer chan2;
   private Integer chan3;
   private Integer chan4;
   private Integer chan5;
   private Integer chan6;
   private Integer chan7;
   private Integer chan8;
   private Integer chan9;
   private Integer chan10;
   private Integer chan11;
   private Integer chan12;
   private Integer chan13;
   private Integer chan14;
   private Integer chan15;
   private Integer chan16;
   private Float key1;
   private Float key2;
   private Float key3;
   private Float key4;
   private Float key5;
   private Float key6;
   private Float key7;
   private Float key8;
   private Float key9;
   private Float key10;
   private Float key11;
   private Float key12;
   private Float key13;
   private Float key14;
   private Float key15;
   private Float key16;
   private Float zero1;
   private Float zero2;
   private Float zero3;
   private Float zero4;
   private Float zero5;
   private Float zero6;
   private Float zero7;
   private Float zero8;
   private Float zero9;
   private Float zero10;
   private Float zero11;
   private Float zero12;
   private Float zero13;
   private Float zero14;
   private Float zero15;
   private Float zero16;
   private Float lx1;
   private Float lx2;
   private Float lx3;
   private Float lx4;
   private Float lx5;
   private Float lx6;
   private Float lx7;
   private Float lx8;
   private Float lx9;
   private Float lx10;
   private Float lx11;
   private Float lx12;
   private Float lx13;
   private Float lx14;
   private Float lx15;
   private Float lx16;
   private String zdbh;

   public String getZdbh() {
      return this.zdbh;
   }

   public void setZdbh(String zdbh) {
      this.zdbh = zdbh;
   }

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Integer getCid() {
      return this.sid;
   }

   public void setCid(Integer cid) {
      this.sid = cid;
   }

   public Integer getClientid() {
      return this.clientid;
   }

   public void setClientid(Integer clientmonitorid) {
      this.clientid = clientmonitorid;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Integer getAddresscode() {
      return this.addresscode;
   }

   public void setAddresscode(Integer addresscode) {
      this.addresscode = addresscode;
   }

   public String getDetail() {
      return this.detail;
   }

   public void setDetail(String detail) {
      this.detail = detail;
   }

   public Integer getState() {
      return this.state;
   }

   public void setState(Integer state) {
      this.state = state;
   }

   protected Serializable pkVal() {
      return this.id;
   }

   public Integer getChan1() {
      return this.chan1;
   }

   public void setChan1(Integer chan1) {
      this.chan1 = chan1;
   }

   public Integer getChan2() {
      return this.chan2;
   }

   public void setChan2(Integer chan2) {
      this.chan2 = chan2;
   }

   public Integer getChan3() {
      return this.chan3;
   }

   public void setChan3(Integer chan3) {
      this.chan3 = chan3;
   }

   public Integer getChan4() {
      return this.chan4;
   }

   public void setChan4(Integer chan4) {
      this.chan4 = chan4;
   }

   public Integer getChan5() {
      return this.chan5;
   }

   public void setChan5(Integer chan5) {
      this.chan5 = chan5;
   }

   public Integer getChan6() {
      return this.chan6;
   }

   public void setChan6(Integer chan6) {
      this.chan6 = chan6;
   }

   public Integer getChan7() {
      return this.chan7;
   }

   public void setChan7(Integer chan7) {
      this.chan7 = chan7;
   }

   public Integer getChan8() {
      return this.chan8;
   }

   public void setChan8(Integer chan8) {
      this.chan8 = chan8;
   }

   public Integer getChan9() {
      return this.chan9;
   }

   public void setChan9(Integer chan9) {
      this.chan9 = chan9;
   }

   public Integer getChan10() {
      return this.chan10;
   }

   public void setChan10(Integer chan10) {
      this.chan10 = chan10;
   }

   public Integer getChan11() {
      return this.chan11;
   }

   public void setChan11(Integer chan11) {
      this.chan11 = chan11;
   }

   public Integer getChan12() {
      return this.chan12;
   }

   public void setChan12(Integer chan12) {
      this.chan12 = chan12;
   }

   public Integer getChan13() {
      return this.chan13;
   }

   public void setChan13(Integer chan13) {
      this.chan13 = chan13;
   }

   public Integer getChan14() {
      return this.chan14;
   }

   public void setChan14(Integer chan14) {
      this.chan14 = chan14;
   }

   public Integer getChan15() {
      return this.chan15;
   }

   public void setChan15(Integer chan15) {
      this.chan15 = chan15;
   }

   public Integer getChan16() {
      return this.chan16;
   }

   public void setChan16(Integer chan16) {
      this.chan16 = chan16;
   }

   public Float getKey1() {
      return this.key1;
   }

   public void setKey1(Float key1) {
      this.key1 = key1;
   }

   public Float getKey2() {
      return this.key2;
   }

   public void setKey2(Float key2) {
      this.key2 = key2;
   }

   public Float getKey3() {
      return this.key3;
   }

   public void setKey3(Float key3) {
      this.key3 = key3;
   }

   public Float getKey4() {
      return this.key4;
   }

   public void setKey4(Float key4) {
      this.key4 = key4;
   }

   public Float getKey5() {
      return this.key5;
   }

   public void setKey5(Float key5) {
      this.key5 = key5;
   }

   public Float getKey6() {
      return this.key6;
   }

   public void setKey6(Float key6) {
      this.key6 = key6;
   }

   public Float getKey7() {
      return this.key7;
   }

   public void setKey7(Float key7) {
      this.key7 = key7;
   }

   public Float getKey8() {
      return this.key8;
   }

   public void setKey8(Float key8) {
      this.key8 = key8;
   }

   public Float getKey9() {
      return this.key9;
   }

   public void setKey9(Float key9) {
      this.key9 = key9;
   }

   public Float getKey10() {
      return this.key10;
   }

   public void setKey10(Float key10) {
      this.key10 = key10;
   }

   public Float getKey11() {
      return this.key11;
   }

   public void setKey11(Float key11) {
      this.key11 = key11;
   }

   public Float getKey12() {
      return this.key12;
   }

   public void setKey12(Float key12) {
      this.key12 = key12;
   }

   public Float getKey13() {
      return this.key13;
   }

   public void setKey13(Float key13) {
      this.key13 = key13;
   }

   public Float getKey14() {
      return this.key14;
   }

   public void setKey14(Float key14) {
      this.key14 = key14;
   }

   public Float getKey15() {
      return this.key15;
   }

   public void setKey15(Float key15) {
      this.key15 = key15;
   }

   public Float getKey16() {
      return this.key16;
   }

   public void setKey16(Float key16) {
      this.key16 = key16;
   }

   public Float getZero1() {
      return this.zero1;
   }

   public void setZero1(Float zero1) {
      this.zero1 = zero1;
   }

   public Float getZero2() {
      return this.zero2;
   }

   public void setZero2(Float zero2) {
      this.zero2 = zero2;
   }

   public Float getZero3() {
      return this.zero3;
   }

   public void setZero3(Float zero3) {
      this.zero3 = zero3;
   }

   public Float getZero4() {
      return this.zero4;
   }

   public void setZero4(Float zero4) {
      this.zero4 = zero4;
   }

   public Float getZero5() {
      return this.zero5;
   }

   public void setZero5(Float zero5) {
      this.zero5 = zero5;
   }

   public Float getZero6() {
      return this.zero6;
   }

   public void setZero6(Float zero6) {
      this.zero6 = zero6;
   }

   public Float getZero7() {
      return this.zero7;
   }

   public void setZero7(Float zero7) {
      this.zero7 = zero7;
   }

   public Float getZero8() {
      return this.zero8;
   }

   public void setZero8(Float zero8) {
      this.zero8 = zero8;
   }

   public Float getZero9() {
      return this.zero9;
   }

   public void setZero9(Float zero9) {
      this.zero9 = zero9;
   }

   public Float getZero10() {
      return this.zero10;
   }

   public void setZero10(Float zero10) {
      this.zero10 = zero10;
   }

   public Float getZero11() {
      return this.zero11;
   }

   public void setZero11(Float zero11) {
      this.zero11 = zero11;
   }

   public Float getZero12() {
      return this.zero12;
   }

   public void setZero12(Float zero12) {
      this.zero12 = zero12;
   }

   public Float getZero13() {
      return this.zero13;
   }

   public void setZero13(Float zero13) {
      this.zero13 = zero13;
   }

   public Float getZero14() {
      return this.zero14;
   }

   public void setZero14(Float zero14) {
      this.zero14 = zero14;
   }

   public Float getZero15() {
      return this.zero15;
   }

   public void setZero15(Float zero15) {
      this.zero15 = zero15;
   }

   public Float getZero16() {
      return this.zero16;
   }

   public void setZero16(Float zero16) {
      this.zero16 = zero16;
   }

   public Float getLx1() {
      return this.lx1;
   }

   public void setLx1(Float lx1) {
      this.lx1 = lx1;
   }

   public Float getLx2() {
      return this.lx2;
   }

   public void setLx2(Float lx2) {
      this.lx2 = lx2;
   }

   public Float getLx3() {
      return this.lx3;
   }

   public void setLx3(Float lx3) {
      this.lx3 = lx3;
   }

   public Float getLx4() {
      return this.lx4;
   }

   public void setLx4(Float lx4) {
      this.lx4 = lx4;
   }

   public Float getLx5() {
      return this.lx5;
   }

   public void setLx5(Float lx5) {
      this.lx5 = lx5;
   }

   public Float getLx6() {
      return this.lx6;
   }

   public void setLx6(Float lx6) {
      this.lx6 = lx6;
   }

   public Float getLx7() {
      return this.lx7;
   }

   public void setLx7(Float lx7) {
      this.lx7 = lx7;
   }

   public Float getLx8() {
      return this.lx8;
   }

   public void setLx8(Float lx8) {
      this.lx8 = lx8;
   }

   public Float getLx9() {
      return this.lx9;
   }

   public void setLx9(Float lx9) {
      this.lx9 = lx9;
   }

   public Float getLx10() {
      return this.lx10;
   }

   public void setLx10(Float lx10) {
      this.lx10 = lx10;
   }

   public Float getLx11() {
      return this.lx11;
   }

   public void setLx11(Float lx11) {
      this.lx11 = lx11;
   }

   public Float getLx12() {
      return this.lx12;
   }

   public void setLx12(Float lx12) {
      this.lx12 = lx12;
   }

   public Float getLx13() {
      return this.lx13;
   }

   public void setLx13(Float lx13) {
      this.lx13 = lx13;
   }

   public Float getLx14() {
      return this.lx14;
   }

   public void setLx14(Float lx14) {
      this.lx14 = lx14;
   }

   public Float getLx15() {
      return this.lx15;
   }

   public void setLx15(Float lx15) {
      this.lx15 = lx15;
   }

   public Float getLx16() {
      return this.lx16;
   }

   public void setLx16(Float lx16) {
      this.lx16 = lx16;
   }

   public String toString() {
      return "Device{id=" + this.id + ", sid=" + this.sid + ", clientid=" + this.clientid + ", name='" + this.name + '\'' + ", addresscode=" + this.addresscode + ", detail='" + this.detail + '\'' + ", state=" + this.state + ", machineno=" + this.machineno + ", chan1=" + this.chan1 + ", chan2=" + this.chan2 + ", chan3=" + this.chan3 + ", chan4=" + this.chan4 + ", chan5=" + this.chan5 + ", chan6=" + this.chan6 + ", chan7=" + this.chan7 + ", chan8=" + this.chan8 + ", chan9=" + this.chan9 + ", chan10=" + this.chan10 + ", chan11=" + this.chan11 + ", chan12=" + this.chan12 + ", chan13=" + this.chan13 + ", chan14=" + this.chan14 + ", chan15=" + this.chan15 + ", chan16=" + this.chan16 + ", key1=" + this.key1 + ", key2=" + this.key2 + ", key3=" + this.key3 + ", key4=" + this.key4 + ", key5=" + this.key5 + ", key6=" + this.key6 + ", key7=" + this.key7 + ", key8=" + this.key8 + ", key9=" + this.key9 + ", key10=" + this.key10 + ", key11=" + this.key11 + ", key12=" + this.key12 + ", key13=" + this.key13 + ", key14=" + this.key14 + ", key15=" + this.key15 + ", key16=" + this.key16 + ", zero1=" + this.zero1 + ", zero2=" + this.zero2 + ", zero3=" + this.zero3 + ", zero4=" + this.zero4 + ", zero5=" + this.zero5 + ", zero6=" + this.zero6 + ", zero7=" + this.zero7 + ", zero8=" + this.zero8 + ", zero9=" + this.zero9 + ", zero10=" + this.zero10 + ", zero11=" + this.zero11 + ", zero12=" + this.zero12 + ", zero13=" + this.zero13 + ", zero14=" + this.zero14 + ", zero15=" + this.zero15 + ", zero16=" + this.zero16 + ", lx1=" + this.lx1 + ", lx2=" + this.lx2 + ", lx3=" + this.lx3 + ", lx4=" + this.lx4 + ", lx5=" + this.lx5 + ", lx6=" + this.lx6 + ", lx7=" + this.lx7 + ", lx8=" + this.lx8 + ", lx9=" + this.lx9 + ", lx10=" + this.lx10 + ", lx11=" + this.lx11 + ", lx12=" + this.lx12 + ", lx13=" + this.lx13 + ", lx14=" + this.lx14 + ", lx15=" + this.lx15 + ", lx16=" + this.lx16 + ", zdbh='" + this.zdbh + '\'' + '}';
   }

   public Integer getMachineno() {
      return this.machineno;
   }

   public void setMachineno(Integer machineno) {
      this.machineno = machineno;
   }
}
