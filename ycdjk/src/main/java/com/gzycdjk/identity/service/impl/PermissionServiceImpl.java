package com.gzycdjk.identity.service.impl;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.gzycdjk.commons.pojo.Menu;
import com.gzycdjk.commons.until.ObjectComparison;
import com.gzycdjk.commons.vo.Message;
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

	@Override
	public Message savePermission(Permission ps, String parentid) {
	
		
		// 新增一个菜单
		if ("".equals(ps.getId())) {
			Permission permission = new Permission();
			permission.setOrderNumber(ps.getOrderNumber());
			// 父级菜单
			Permission load = null;
			if (!"".equals(parentid)) {
				load= this.permissiondao.load(Permission.class, parentid);
			}
		
			
			permission.setParent(load);
			permission.setPercode("crud");
			permission.setText(ps.getText());
			permission.setUrl(ps.getUrl());
			this.permissiondao.save(permission);
		}else 
		// 编辑或者保存菜单
		{
			Permission permission = this.permissiondao.load(Permission.class, ps.getId());
			permission.setOrderNumber(ps.getOrderNumber());
			permission.setPercode("crud");
			permission.setText(ps.getText());
			permission.setUrl(ps.getUrl());
		}
		
		
		return new Message("保存或编辑成功",true);
	}

	
	
	
}
