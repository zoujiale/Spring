package com.gzycdjk.identity.service.impl;

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
	public Permission findByPermission(String id) {
		Permission ps = new Permission();
		
		Permission permission = permissiondao.get(Permission.class, id);
		
		ps.setId(permission.getId());
		ps.setOrderNumber(permission.getOrderNumber());
		ps.setParent(permission.getParent());
		ps.setPercode(permission.getPercode());
		ps.setRoles(permission.getRoles());
		ps.setText(permission.getText());
		ps.setUrl(permission.getUrl());
		return ps; 
	}
	@Override
	public List<Permission> findTopPermission() {
		return this.permissiondao.findTopPermission();
	}
	
	
}
