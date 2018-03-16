package com.gzycdjk.identity.service.impl;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzycdjk.commons.pojo.Menu;
import com.gzycdjk.commons.until.ObjectComparison;
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
		Permission permission = permissiondao.load(Permission.class, id);
		Permission ps = new Permission();
		
		try {
			ObjectComparison.comparison(ps, permission);
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (NoSuchMethodException e) {
		} catch (SecurityException e) {
		} catch (IllegalArgumentException e) {
		} catch (InvocationTargetException e) {
		} catch (IntrospectionException e) {
		}
		return ps; 
	}
	@Override
	public List<Permission> findTopPermission() {
		return this.permissiondao.findTopPermission();
	}
	@Override
	public Menu test(String id) {
		Permission permission = permissiondao.load(Permission.class, id);
		Menu menu = new Menu();
		menu.setNodes(permission.getNodes());
		menu.setParent(permission.getParent());
		return menu;
	}
	
	
}
