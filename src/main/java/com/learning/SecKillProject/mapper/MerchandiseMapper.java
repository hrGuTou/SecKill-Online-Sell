package com.learning.SecKillProject.mapper;

import com.learning.SecKillProject.model.Merchandise;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MerchandiseMapper {

    @Select("Select * from merchandise")
    List<Merchandise> getAllMerchandise();

    @Select("SELECT * FROM merchandise WHERE merchandise_stock>0;")
    List<Merchandise> getValidMerchandise();

    @Select("SELECT merchandise_stock FROM merchandise WHERE merchandise_id = #{merchandise_id;}")
    int getCount(@Param("merchandise_id") int merchandise_id);

    //TODO: 加乐观锁
    @Update("Update merchandise set merchandise_stock = merchandise_stock-1 WHERE merchandise_id =#{merchandise_id} AND merchandise_stock >0 ")
    void reduceStock(Merchandise merchandise);

    //
    @Update("Update merchandise set merchandise_stock = 0 WHERE merchandise_id =#{merchandise_id}")
    void setOutOfStock(Merchandise merchandise);


    @Select("Select * from merchandise Where merchandise_id=#{merchandise_id}")
    Merchandise getMerchandiseById(@Param("merchandise_id") int merchandise_id);


}
