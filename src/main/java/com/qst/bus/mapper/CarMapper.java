package com.qst.bus.mapper;

import com.qst.bus.domain.Car;
import com.qst.bus.domain.CarExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarMapper {
    long countByExample(CarExample example);

    int deleteByExample(CarExample example);

    int deleteByPrimaryKey(String carnumber);

    int insert(Car record);

    int insertSelective(Car record);

    List<Car> selectByExample(CarExample example);

    Car selectByPrimaryKey(String carnumber);

    int updateByExampleSelective(@Param("record") Car record, @Param("example") CarExample example);

    int updateByExample(@Param("record") Car record, @Param("example") CarExample example);

    int updateByPrimaryKeySelective(Car record);

    int updateByPrimaryKey(Car record);

    //查询
    List<Car> queryAllCar(Car car);
}