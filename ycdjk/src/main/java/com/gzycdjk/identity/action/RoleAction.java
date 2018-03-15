package com.gzycdjk.identity.action;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gzycdjk.commons.pojo.UserRole;
import com.gzycdjk.commons.vo.Message;
import com.gzycdjk.commons.vo.Page;
import com.gzycdjk.commons.vo.Pageable;
import com.gzycdjk.identity.domain.Role;
import com.gzycdjk.identity.domain.User;
import com.gzycdjk.identity.service.RoleService;
import com.gzycdjk.identity.service.UserService;

@Controller
@RequestMapping("/role")
public class RoleAction {

	@Resource
	RoleService roleservice;
	@Autowired
	UserService userservice;

	/**
	 * 获取角色页面
	 * 
	 * @return
	 */
	@GetMapping
	public String getRoleIndex() {
		return "/role/list";
	}

	@PostMapping
	@ResponseBody
	public Page<Role> getAllRole(@RequestParam("inputInfo") String inputInfo) {
		System.out.println(inputInfo);
		JSONObject jsonObject = JSON.parseObject(inputInfo);

		String roleName = jsonObject.getString("role.name");
		Page<Role> roles = this.roleservice.findRoleByQuery(roleName);
		return roles;
	}

	/**
	 * 试图
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/edit")
	public Role editRole(@RequestParam(value = "id", required = false) String id) {
		if (id != null) {
			this.roleservice.findRoleByid(id);
		}
		return null;
	}

	@PostMapping("/save")
	@ResponseBody
	public Message roleSave(@RequestParam("params") String params) {

		Role role = JSONObject.parseObject(params, Role.class);
		Message ms = new Message();
		// id 为空则新增
		if (role.getId() == null) {
			ms = this.roleservice.addRole(role);
		}
		// 不为空则编辑
		else {
			ms = this.roleservice.editRoleById(role);
		}

		return ms;
	}

	@PostMapping("/delete")
	@ResponseBody
	public Message deletRole(@RequestParam("id") String id) {
		Message ms = this.roleservice.deleteRoleById(id);
		return ms;
	}

	/**
	 * 获取一个根据角色
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/get")
	@ResponseBody
	public Role getRole(@RequestParam("id") String id) {
		return this.roleservice.findRoleByid(id);
	}

	/**
	 * 获取角色中用户
	 * 
	 * @return
	 */
	@PostMapping("/roleuser")
	@ResponseBody
	public Page<User> getRoleUser(@RequestParam(value = "id", required = false) String id,
			@RequestParam("inputInfo") String inputInfo,
			@RequestParam("pInfo")String pInfo
			) {
		Pageable page = JSON.parseObject(pInfo,Pageable.class);
		
		JSONObject jsonObject = JSON.parseObject(inputInfo);

		String userName = jsonObject.getString("user.name");
		Page<User> rl = this.roleservice.findRoleUserById(id,page,userName);
		return rl;

	}
	@GetMapping("/select")
	public ModelAndView UserroleIndex(@RequestParam("roleId")String roleId){
		ModelAndView view = new ModelAndView();
		view.addObject("role", roleId);
		view.setViewName("/role/userrole");	
 		return view;
		
	}
	
	@PostMapping("/usernotbound")
	@ResponseBody
	public Page<User> UserNotBound(@RequestParam(value = "id", required = false) String id,@RequestParam("pInfo")String pInfo,@RequestParam("inputInfo") String inputInfo){
		Pageable pga = JSON.parseObject(pInfo,Pageable.class);
		JSONObject object = JSON.parseObject(inputInfo);
		String userName = object.getString("userName");
		Page<User> pg = this.roleservice.UserNotBoundByRoleId(id,pga,userName);
		return pg;
	}
	
	@PostMapping("/userrole/save")
	@ResponseBody
	public Message UserBoundRole(@RequestParam("urlist")String urlist) {
		List<UserRole> parseArray = JSON.parseArray(urlist, UserRole.class);
		
		Message ms = this.roleservice.UserBoundRole(parseArray);
		return ms;
		
	}
	@PostMapping("/userrole/delete")
	@ResponseBody
	public Message UnbindRole(@RequestParam("urlist")String urlist) {
		
		List<UserRole> parseArray = JSON.parseArray(urlist, UserRole.class);
		Message ms = this.userservice.UnbindRoleById(parseArray);
		return ms;
		
	}
}
