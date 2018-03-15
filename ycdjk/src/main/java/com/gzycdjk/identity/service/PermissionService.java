package com.gzycdjk.identity.service;

import java.util.List;

import com.gzycdjk.identity.domain.Permission;

public interface PermissionService {


	Permission findByPermission(String id);

	List<Permission> findTopPermission();

}
