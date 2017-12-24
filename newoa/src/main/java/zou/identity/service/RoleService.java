package zou.identity.service;

import java.util.List;
import java.util.Set;

import zou.identity.domain.Role;
import zou.identity.domain.User;

public interface RoleService {
	Set<String> findRoleByUser(User user);
	List<Role> findAllRole();
	List<Role> findRoleByUser(String  id);
}
