package com.gzycdjk.identity.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzycdjk.commons.pojo.UserRole;
import com.gzycdjk.commons.shiro.CustomRealm;
import com.gzycdjk.commons.until.ObjectComparison;
import com.gzycdjk.commons.vo.Message;
import com.gzycdjk.commons.vo.Page;
import com.gzycdjk.commons.vo.Pageable;
import com.gzycdjk.identity.dao.RoleDao;
import com.gzycdjk.identity.dao.UserDao;
import com.gzycdjk.identity.domain.Role;
import com.gzycdjk.identity.domain.User;
import com.gzycdjk.identity.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	private static Logger LOG = LogManager.getLogger(UserServiceImpl.class);
    
	@Autowired
	private UserDao userdao;
	
	@Autowired
	private RoleDao roledao;
	
	@Override
	public User findByUserName(String username, String password) {
		
	
		User user = new User();
		user.setLoginName(username);
		
		List<User> users = userdao.findByExample(user);
		
		if (users.isEmpty()) {
			return null;
		}
		// 用户名是存在的
		User user2 = users.get(0);
		LOG.debug( "数据库传来的密码{"+ user2.getPassword()+"}前台加密后的密码" + password   );
		
		// 密码也正确
		if (user2.getPassword().equals(password)) {
			return user2;
		}
		
		return null;
	}

	@Override
	public User findByUserId(String id) {
		return this.userdao.get(User.class, id);
	}

	@Override
	public List<User> findAllUser() {
		return this.userdao.loadAll(User.class);
	}

	@Override
	public Long countData() {
		return this.userdao.CountNumber();
	}

	

	@Override
	public Page<User> findByUserQuery(Pageable page, String name, String loginName) {
		if (!"".equals(name) ) {
			name = "%" + name + "%";
		}
		if(!"".equals(loginName)) {
			loginName = "%" + loginName + "%";
		}
		
		Page<User> pg= this.userdao.findUserByConditions(page,name,loginName);
	
		return pg;
	}

	@Override
	public Map<Integer, String> saveUser(User user) {
		User user2 = new User();
		user.setEnable(true);
		user.setPassword(CustomRealm.md5Pwd(user.getPassword(), user.getUserName()));
		HashMap<Integer, String> mp = new HashMap<>();
		try {
			ObjectComparison.comparison(user2, user);
			this.userdao.save(user2);
			mp.put(200, "注册成功");
		} catch (Exception e) {
			mp.put(500, "服务器出错,请重新操作"); 
		}
		
		return mp;
	}

	@Override
	public Map<Integer, String> editUser(User user) {
		HashMap<Integer, String> map = new HashMap<>();
		try {
			User user2 = this.userdao.get(User.class, user.getId());
			String pwd = CustomRealm.md5Pwd(user.getPassword(), user.getUserName());
			// 这里是密码有修改 ,Md5加密后放到数据库
			if (!pwd.equals(user2.getPassword())) {
				
				user.setPassword(pwd);
			}
			ObjectComparison.comparison(user2, user);
			map.put(200, "修改成功");
		} catch (Exception e) {
			map.put(500, "服务器错误请重新再编辑");
		}
	
		return map;
	}

	@Override
	public Message deleteUserById(String id) {
		User user = this.userdao.get(User.class, id);
		Message ms = new Message();
		
		try {
			userdao.delete(user);
			ms.setMessage("删除成功");
			ms.setState(true);
			
		} catch (Exception e) {
			ms.setState(false);
			ms.setMessage("服务器出错");
		}
		
		
		return ms;
	}

	@Override
	public Message UnbindRoleById(List<UserRole> parseArray) {
		Message ms = new Message("解绑失败",false);
		
		if (parseArray.isEmpty()) {
			return ms;
		}
	
		
		
		for (int i = 0; i < parseArray.size(); i++) {
			UserRole role = parseArray.get(i);
			User role2 = this.userdao.load(User.class,role.getUser().getId() );
			Role role3 = this.roledao.load(Role.class, role.getRoleId());
			role2.getRoles().remove(role3);
			role3.getUsers().remove(role2);
		}
		ms.setMessage("成功");
		ms.setState(true);
		return ms;
	}

	
	
	

}
