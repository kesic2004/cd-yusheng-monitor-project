package com.model.tbltoken.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.model.tbltoken.entity.Tbltoken;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TbltokenMapper extends BaseMapper<Tbltoken> {
   @Select({"SELECT id , devicecode, stationcode, token, recordtime FROM tbltoken  WHERE  devicecode = #{devicecode} Order By  recordtime DESC LIMIT 1 "})
   Tbltoken findTokenByDevicecode(@Param("devicecode") String devicecode);

   @Delete({"DELETE FROM tbltoken  WHERE  devicecode = #{devicecode} "})
   void delCurrentuser(@Param("devicecode") String devicecode);
}
