package com.gzycdjk.identity.service;

import java.util.List;

import com.gzycdjk.commons.pojo.Menu;
import com.gzycdjk.commons.pojo.TreeNode;
import com.gzycdjk.commons.vo.Message;
import com.gzycdjk.identity.domain.Permission;

public interface PermissionService {


	Permission findByPermission(String id);
	/**
	 * 找到一级菜单
	 * @return
	 */
	List<Permission> findTopPermission();


	/**
	 * 保存或者编辑权限
	 * @param ps
	 * @param parentid
	 * @return
	 */
	Message savePermission(Permission ps, String parentid);
	
	/**
	 * 生成菜单树
	 * @return
	 */
	List<TreeNode> createTreeNode();

}
