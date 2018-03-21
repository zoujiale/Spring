package com.gzycdjk.identity.service.impl;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.gzycdjk.commons.pojo.Menu;
import com.gzycdjk.commons.pojo.TreeNode;
import com.gzycdjk.commons.until.ObjectComparison;
import com.gzycdjk.commons.until.TreeUntil;
import com.gzycdjk.commons.vo.Message;
import com.gzycdjk.identity.dao.PermissionDao;
import com.gzycdjk.identity.dao.RoleDao;
import com.gzycdjk.identity.domain.Permission;
import com.gzycdjk.identity.domain.Role;
import com.gzycdjk.identity.service.PermissionService;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	PermissionDao permissiondao;
	@Autowired
	RoleDao roledao;

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
	public Message savePermission(Permission ps, String parentid) {

		// 新增一个菜单
		if ("".equals(ps.getId())) {
			Permission permission = new Permission();
			permission.setOrderNumber(ps.getOrderNumber());
			// 父级菜单
			Permission load = null;
			if (!"".equals(parentid)) {
				load = this.permissiondao.load(Permission.class, parentid);
			}

			permission.setParent(load);
			permission.setPercode("crud");
			permission.setText(ps.getText());
			permission.setUrl(ps.getUrl());
			this.permissiondao.save(permission);
		} else
		// 编辑或者保存菜单
		{
			Permission permission = this.permissiondao.load(Permission.class, ps.getId());
			permission.setOrderNumber(ps.getOrderNumber());
			permission.setPercode("crud");
			permission.setText(ps.getText());
			permission.setUrl(ps.getUrl());
		}

		return new Message("保存或编辑成功", true);
	}

	@Override
	public List<TreeNode> createTreeNode() {
		List<Permission> permission = this.permissiondao.loadAll(Permission.class);
		Map<String, TreeNode> mp = new LinkedHashMap<>();
		for (Permission ps : permission) {
			TreeNode node = new TreeNode();
			node.setId(ps.getId());
			node.setText(ps.getText());
			node.setOrderNumber(ps.getOrderNumber());
			if (ps.getParent() == null) {
				node.setParentId(null);
			}else {
				node.setParentId(ps.getParent().getId());
			}
			mp.put(node.getId(), node);
		}
		return TreeUntil.getTreeNodeList(mp);
	}

	@Override
	public List<TreeNode> getTreeNodeByRoleId(String roleId) {
		Role rl = this.roledao.load(Role.class, roleId);
		Map<String,TreeNode> mp = new LinkedHashMap<>();
		Set<Permission> permissions = rl.getPermissions();
		for (Permission permission : permissions) {
				TreeNode node = new TreeNode();
				node.setId(permission.getId());
				node.setText(permission.getText());
				node.setOrderNumber(permission.getOrderNumber());
				if (permission.getParent() == null) {
					node.setParentId(null);
				}else {
					node.setParentId(permission.getParent().getId());
				}
				mp.put(node.getId(), node);
		}
		
		return TreeUntil.getTreeNodeList(mp);
	}

}
