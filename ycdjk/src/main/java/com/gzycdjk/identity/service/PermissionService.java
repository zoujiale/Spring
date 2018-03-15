package com.gzycdjk.identity.service;

import java.util.List;

import com.gzycdjk.identity.domain.Permission;

public interface PermissionService {

	List<Permission> findAllPermission();

}
