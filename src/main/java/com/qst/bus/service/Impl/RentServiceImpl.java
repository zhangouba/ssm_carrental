package com.qst.bus.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qst.bus.domain.Car;
import com.qst.bus.domain.Rent;
import com.qst.bus.mapper.CarMapper;
import com.qst.bus.mapper.CheckMapper;
import com.qst.bus.mapper.CustomerMapper;
import com.qst.bus.mapper.RentMapper;
import com.qst.bus.service.RentService;
import com.qst.bus.vo.RentVo;
import com.qst.sys.constast.SysConstast;
import com.qst.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentServiceImpl implements RentService {

    @Autowired
    private RentMapper rentMapper;

    @Autowired
    private CarMapper carMapper;
    @Autowired
    private CheckMapper checkMapper;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public void addRent(RentVo rentVo) {
        this.rentMapper.insertSelective(rentVo);
        //更改车辆的出租状态
        Car car=new Car();
        car.setCarnumber(rentVo.getCarnumber());
        car.setIsrenting(SysConstast.RENT_CAR_FALSE);
        carMapper.updateByPrimaryKeySelective(car);
    }

    @Override
    public DataGridView queryAllRent(RentVo rentVo) {
        Page<Object> page= PageHelper.startPage(rentVo.getPage(),rentVo.getLimit());
        List<Rent> data= rentMapper.queryAllRent(rentVo);
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void updateRent(RentVo rentVo) {
        rentMapper.updateByPrimaryKeySelective(rentVo);
    }

    @Override
    public void deleteRent(String rentid) {
       rentMapper.deleteByPrimaryKey(rentid);
    }

    @Override
    public Rent queryAlltByRentId(String rentid) {
          return  rentMapper.selectByPrimaryKey(rentid);
    }


}
