package com.qst.stat.service;

import com.qst.stat.domain.BaseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface StatService {
    /*统计分析客户地区*/
    List<BaseEntity> loadCustomerAreaStatList();

    Map<String, Object> toCompanyYearGradeStat(String year);

    List<Double> loadCompanyYearGradeStat(String year);
}
