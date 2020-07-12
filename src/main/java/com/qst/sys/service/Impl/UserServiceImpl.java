package com.qst.sys.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qst.sys.constast.SysConstast;
import com.qst.sys.domain.Role;
import com.qst.sys.domain.User;
import com.qst.sys.mapper.RoleMapper;
import com.qst.sys.mapper.UserMapper;
import com.qst.sys.service.UserService;
import com.qst.sys.utils.DataGridView;
import com.qst.sys.vo.RoleVo;
import com.qst.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public User login(UserVo userVo) {

        String pwd=DigestUtils.md5DigestAsHex(userVo.getPwd().getBytes());
        userVo.setPwd(pwd);
        return userMapper.login(userVo);
    }


    @Override
    public DataGridView queryAllUser(UserVo userVo) {
        Page<Object> page= PageHelper.startPage(userVo.getPage(),userVo.getLimit());
       List<User> data= userMapper.queryAllUser(userVo);

        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void addUser(UserVo userVo) {
        //设置默认密码
        userVo.setPwd(DigestUtils.md5DigestAsHex(SysConstast.DEFAULT_PWD.getBytes()));
        //添加的用户都是普通用户，超级管理员只有一个是系统内置的
        userVo.setType(SysConstast.USER_NORMALLY_TYPE);
        userMapper.insertSelective(userVo);
    }

    @Override
    public void updateUser(UserVo userVo) {
          userMapper.updateByPrimaryKeySelective(userVo);
    }

    @Override
    public void deleteUser(Integer roleid) {
            userMapper.deleteByPrimaryKey(roleid);
            roleMapper.deleteRoleUserByUid(roleid);
    }

    @Override
    public void deleteBatchUser(Integer[] ids) {
          for (Integer uid:ids){
            deleteUser(uid);
          }
    }

    @Override
    public void resetUserPwd(Integer userid) {
        User user=new User();
        user.setUserid(userid);
          //设置默认密码
        user.setPwd(DigestUtils.md5DigestAsHex(SysConstast.DEFAULT_PWD.getBytes()));

        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public DataGridView queryUserRole(Integer userid) {
        //查询所有可用角色
        RoleVo roleVo=new RoleVo();
        roleVo.setAvailable(SysConstast.AVAILABLE_TRUE);
        List<Role> allRole=  roleMapper.queryAllRole(roleVo);

        //根据用户id查询已拥有的角色
      List<Role> userRole =  roleMapper.queryRoleByUid(SysConstast.AVAILABLE_TRUE,userid);
      List<Map<String,Object>> data=new ArrayList<>();
      for (Role r1:allRole){
          boolean LAY_CHECKED=false;
          for (Role r2:userRole){
              if (r1.getRoleid()==r2.getRoleid()){
                  LAY_CHECKED=true;
              }
          }
          Map<String,Object> map=new HashMap<>();
          map.put("roleid",r1.getRoleid());
          map.put("rolename",r1.getRolename());
          map.put("roledesc",r1.getRoledesc());
          map.put("LAY_CHECKED",LAY_CHECKED);
          data.add(map);
      }



        return new DataGridView(data);
    }


    // 保存用户分配角色
    @Override
    public void saveUserRole(UserVo userVo) {
        roleMapper.deleteRoleUserByUid(userVo.getUserid());
        if (userVo.getIds() != null && userVo.getIds().length > 0) {
            for (Integer ids : userVo.getIds()) {

                userMapper.saveUserRole(userVo.getUserid(), ids);
            }
        }
    }


}
