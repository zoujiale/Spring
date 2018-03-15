package com.gzycdjk.identity.dao;

import java.util.List;

import com.gzycdjk.commons.dao.CommonDao;
import com.gzycdjk.commons.vo.Page;
import com.gzycdjk.commons.vo.Pageable;
import com.gzycdjk.identity.domain.User;

public interface UserDao extends CommonDao<User> {
	
	/**
	 * 根据条件执行模糊查询
	 * @param page
	 * @param name
	 * @param loginName
	 * @return
	 */
	Page<User> findUserByConditions(Pageable page, String name, String loginName);


	Page<User> findUserQuery(Pageable page, String id, String userName);


	Page<User> findUserByRoleId(Pageable pga, String id, String userName);

}
