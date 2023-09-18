package com.thirdinf;

import com.model.tbldata.entity.Tbldata;
import org.springframework.context.ApplicationEvent;

public class MyEvent extends ApplicationEvent {
   private Tbldata t;

   public MyEvent(Object source, Tbldata t) {
      super(source);
      this.t = t;
   }

   public Tbldata getT() {
      return this.t;
   }

   public void setT(Tbldata t) {
      this.t = t;
   }
}
