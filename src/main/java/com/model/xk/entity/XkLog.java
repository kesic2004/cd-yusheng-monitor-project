package com.model.xk.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;

@TableName("xk_log")
public class XkLog extends Model<XkLog> {
   private static final long serialVersionUID = 1L;
   @TableId(
      value = "id",
      type = IdType.AUTO
   )
   private Integer id;
   private String tag;
   private String detail;
   private Date rq;

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getTag() {
      return this.tag;
   }

   public void setTag(String tag) {
      this.tag = tag;
   }

   public String getDetail() {
      return this.detail;
   }

   public void setDetail(String detail) {
      this.detail = detail;
   }

   public Date getRq() {
      return this.rq;
   }

   public void setRq(Date rq) {
      this.rq = rq;
   }

   protected Serializable pkVal() {
      return this.id;
   }

   public String toString() {
      return "XkLog{id=" + this.id + ", tag=" + this.tag + ", detail=" + this.detail + ", rq=" + this.rq + "}";
   }
}
