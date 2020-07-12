package com.qst.stat.controller;

import com.qst.bus.domain.Customer;
import com.qst.bus.domain.Rent;
import com.qst.bus.service.CustomerService;
import com.qst.bus.service.RentService;
import com.qst.bus.vo.CustomerVo;
import com.qst.stat.domain.BaseEntity;
import com.qst.stat.service.StatService;
import com.qst.stat.utils.ExproCustomerUtils;
import com.qst.stat.utils.ExprotRentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@RequestMapping("stat")
@Controller
public class StatController {

    @Autowired
    private StatService statService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private RentService rentService;

    /**
     * 跳转到客户地区统计页面*/

    @RequestMapping("toCustomerAreaStat")
    public  String toCustomerAreaStat(){
        return "stat/customerAreaStat";
    }

    @RequestMapping("loadOpernameYearAreaStatJson")
    @ResponseBody
    public List<BaseEntity> loadCustomerAreaStatJson(){
          return statService.loadCustomerAreaStatList();
    }





    @RequestMapping("toOpernameYearGradeStat")
    public String toOpernameYearGradeStat() {
        return "stat/opernameYearGradeAreaStat";
    }

    /**
     * 跳转到操作员业绩统计页面*/
    @RequestMapping("loadOpernameYearGradeStat")
    @ResponseBody
    public Map<String, Object> loadOpernameYearAreaStatJson(String year) {
        return statService.toCompanyYearGradeStat(year);
    }






    @RequestMapping("toCompanyYearGradeStat")
    public String toCompanyYearGradeStat() {
        return "stat/companyYearGradeStat";
    }

    @RequestMapping("loadCompanyYearGradeStat")
    @ResponseBody
    public List<Double> loadCompanyYearGradeStat(String year) {
        return statService.loadCompanyYearGradeStat(year);
    }

    //带出客户数据
    @RequestMapping("exportCustomer")
    public ResponseEntity<Object> exportCustomer(CustomerVo customerVo, HttpServletResponse response) throws UnsupportedEncodingException {
     List<Customer> list=   customerService.queryAllCustomerForList(customerVo);
     String fileName="客户数据.xls";
        String sheetName="客户数据";
        ByteArrayOutputStream bos= null;
        try {
            bos = ExproCustomerUtils.exprotCustomer(list,sheetName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        fileName= URLEncoder.encode(fileName,"UTF-8");
        //创建封装响应头信息
        HttpHeaders httpHeaders=new HttpHeaders();
        //封装相应内容(APPLICATION_OCTET_STREAM为响应内容不限)
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //设置下载文件名字
        httpHeaders.setContentDispositionFormData("attachment",fileName);
        return  new ResponseEntity<Object>(bos.toByteArray(),httpHeaders, HttpStatus.CREATED);
    }


    @RequestMapping("exportRent")
    public ResponseEntity<Object> exportRent(String rentid, HttpServletResponse response) throws UnsupportedEncodingException {
        //根据出租单号查询出租单信息
      Rent rent= rentService.queryAlltByRentId(rentid);
        //根据客户身份证号查询客户姓名
     Customer customer=   customerService.queryAllCustomerByIdentity(rent.getIdentity());
        String fileName=customer.getCustname()+"的出租单信息.xls";
        String sheetName=customer.getCustname()+"的出租单信息";
        ByteArrayOutputStream bos= null;
        try {
            bos = ExprotRentUtils.exportRent(rent,customer,sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        fileName= URLEncoder.encode(fileName,"UTF-8");
        //创建封装响应头信息
        HttpHeaders httpHeaders=new HttpHeaders();
        //封装相应内容(APPLICATION_OCTET_STREAM为响应内容不限)
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //设置下载文件名字
        httpHeaders.setContentDispositionFormData("attachment",fileName);
        return  new ResponseEntity<Object>(bos.toByteArray(),httpHeaders, HttpStatus.CREATED);
    }


}
