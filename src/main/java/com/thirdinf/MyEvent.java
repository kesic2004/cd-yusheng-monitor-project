package com.thirdinf;

import com.myserver.DataPackage;
import org.springframework.context.ApplicationEvent;

public class MyEvent extends ApplicationEvent {
   private DataPackage t;

   public MyEvent(Object source, DataPackage t) {
      super(source);
      this.t = t;
   }

   public DataPackage getT() {
      return this.t;
   }

   public void setT(DataPackage t) {
      this.t = t;
   }
}
