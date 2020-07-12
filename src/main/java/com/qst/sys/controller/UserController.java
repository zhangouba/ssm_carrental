package com.qst.sys.controller;

import com.qst.sys.service.RoleService;
import com.qst.sys.service.UserService;
import com.qst.sys.utils.DataGridView;
import com.qst.sys.utils.ResultObj;
import com.qst.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理控制器
 * 
 * @author LJH
 *
 */
@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService UserService;

	@Autowired
	private RoleService roleService;
	/**
	 * 加载用户列表返回DataGridView
	 * loadAllUser
	 */
	@RequestMapping("loadAllUser")
	public DataGridView loadAllUser(UserVo UserVo) {
		//System.out.println("21312312312321312312312");
		return this.UserService.queryAllUser(UserVo);
	}

	
	/**
	 * 添加用户
	 */
	@RequestMapping("addUser")
	public ResultObj addUser(UserVo UserVo) {
		try {
			this.UserService.addUser(UserVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}
	
	/**
	 * 修改用户
	 */
	@RequestMapping("updateUser")
	public ResultObj updateUser(UserVo UserVo) {
		try {

			this.UserService.updateUser(UserVo);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	
	
	/**
	 * 删除用户
	 */
	@RequestMapping("deleteUser")
	public ResultObj deleteUser(UserVo UserVo) {
		try {
			this.UserService.deleteUser(UserVo.getUserid());
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	
	/**
	 * 批量删除用户
	 */
	@RequestMapping("deleteBatchUser")
	public ResultObj deleteBatchUser(UserVo UserVo) {
		try {
			this.UserService.deleteBatchUser(UserVo.getIds());
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	/**
	 * 充值用户密码
	 */
	@RequestMapping("resrtUserPwd")
	public ResultObj resrtUserPwd(UserVo UserVo) {
		try {
			this.UserService.resetUserPwd(UserVo.getUserid());
			return ResultObj.RESET_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.RESET_ERROR;
		}
	}

	//分配角色菜单列表
	@RequestMapping("initUserRole")
	public DataGridView initUserRole(UserVo UserVo) {

		return UserService.queryUserRole(UserVo.getUserid());
	}
  //保存用户角色分配
	@RequestMapping("saveUserRole")
	public ResultObj saveUserRole(UserVo userVo){
		try {
			this.UserService.saveUserRole(userVo);
			return ResultObj.DISPATCH_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}




}
