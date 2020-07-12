package com.qst.sys.mapper;

import com.qst.sys.domain.Role;
import com.qst.sys.vo.RoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
	int deleteByPrimaryKey(Integer roleid);

	int insert(Role record);

	int insertSelective(Role record);

	Role selectByPrimaryKey(Integer roleid);

	int updateByPrimaryKeySelective(Role record);

	int updateByPrimaryKey(Role record);

	/**
	 * 查询角色
	 */
	List<Role> queryAllRole(RoleVo roleVo);

	/**
	 * 根据角色id删除sys_role_role里面的数据
	 * 
	 * @param roleid
	 */
	void deleteRolMenuByRid(Integer roleid);

	/**
	 * 根据角色id删除sys_role_user里面的数据
	 * 
	 * @param roleid
	 */
	void deleteRoleUserByRid(Integer roleid);

	/**
	 * 保存角色和菜单的关系 sys_role_menu
	 * @param rid
	 * @param mid
	 */
	void insertRoleMenu(@Param("rid") Integer rid, @Param("mid") Integer mid);
	

	/**
	 * 根据用户id删除sys_role_user里面的数据
	 * @param userid
	 */
	void deleteRoleUserByUid(Integer userid);

	/**
	 * 根据用户ID查询角色
	 * @param available
	 * @param userid
	 * @return
	 */
	List<Role> queryRoleByUid(@Param("available") Integer available, @Param("uid") Integer userid);

}