package com.qst.bus.service;

import com.qst.bus.domain.Customer;
import com.qst.bus.vo.CustomerVo;
import com.qst.sys.utils.DataGridView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    /**
     * 查询所有客户
     * @param customerVo
     * @return
     */
    public DataGridView queryAllCustomer(CustomerVo customerVo);
    public List<Customer> queryAllCustomerForList(CustomerVo customerVo);

    /**
     * 添加客户
     * @param customerVo
     */
    public void addCustomer(CustomerVo customerVo);

    /**
     * 根据id删除客户
     * @param identity
     */
    public void deleteCustomer(String identity);
    /**
     * 批量删除客户s
     * @param identitys
     */
    public void deleteBatchCustomer(String[] identitys);
    /**
     * 更新客户
     * @param customerVo
     */
    public void updateCustomer(CustomerVo customerVo);

    Customer  queryAllCustomerByIdentity(String identity);
}
