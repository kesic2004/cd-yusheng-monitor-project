package com.model.currentuser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.model.currentuser.entity.Currentuser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CurrentuserMapper extends BaseMapper<Currentuser> {
   @Select({"SELECT id , province, city, station, address, qrcode, recordtime FROM currentuser  WHERE  province = #{province} And city = #{city} And station = #{station} And address = #{address} And machineno = #{machineno} Order By  recordtime DESC LIMIT 1 "})
   Currentuser findCurrentuser(@Param("province") Integer province, @Param("city") Integer city, @Param("station") Integer station, @Param("address") Integer address, @Param("machineno") Integer machineno);

   @Delete({"DELETE FROM currentuser  WHERE  province = #{province} And city = #{city} And station = #{station} And address = #{address} And machineno = #{machineno} "})
   void delCurrentuser(@Param("province") Integer province, @Param("city") Integer city, @Param("station") Integer station, @Param("address") Integer address, @Param("machineno") Integer machineno);
}
