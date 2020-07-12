package com.qst.bus.service;

import com.qst.bus.domain.Car;
import com.qst.bus.vo.CarVo;
import com.qst.sys.utils.DataGridView;
import org.springframework.stereotype.Service;

@Service
public interface CarService {

    /**
     * 查询所有车辆
     * @param carVo
     * @return
     */
    public DataGridView queryAllCar(CarVo carVo);

    /**
     * 添加车辆
     * @param carVo
     */
    public void addCar(CarVo carVo);

    /**
     * 根据id删除车辆
     * @param identity
     */
    public void deleteCar(String identity);
    /**
     * 批量删除车辆s
     * @param identitys
     */
    public void deleteBatchCar(String[] identitys);
    /**
     * 更新车辆
     * @param carVo
     */
    public void updateCar(CarVo carVo);
    /**
     * 根据车牌号查询以前的图片路径
     * @param carnumber
     */
    public Car queryCarByCarNumber(String carnumber);

}
