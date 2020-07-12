package com.qst.sys.controller;

import com.qst.sys.service.RoleService;
import com.qst.sys.utils.DataGridView;
import com.qst.sys.utils.ResultObj;
import com.qst.sys.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * 角色管理控制器
 * 
 * @author LJH
 *
 */
@RestController
@RequestMapping("role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	/**
	 * 加载角色列表返回DataGridView
	 * loadAllrole
	 */
	@RequestMapping("loadAllRole")
	public DataGridView loadAllRole(RoleVo roleVo) {
		//System.out.println("21312312312321312312312");
		return this.roleService.queryAllRole(roleVo);
	}

	
	/**
	 * 添加角色
	 */
	@RequestMapping("addRole")
	public ResultObj addRole(RoleVo roleVo) {
		try {
			this.roleService.addRole(roleVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}
	
	/**
	 * 修改角色
	 */
	@RequestMapping("updateRole")
	public ResultObj updateRole(RoleVo roleVo) {
		try {
			System.out.println(roleVo.getRolename());
			System.out.println(roleVo.getRoledesc());
			this.roleService.updateRole(roleVo);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	
	
	/**
	 * 删除角色
	 */
	@RequestMapping("deleteRole")
	public ResultObj deleteRole(RoleVo roleVo) {
		try {
			this.roleService.deleteRole(roleVo.getRoleid());
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	
	/**
	 * 批量删除角色
	 */
	@RequestMapping("deleteBatchRole")
	public ResultObj deleteBatchRole(RoleVo roleVo) {
		try {
			this.roleService.deleteBatchRole(roleVo.getIds());
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	
	/**
	 * 加载角色管理分配菜单的json
	 */
	@RequestMapping("initRoleMenuTreeJson")
	public DataGridView initRoleMenuTreeJson(Integer roleid) {
		return this.roleService.initRoleMenuTreeJson(roleid);
	}

	/**
	 * 保存角色和菜单的关系
	 */
	@RequestMapping("saveRoleMenu")
	public ResultObj saveRoleMenu(RoleVo roleVo) {
		try {

			this.roleService.saveRoleMenu(roleVo);
			return ResultObj.DISPATCH_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DISPATCH_ERROR;

		}
	}
}
