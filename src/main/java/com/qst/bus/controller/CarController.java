package com.qst.bus.controller;

import com.qst.bus.domain.Car;
import com.qst.bus.service.CarService;
import com.qst.bus.vo.CarVo;
import com.qst.sys.constast.SysConstast;
import com.qst.sys.utils.AppFileUtils;
import com.qst.sys.utils.DataGridView;
import com.qst.sys.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 车辆管理控制器
 *
 * @author LJH
 *
 */
@RestController
@RequestMapping("car")
public class CarController {

	@Autowired
	private CarService carService;
	/**
	 * 加载车辆列表返回DataGridView
	 * loadAllcar
	 */
	@RequestMapping("loadAllCar")
	public DataGridView loadAllCar(CarVo carVo) {

		return this.carService.queryAllCar(carVo);
	}

	/**
	 * 添加车辆
	 */
	@RequestMapping("addCar")
	public ResultObj addCar(CarVo carVo) {
		try {
			carVo.setCreatetime(new Date());
			//如果用户上传不是默认图片，就去掉图片的后缀
			if (!carVo.getCarimg().equals(SysConstast.DEFAULT_CAR_IMG)){
			carVo.setCarimg(AppFileUtils.updateFileName(carVo.getCarimg(), SysConstast.FILE_UPLOAD_TEMP));
			}
			this.carService.addCar(carVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}
	/**
	 * 修改车辆
	 */
	@RequestMapping("updateCar")
	public ResultObj updateCar(CarVo carVo) {
		try {
		    //如果修改过图片
			if (carVo.getCarimg().endsWith(SysConstast.FILE_UPLOAD_TEMP)){
				//1.把带_temp的改名字
				carVo.setCarimg(AppFileUtils.updateFileName(carVo.getCarimg(),SysConstast.FILE_UPLOAD_TEMP));
				//2.把原来的图片去掉
               Car car= carService.queryCarByCarNumber(carVo.getCarnumber());
               AppFileUtils.removeFileByPath(car.getCarimg());
			}
			this.carService.updateCar(carVo);



			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}


	/**
	 * 删除车辆
	 */
	@RequestMapping("deleteCar")
	public ResultObj deleteCar(CarVo carVo) {
		try {
			this.carService.deleteCar(carVo.getCarnumber());
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}

	/**
	 * 批量删除车辆
	 */
	@RequestMapping("deleteBatchCar")
	public ResultObj deleteBatchCar(CarVo carVo) {
		try {
			this.carService.deleteBatchCar(carVo.getIds());
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
}
