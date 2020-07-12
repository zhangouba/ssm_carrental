package com.qst.bus.mapper;

import com.qst.bus.domain.Check;
import com.qst.bus.domain.CheckExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckMapper {
    long countByExample(CheckExample example);

    int deleteByExample(CheckExample example);

    int deleteByPrimaryKey(String checkid);

    int insert(Check record);

    int insertSelective(Check record);

    List<Check> selectByExample(CheckExample example);

    Check selectByPrimaryKey(String checkid);

    int updateByExampleSelective(@Param("record") Check record, @Param("example") CheckExample example);

    int updateByExample(@Param("record") Check record, @Param("example") CheckExample example);

    int updateByPrimaryKeySelective(Check record);

    int updateByPrimaryKey(Check record);
    //查询
    List<Check> queryAllCheck(Check check);
}