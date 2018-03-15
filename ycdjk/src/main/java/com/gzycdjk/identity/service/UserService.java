package com.gzycdjk.identity.service;

import java.util.List;
import java.util.Map;

import com.gzycdjk.commons.pojo.UserRole;
import com.gzycdjk.commons.vo.Message;
import com.gzycdjk.commons.vo.Page;
import com.gzycdjk.commons.vo.Pageable;
import com.gzycdjk.identity.domain.User;

public interface UserService {
	/**
	 * 查询用户名 跟密码
	 * @param username
	 * @param password
	 * @return
	 */
	User findByUserName(String username, String password);
	
	
	/**
	 * 根据id获得User
	 * @param id
	 * @return
	 */
	User findByUserId(String id);

	/**
	 * 获得所有的用户
	 * @return
	 */
	List<User> findAllUser();

	Long countData();
	
	/**
	 * 根据条件
	 * @param page
	 * @param conditions
	 * @param loginName 
	 * @return
	 */
	
	Page<User> findByUserQuery(Pageable page, String name, String loginName);

	/**
	 * 添加用户并提供错误代码成功
	 * @param user
	 * @return
	 */
	Map<Integer, String> saveUser(User user);


	Map<Integer, String> editUser(User user);


	Message deleteUserById(String id);


	Message UnbindRoleById(List<UserRole> parseArray);



}
