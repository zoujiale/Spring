package com.gzycdjk.identity.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzycdjk.commons.pojo.Menu;
import com.gzycdjk.commons.pojo.TreeNode;
import com.gzycdjk.commons.vo.Message;
import com.gzycdjk.identity.domain.Permission;
import com.gzycdjk.identity.service.PermissionService;


@Controller
@RequestMapping("/permission")
public class PermissionAction {
	
	@Autowired
	PermissionService permissionservice;
	
	@GetMapping
	public ModelAndView getMenuList() {
		return new ModelAndView("/permission/list");
	}
	/**
	 * 获取生成树
	 * @return
	 */
	@PostMapping("/treedata")
	@ResponseBody
	public List<TreeNode> getPermission(){
		
		return this.permissionservice.createTreeNode();
		
	}
	/**
	 * 获取单个菜单信息
	 * @param id
	 * @return
	 */
	@PostMapping("/get/{id}")
	@ResponseBody
	public Permission getPermissionById(@PathVariable("id") String id) {
		Permission ps = this.permissionservice.findByPermission(id);
	
		return ps;
		
		
	}
	/**
	 * 菜单保存
	 * @param ps
	 * @param parentid
	 * @return
	 */
	@PostMapping("/save")
	@ResponseBody
	public Message savePermission(Permission ps,@RequestParam("parent_id") String parentid
			) {
		Message ms = this.permissionservice.savePermission(ps,parentid);
				return ms;
		
	}
	
	
	/**
	 * 返回角色授权试图
	 * @return
	 */
	@GetMapping("/authorization")
	public ModelAndView getAuthorization() {
		return new ModelAndView("/permission/authorization");
	}
	/**
	 * 根据角色Id获取权限(生成树)
	 * @param roleId
	 * @return
	 */
	@PostMapping("/listtree/{roldId}")
	@ResponseBody
	public List<TreeNode> getTreeNodeByRoleId(@PathVariable("roldId")String roleId){
		System.out.println(roleId+"角色Id");
		return this.permissionservice.getTreeNodeByRoleId(roleId);
		
	}
}
