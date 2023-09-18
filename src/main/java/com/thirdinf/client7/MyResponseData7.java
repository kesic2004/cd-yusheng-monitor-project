package com.thirdinf.client7;

import java.util.List;

public class MyResponseData7 {
   private String uploadTime;
   private List<MyResponseData7.DenyList> denyList;

   public String toString() {
      return "MyResponseData7{uploadTime='" + this.uploadTime + '\'' + ", denyList=" + this.denyList + '}';
   }

   public void setUploadTime(String uploadTime) {
      this.uploadTime = uploadTime;
   }

   public String getUploadTime() {
      return this.uploadTime;
   }

   public void setDenyList(List<MyResponseData7.DenyList> denyList) {
      this.denyList = denyList;
   }

   public List<MyResponseData7.DenyList> getDenyList() {
      return this.denyList;
   }

   public class DenyList {
      private String id;
      private String error;
      private String reason;

      public void setId(String id) {
         this.id = id;
      }

      public String getId() {
         return this.id;
      }

      public void setError(String error) {
         this.error = error;
      }

      public String getError() {
         return this.error;
      }

      public void setReason(String reason) {
         this.reason = reason;
      }

      public String getReason() {
         return this.reason;
      }

      public String toString() {
         return "DenyList{id='" + this.id + '\'' + ", error='" + this.error + '\'' + ", reason='" + this.reason + '\'' + '}';
      }
   }
}
