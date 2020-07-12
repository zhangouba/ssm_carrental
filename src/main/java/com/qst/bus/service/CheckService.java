package com.qst.bus.service;

import com.qst.bus.vo.CheckVo;
import com.qst.sys.utils.DataGridView;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface CheckService {

    Map<String,Object> initCheckFormData(String rentid);

    void saveCheck(CheckVo checkVo);

    DataGridView queryAllCheck(CheckVo checkVo);

    void updateCheck(CheckVo checkVo);
}
