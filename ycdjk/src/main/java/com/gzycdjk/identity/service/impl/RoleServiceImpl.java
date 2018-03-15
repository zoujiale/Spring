package com.gzycdjk.identity.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzycdjk.commons.pojo.UserRole;
import com.gzycdjk.commons.until.ObjectComparison;
import com.gzycdjk.commons.vo.Message;
import com.gzycdjk.commons.vo.Page;
import com.gzycdjk.commons.vo.Pageable;
import com.gzycdjk.identity.dao.RoleDao;
import com.gzycdjk.identity.dao.UserDao;
import com.gzycdjk.identity.domain.Role;
import com.gzycdjk.identity.domain.User;
import com.gzycdjk.identity.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{
	
	private static Logger LG = LogManager.getLogger(RoleServiceImpl.class);
	
	@Resource
	RoleDao roledao;
	@Autowired
	UserDao userdao;
	
	@Override
	public Message addRole(Role role)  {
		
		Message ms = new Message();
		Role role2 = new Role();
		try {
			ObjectComparison.comparison(role2, role);
			this.roledao.save(role2);
			ms.setState(true);
			ms.setMessage("保存成功");
			LG.debug("对象比较完毕");
		} catch (Exception e) {
			LG.debug("对象比较出错" + e);
			ms.setState(false);
			ms.setMessage("你的角色保存出错了");
		}

		return ms;
	}

	@Override
	public Role findRoleByid(String id) {
		return roledao.get(Role.class, id);
	}

	@Override
	public List<Role> findAllRole() {
		return this.roledao.loadAll(Role.class);
	}

	@Override
	public Page<Role> findRoleByQuery(String roleName) {
		if (!"".equals(roleName)) {
			roleName = "%" + roleName + "%";
		} 
		
		Page<Role> rs  = this.roledao.findRoleByQuery(roleName);
		return rs;
	}

	@Override
	public Message editRoleById(Role role) {
		Role role2 = this.roledao.get(Role.class, role.getId());
		Message ms;
		try {
			ObjectComparison.comparison(role2, role);
			 ms = new Message("保存成功", true);
		} catch (Exception e) {
			// TODO: handle exception
			ms=new Message("保存失败",false);
		}
		return ms;
	}

	@Override
	public Message deleteRoleById(String id) {
			Message ms = new Message("角色删除成功",true);
		try {
			Role role = this.roledao.load(Role.class, id);
			this.roledao.delete(role);
		} catch (Exception e) {
			// TODO: handle exception
			ms.setMessage("删除失败,可能不存在了");
			ms.setState(false);
		}
		return ms;
	}

	@Override
	public Page<User> findRoleUserById(String id, Pageable page, String userName) {
		if (!"".equals(userName)) {
			userName = "%" + userName + "%";
		}
		System.out.println(userName);
		Page<User> pu = this.userdao.findUserQuery(page, id,userName);
		
		return pu;
	}

	@Override
	public Page<User> UserNotBoundByRoleId(String id, Pageable pga, String userName) {
		if (!"".equals(userName)) {
			userName = "%" + userName + "%";
		}
		Page<User> pg= this.userdao.findUserByRoleId(pga,id,userName);
		return pg;
	}

	@Override
	public Message UserBoundRole(List<UserRole> parseArray) {
		Set<Role> se = new HashSet<>();
		Message message = new Message("成功",true);
		
		try {
			
			for (int i = 0; i < parseArray.size(); i++) {
				UserRole role = parseArray.get(i);
				User user = userdao.get(User.class, role.getUser().getId());
				Role rl = roledao.get(Role.class, role.getRoleId());		
				
				user.getRoles().add(rl);
				rl.getUsers().add(user);
				
			}
			
		} catch (Exception e) {
			message.setMessage("保存失败");
			message.setState(false);
		}
	
		
		
		return message;
	}

	@Override
	public Set<Role> findRoleByUser(User us) {
		User user = this.userdao.get(User.class, us.getId());
		Set<Role> roles = user.getRoles();
		return roles;
	}

	

	
	


}
