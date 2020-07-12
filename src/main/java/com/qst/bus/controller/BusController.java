package com.qst.bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 业务管理路由控制器跳转页面
 * @param
 */

@Controller
@RequestMapping("bus")
public class BusController {
    /**
     * 跳转到客户管业里面
     *
     * @param
     **/
    @RequestMapping("toCustomerManager")
    public String toCustomerManager() {
        return "business/customer/customerManager";
    }
    /**
     * 跳转到车辆管业里面
     *
     * @param
     **/
    @RequestMapping("toCarManager")
    public String toCarManager() {
        return "business/car/carManager";
    }

    /**
     * 跳转到车辆出租页面
     *
     * @param
     **/
    @RequestMapping("toRentCarManager")
    public String toRentCarManager() {
        return "business/rent/rentCarManager";
    }

    /**
     * 跳转到出租但管理页面
     *
     * @param
     **/
    @RequestMapping("toRentManager")
    public String toRentManager() {
        return "business/rent/rentManager";
    }

    /**
     * 跳转到汽车入库管理页面
     *
     * @param
     **/
    @RequestMapping("toCheckCarManager")
    public String toCheckCarManager() {
        return "business/check/checkCarManager";
    }

    /**
     * 跳转到检查单管理页面
     * @param
     **/
    @RequestMapping("toCheckManager")
    public String toCheckManager() {
        return "business/check/checkManager";
    }
}
