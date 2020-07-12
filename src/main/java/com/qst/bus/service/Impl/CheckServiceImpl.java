package com.qst.bus.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qst.bus.domain.Car;
import com.qst.bus.domain.Check;
import com.qst.bus.domain.Customer;
import com.qst.bus.domain.Rent;
import com.qst.bus.mapper.CarMapper;
import com.qst.bus.mapper.CheckMapper;
import com.qst.bus.mapper.CustomerMapper;
import com.qst.bus.mapper.RentMapper;
import com.qst.bus.service.CheckService;
import com.qst.bus.vo.CheckVo;
import com.qst.sys.constast.SysConstast;
import com.qst.sys.domain.User;
import com.qst.sys.utils.DataGridView;
import com.qst.sys.utils.RandomUtils;
import com.qst.sys.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CheckServiceImpl implements CheckService {

    @Autowired
    private RentMapper rentMapper;

    @Autowired
    private CarMapper carMapper;
    @Autowired
    private CheckMapper checkMapper;
    @Autowired
    private CustomerMapper customerMapper;



    @Override
    public Map<String,Object> initCheckFormData(String rentid) {
        Map<String,Object> map=new HashMap<>();
        //出租单
        Rent rent=  rentMapper.selectByPrimaryKey(rentid);
        //车辆信息
        Car car=carMapper.selectByPrimaryKey(rent.getCarnumber());
        //客户
        Customer customer= customerMapper.selectByPrimaryKey(rent.getIdentity());
        //组装check
        Check check=new Check();
        check.setCheckid(RandomUtils.createRandomStringUseTime(SysConstast.CAR_ORDER_JC));
        check.setRentid(rentid);
        check.setCheckdate(new Date());
        User user= (User) WebUtils.getHttpSession().getAttribute("user");
        check.setOpername(user.getRealname());

        map.put("rent",rent);
        map.put("car",car);
        map.put("customer",customer);
        map.put("check",check);

        return map;

    }

    @Override
    public void saveCheck(CheckVo checkVo) {
          checkVo.setCreatetime(new Date());
          checkMapper.insertSelective(checkVo);
          //更改出租单状态
         Rent rent=   rentMapper.selectByPrimaryKey(checkVo.getRentid());
         rent.setRentflag(SysConstast.RENT_BACK_TRUE);
         rent.setReturndate(new Date());
         rentMapper.updateByPrimaryKeySelective(rent);
         //更改汽车状态
         Car car=carMapper.selectByPrimaryKey(rent.getCarnumber());
         car.setIsrenting(SysConstast.RENT_CAR_TRUE);
         carMapper.updateByPrimaryKeySelective(car);
    }

    @Override
    public DataGridView queryAllCheck(CheckVo checkVo) {
        Page<Object> page= PageHelper.startPage(checkVo.getPage(),checkVo.getLimit());
        List<Check> data=checkMapper.queryAllCheck(checkVo);
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void updateCheck(CheckVo checkVo) {
        checkMapper.updateByPrimaryKeySelective(checkVo);
    }
}
