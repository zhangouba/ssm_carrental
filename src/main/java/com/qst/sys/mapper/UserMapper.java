package com.qst.sys.mapper;

import com.qst.sys.domain.Role;
import com.qst.sys.domain.User;
import com.qst.sys.domain.UserExample;
import com.qst.sys.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


    //用户登录
    User login(UserVo userVo);

    //查询用户
    List<User> queryAllUser(User user);
    void saveUserRole(@Param("uid") Integer userid,@Param("rid") Integer ids);
    List<Role> queryRoleByUid(@Param("available") Integer available,@Param("userid") Integer userid);


}