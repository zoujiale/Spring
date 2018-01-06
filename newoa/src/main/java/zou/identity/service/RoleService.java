package zou.identity.service;

import java.util.List;
import java.util.Set;

import zou.common.vo.Message;
import zou.identity.domain.Role;
import zou.identity.domain.User;

public interface RoleService {
	Set<String> findRoleByUser(User user);
	List<Role> findAllRole();
	List<Role> findRoleByUser(String  id);
	List<Role> findContent(String findContent);
	/**
	 * 添加角色
	 * 
	 * @param type
	 * @param name
	 * @return
	 */
	Message addRole(String type, String name);
	/**
	 * 删除用户
	 */
	void editRole(String id);
}
