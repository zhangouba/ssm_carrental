package com.qst.stat.service.Impl;

import com.qst.stat.domain.BaseEntity;
import com.qst.stat.mapper.StatMapper;
import com.qst.stat.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatServiceImpl implements StatService {

     @Autowired
     private StatMapper statMapper;

    @Override
    public List<BaseEntity> loadCustomerAreaStatList() {
        return statMapper.loadCustomerAreaStatList();
    }

    @Override
    public Map<String, Object> toCompanyYearGradeStat(String year) {
        List<BaseEntity> baseEntityList = statMapper.toCompanyYearGradeStat(year);
        List<String> name = new ArrayList<>();
        List<Double> value = new ArrayList<>();
        for (BaseEntity baseEntity : baseEntityList) {
            name.add(baseEntity.getName());
            value.add(baseEntity.getValue());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("value", value);
        return map;
    }

    @Override
    public List<Double> loadCompanyYearGradeStat(String year) {
        List<Double> list=statMapper.loadCompanyYearGradeStat(year);
        for (int i=0;i<list.size();i++){
            if (list.get(i)==null){
                list.set(i,0.0);
            }
        }
        return list;
    }

}
