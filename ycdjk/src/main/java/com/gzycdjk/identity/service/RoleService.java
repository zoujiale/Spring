package com.gzycdjk.identity.service;

import java.util.List;
import java.util.Set;

import com.gzycdjk.commons.pojo.UserRole;
import com.gzycdjk.commons.vo.Message;
import com.gzycdjk.commons.vo.Page;
import com.gzycdjk.commons.vo.Pageable;
import com.gzycdjk.identity.domain.Role;
import com.gzycdjk.identity.domain.User;

/**
 * 
 * 角色的业务层
 * @author YCJKmr.zo
 *
 */

public interface RoleService {
	
	/**
	 * 添加角色
	 * @param role
	 * @return
	 */
	Message addRole(Role role);
	
	/**
	 * 根据Id查询角色
	 * @param id
	 * @return
	 */
	Role findRoleByid(String id);

	/**
	 * 找到全部角色
	 * @return
	 */
	List<Role> findAllRole();
	/**
	 * 根据角色名寻找角色
	 * @param roleName
	 * @return
	 */
	Page<Role> findRoleByQuery(String roleName);
	/**
	 * 根据Id找角色
	 * @param role
	 * @return
	 */
	Message editRoleById(Role role);
	/**
	 * 根据Id删除角色
	 * @param id
	 * @return
	 */
	Message deleteRoleById(String id);

	Page<User> findRoleUserById(String id, Pageable page, String userName);
	
	Page<User> UserNotBoundByRoleId(String id, Pageable pga, String userName);

	Message UserBoundRole(List<UserRole> parseArray);

	Set<Role> findRoleByUser(User us);




}
