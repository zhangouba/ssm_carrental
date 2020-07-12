package com.qst.sys.service;

import com.qst.sys.domain.User;
import com.qst.sys.utils.DataGridView;
import com.qst.sys.vo.UserVo;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
      //LOGIN
    public User login(UserVo userVo);

    /**
     * 查询所有用户返回
     * List<User>
     */

    public DataGridView queryAllUser(UserVo userVo);

    /**
     * 添加用户
     * @param userVo
     */
    public void addUser(UserVo userVo);

    /**
     * 修改用户
     * @param userVo
     */
    public void updateUser(UserVo userVo);

    /**
     * 根据id删除用户
     * @param userid
     */
    public void deleteUser(Integer userid);
    /**
     * 批量删除用户
     * @param ids
     */
    public void deleteBatchUser(Integer[] ids);

    /**
     * 重置密码
     * @param userid
     */
    public void resetUserPwd(Integer userid);

    DataGridView queryUserRole(Integer userid);

    void saveUserRole(UserVo userVo);
}
