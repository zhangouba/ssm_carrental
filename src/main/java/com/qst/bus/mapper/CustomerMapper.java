package com.qst.bus.mapper;

import com.qst.bus.domain.Customer;
import com.qst.bus.domain.CustomerExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerMapper {
    long countByExample(CustomerExample example);

    int deleteByExample(CustomerExample example);

    int deleteByPrimaryKey(String identity);

    int insert(Customer record);

    int insertSelective(Customer record);

    List<Customer> selectByExample(CustomerExample example);

    Customer selectByPrimaryKey(String identity);

    int updateByExampleSelective(@Param("record") Customer record, @Param("example") CustomerExample example);

    int updateByExample(@Param("record") Customer record, @Param("example") CustomerExample example);

    int updateByPrimaryKeySelective(Customer record);

    /*查询*/
    List<Customer> queryAllCustomer(Customer customer);

}