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


    //TODO: 加乐观锁
    @Update("Update merchandise set merchandise_stock = merchandise_stock-1 WHERE merchandise_id =#{merchandise_id} AND merchandise_stock >0 ")
    void reduceStock(Merchandise merchandise);

    @Select("Select * from merchandise Where merchandise_id=#{merchandise_id}")
    Merchandise getMerchandiseById(@Param("merchandise_id") int merchandise_id);


}
