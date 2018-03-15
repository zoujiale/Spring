package com.gzycdjk.identity.dao;

import com.gzycdjk.commons.dao.CommonDao;
import com.gzycdjk.commons.vo.Page;
import com.gzycdjk.commons.vo.Pageable;
import com.gzycdjk.identity.domain.Role;
import com.gzycdjk.identity.domain.User;

public interface RoleDao extends CommonDao<Role>{

	Page<Role> findRoleByQuery(String roleName);


}
