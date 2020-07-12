package com.qst.bus.controller;

import com.qst.bus.domain.Rent;
import com.qst.bus.service.CheckService;
import com.qst.bus.service.RentService;
import com.qst.bus.vo.CheckVo;
import com.qst.sys.utils.DataGridView;
import com.qst.sys.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/*检查单管理控制器*/
@RestController
@RequestMapping("check")
public class CheckController {

    @Autowired
    private RentService rentService;

    @Autowired
    private CheckService checkService;
    //根据出租单号查询出租信息
    @RequestMapping("checkRentExist")
    public Rent checkRentExist(String rentid){
      Rent rent= rentService.queryAlltByRentId(rentid);
       return  rent;
    }

    @RequestMapping("initCheckFormData")
    public Map<String,Object> initCheckFormData(String rentid){

        return checkService.initCheckFormData(rentid);
    }

    @RequestMapping("saveCheck")
    public ResultObj saveCheck(CheckVo checkVo){
        try {

            checkService.saveCheck(checkVo);

            return ResultObj.ADD_SUCCESS;

        }catch (Exception e){
            return ResultObj.ADD_ERROR;
        }
    }
    ///w
    //////////////////////////////////////////////////检查单
    //初始化数据和模糊查询
    @RequestMapping("loadAllCheck")
    public DataGridView loadAllCheck(CheckVo checkVo){
        return  checkService.queryAllCheck(checkVo);
    }
    //保存修改
    @RequestMapping("updateCheck")
    public  ResultObj updateCheck(CheckVo checkVo){
        try {
            checkService.updateCheck(checkVo);
            return ResultObj.UPDATE_SUCCESS;

        }catch (Exception e){
            return ResultObj.UPDATE_ERROR;
        }
    }

}
