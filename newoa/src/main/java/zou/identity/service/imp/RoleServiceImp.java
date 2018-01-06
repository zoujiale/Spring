package zou.identity.service.imp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import zou.common.vo.Message;
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

	@Override
	public List<Role> findContent(String findContent) {
		String context12 = "%%";
		if (findContent !=null) {
			 context12 = "%"+ findContent + "%";
		}
		
		List<Role> roles = roledao.findbyContent(context12);
	
		return roles;
	}

	@Override
	public Message addRole(String type, String name) {
		Role role = new Role();
		role.setName(name);
		List<Role> roles = roledao.findByExample(role);
		Message ms = new Message();
		// 数据库中查询存在的用户名
		if (!roles.isEmpty()) {
			Role role2 = roles.get(0);
			ms.setMessage("你的角色名字已被占用");
			ms.setState(false);
			// 找到名字相同但类型不相同的就修改
			if (!type.equals(role2.getType())) {
				role2.setType(type);
				ms.setMessage("修改类型成功");
				ms.setState(true);
			}
			return ms;
		}
		role.setType(type);
		this.roledao.save(role);
		ms.setMessage("角色创建成功");
		ms.setState(true);
		return ms;
	}

	@Override
	public void editRole(String id) {
		Role role = roledao.get(Role.class, id);
		roledao.delete(role);
	}

}
