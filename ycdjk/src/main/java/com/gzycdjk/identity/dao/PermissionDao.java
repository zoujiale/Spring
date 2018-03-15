package com.gzycdjk.identity.dao;

import java.util.List;

import com.gzycdjk.commons.dao.CommonDao;
import com.gzycdjk.identity.domain.Permission;

public interface PermissionDao extends CommonDao<Permission> {


	List<Permission> findTopPermission();

}
