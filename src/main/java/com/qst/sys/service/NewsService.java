package com.qst.sys.service;

import com.qst.sys.domain.News;
import com.qst.sys.utils.DataGridView;
import com.qst.sys.vo.NewsVo;
import org.springframework.stereotype.Service;

@Service
public interface NewsService {

    /**
     * 查询所有公告
     * @param newsVo
     * @return
     */
    public DataGridView queryAllNews(NewsVo newsVo);

    /**
     * 添加公告
     * @param newsVo
     */
    public void addNews(NewsVo newsVo);

    /**
     * 根据id删除公告
     * @param id
     */
    public void deleteNews(Integer id);
    /**
     * 批量删除公告s
     * @param ids
     */
    public void deleteBatchNews(Integer[] ids);
    /**
     * 更新公告
     * @param newsVo
     */
    public void updateNews(NewsVo newsVo);

    News queryNewsById(Integer newsid);
}
