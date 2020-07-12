package com.qst.bus.mapper;

import com.qst.bus.domain.Rent;
import com.qst.bus.domain.RentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RentMapper {
    long countByExample(RentExample example);

    int deleteByExample(RentExample example);

    int deleteByPrimaryKey(String rentid);

    int insert(Rent record);

    int insertSelective(Rent record);

    List<Rent> selectByExample(RentExample example);

    Rent selectByPrimaryKey(String rentid);

    int updateByExampleSelective(@Param("record") Rent record, @Param("example") RentExample example);

    int updateByExample(@Param("record") Rent record, @Param("example") RentExample example);

    int updateByPrimaryKeySelective(Rent record);

    int updateByPrimaryKey(Rent record);
    //查询
    public List<Rent> queryAllRent(Rent rent);
}