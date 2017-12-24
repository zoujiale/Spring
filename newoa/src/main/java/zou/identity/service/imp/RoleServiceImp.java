package zou.identity.service.imp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zou.identity.dao.RoleDao;
import zou.identity.dao.UserDao;
import zou.identity.domain.Role;
import zou.identity.domain.User;
import zou.identity.service.RoleService;
@Service
@Transactional
public class RoleServiceImp implements RoleService {
	@Autowired
	RoleDao roledao;
	@Autowired
	UserDao userdao;
	
	@Override
	public Set<String> findRoleByUser(User user) {
		List<Role> role = user.getRole();
		
		HashSet<String> set = new HashSet<>();
		for (Role role2 : role) {
			set.add(role2.getId());
		}
	
		return role.isEmpty() == true ? null : set ;
	}

	@Override
	public List<Role> findAllRole() {
		
		return roledao.loadAll(Role.class);
	}

	@Override
	public List<Role> findRoleByUser(String id) {
		User user = userdao.get(User.class, id);
		return user.getRole();
	}

	

}
