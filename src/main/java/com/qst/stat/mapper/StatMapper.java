package com.qst.stat.mapper;

import com.qst.stat.domain.BaseEntity;

import java.util.List;

public interface StatMapper {
    List<BaseEntity> loadCustomerAreaStatList();

    List<BaseEntity> toCompanyYearGradeStat(String year);

    List<Double> loadCompanyYearGradeStat(String year);
}
