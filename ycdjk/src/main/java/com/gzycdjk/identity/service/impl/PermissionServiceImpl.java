package com.gzycdjk.identity.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzycdjk.identity.dao.PermissionDao;
import com.gzycdjk.identity.domain.Permission;
import com.gzycdjk.identity.service.PermissionService;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService{
	@Autowired
	PermissionDao permissiondao;
	@Override
	public List<Permission> findAllPermission() {
		List<Permission> ps = this.permissiondao.findParentsId();
		return ps ;
	}
	
	
	
}
