package com.qst.bus.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qst.bus.domain.Car;
import com.qst.bus.mapper.CarMapper;
import com.qst.bus.service.CarService;
import com.qst.bus.vo.CarVo;
import com.qst.sys.constast.SysConstast;
import com.qst.sys.utils.AppFileUtils;
import com.qst.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
   @Autowired
   private CarMapper carMapper;

    @Override
    public DataGridView queryAllCar(CarVo carVo) {
        Page<Object> page= PageHelper.startPage(carVo.getPage(),carVo.getLimit());
        List<Car> data=carMapper.queryAllCar(carVo);
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void addCar(CarVo carVo) {
     carMapper.insertSelective(carVo);
    }

    @Override
    public void deleteCar(String carnumber) {
        Car car = this.queryCarByCarNumber(carnumber);
        if (!car.getCarimg().equals(SysConstast.DEFAULT_CAR_IMG)) {
            AppFileUtils.removeFileByPath(car.getCarimg());
        }
        carMapper.deleteByPrimaryKey(carnumber);
    }

    @Override
    public void deleteBatchCar(String[] carnumbers) {
         for (String id:carnumbers){
             deleteCar(id);
         }
    }

    @Override
    public void updateCar(CarVo carVo) {
        carMapper.updateByPrimaryKeySelective(carVo);
    }

    @Override
    public Car queryCarByCarNumber(String carnumber) {
        return carMapper.selectByPrimaryKey(carnumber);
    }

}
