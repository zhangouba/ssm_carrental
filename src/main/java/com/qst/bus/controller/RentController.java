package com.qst.bus.controller;

import com.qst.bus.domain.Customer;
import com.qst.bus.service.CustomerService;
import com.qst.bus.service.RentService;
import com.qst.bus.vo.CustomerVo;
import com.qst.bus.vo.RentVo;
import com.qst.sys.constast.SysConstast;
import com.qst.sys.domain.User;
import com.qst.sys.utils.DataGridView;
import com.qst.sys.utils.RandomUtils;
import com.qst.sys.utils.ResultObj;
import com.qst.sys.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("rent")
public class RentController {
    @Autowired
    private RentService rentService;
@Autowired
    private CustomerService customerService;

    /**
     * 检查身份证号是否存在
     * @param customerVo
     * */
    @RequestMapping("checkCustomerExist")
    public ResultObj checkCustomerExist(CustomerVo  customerVo){
       Customer customer= customerService.queryAllCustomerByIdentity(customerVo.getIdentity());
       if (customer!=null){
           return ResultObj.STATUS_TRUE;
       }else {
           return ResultObj.STATUS_FALSE;
       }
    }
    /**
     * 初始化添加出租单的表单数据
     * @param rentVo
     * */
    @RequestMapping("initRenFrom")
    public RentVo initRenFrom(RentVo  rentVo){
       //生成出租单号
        rentVo.setRentid(RandomUtils.createRandomStringUseTime(SysConstast.CAR_ORDER_CZ));
       //设置起租时间
        rentVo.setBegindate(new Date());
       //设置操作员
      User user= (User) WebUtils.getHttpSession().getAttribute("user");
        rentVo.setOpername(user.getRealname());
        RentVo rentVoData=new RentVo();
        return rentVo;
    }
    /**
     * 保存出租单信息
     */
    @RequestMapping("saveRent")
    public ResultObj saveRent(RentVo rentVo) {
        try {
           //设置创建时间
            rentVo.setCreatetime(new Date());
            //设置归还状态
            rentVo.setRentflag(SysConstast.RENT_BACK_FALSE);

            //保存
            this.rentService.addRent(rentVo);

            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
//    出租但管理——————————————————————————————————————————————————————
    /**
     * 查询
     * */
    @RequestMapping("loadAllRent")
    public DataGridView loadAllRent(RentVo rentVo){

        return rentService.queryAllRent(rentVo);
    }

/*修改出租单信息*/
    @RequestMapping("updateRent")
    public ResultObj updateRent(RentVo rentVo) {
        try {
            rentService.updateRent(rentVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            return ResultObj.UPDATE_ERROR;
        }
    }

    /*删除出租但*/

    @RequestMapping("deleteRent")
    public ResultObj deleteRent(String rentid) {
        try {
             rentService.deleteRent(rentid);
            return ResultObj.DELETE_SUCCESS;

        } catch (Exception e) {
            return ResultObj.DELETE_ERROR;
        }

    }
}
