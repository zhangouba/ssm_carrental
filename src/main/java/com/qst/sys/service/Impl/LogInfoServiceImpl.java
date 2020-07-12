package com.qst.sys.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qst.sys.domain.LogInfo;
import com.qst.sys.mapper.LogInfoMapper;
import com.qst.sys.service.LogInfoService;
import com.qst.sys.utils.DataGridView;
import com.qst.sys.vo.LogInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogInfoServiceImpl  implements LogInfoService {
   @Autowired
   private LogInfoMapper logInfoMapper;

    @Override
    public DataGridView queryAllLogInfo(LogInfoVo logInfoVo) {
        Page<Object> page= PageHelper.startPage(logInfoVo.getPage(),logInfoVo.getLimit());
        List<LogInfo> data=logInfoMapper.queryAllLogInfo(logInfoVo);
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void addLogInfo(LogInfoVo logInfoVo) {
     logInfoMapper.insertSelective(logInfoVo);
    }

    @Override
    public void deleteLogInfo(Integer logInfoVo) {
        logInfoMapper.deleteByPrimaryKey(logInfoVo);
    }

    @Override
    public void deleteBatchLogInfo(Integer[] ids) {
         for (Integer id:ids){
             deleteLogInfo(id);
         }
    }
}
