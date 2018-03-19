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
	
	@PostMapping("/treedata")
	@ResponseBody
	public List<Permission> getPermission(){
		
		return this.permissionservice.findTopPermission();
		
	}
	
	@PostMapping("/get/{id}")
	@ResponseBody
	public Permission getPermissionById(@PathVariable("id") String id) {
		Permission ps = this.permissionservice.findByPermission(id);
	
		return ps;
		
		
	}
	
	@PostMapping("/save")
	@ResponseBody
	public Message savePermission(Permission ps,@RequestParam("parent_id") String parentid
			) {
		Message ms = this.permissionservice.savePermission(ps,parentid);
				return ms;
		
	}
}
