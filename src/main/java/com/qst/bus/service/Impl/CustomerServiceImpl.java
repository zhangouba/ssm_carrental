package com.qst.bus.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qst.bus.domain.Customer;
import com.qst.bus.mapper.CustomerMapper;
import com.qst.bus.service.CustomerService;
import com.qst.bus.vo.CustomerVo;
import com.qst.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
   @Autowired
   private CustomerMapper customerMapper;

    @Override
    public DataGridView queryAllCustomer(CustomerVo customerVo) {
        Page<Object> page= PageHelper.startPage(customerVo.getPage(),customerVo.getLimit());
        List<Customer> data=customerMapper.queryAllCustomer(customerVo);
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public List<Customer> queryAllCustomerForList(CustomerVo customerVo) {
        return customerMapper.queryAllCustomer(customerVo);
    }

    @Override
    public void addCustomer(CustomerVo customerVo) {
     customerMapper.insertSelective(customerVo);
    }

    @Override
    public void deleteCustomer(String identity) {
        customerMapper.deleteByPrimaryKey(identity);
    }

    @Override
    public void deleteBatchCustomer(String[] identitys) {
         for (String id:identitys){
             deleteCustomer(id);
         }
    }

    @Override
    public void updateCustomer(CustomerVo customerVo) {
        customerMapper.updateByPrimaryKeySelective(customerVo);
    }

    @Override
    public Customer queryAllCustomerByIdentity(String identity) {
        return customerMapper.selectByPrimaryKey(identity);
    }

}
