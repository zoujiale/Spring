package com.gzycdjk.identity.action;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.gzycdjk.commons.vo.Message;
import com.gzycdjk.commons.vo.Page;
import com.gzycdjk.commons.vo.Pageable;
import com.gzycdjk.identity.domain.User;
import com.gzycdjk.identity.service.UserService;

@Controller
@RequestMapping("/management")
public class ManagementAction {
	
	@Autowired
	UserService userservice;
	
	@GetMapping
	public ModelAndView getindex() {
		return new ModelAndView("/management/index");
		
	}
	
	/**
	 * 获得所有用户
	 * @return
	 */
	@PostMapping("/loadData")
	@ResponseBody
	public Page<User> getAllUser(String pInfo, 
			@RequestParam("name")String name,
			@RequestParam("loginName")String loginName	){
		
		
		
		Pageable page = JSON.parseObject(pInfo,Pageable.class);
		
		
		Page<User> pg = this.userservice.findByUserQuery(page,name,loginName);
		
		return pg;
		
	}
	
	/**
	 * 用户编辑
	 * @param id
	 * @return
	 */
	
	@PostMapping("/edit")
	@ResponseBody
	public User editUser(@RequestParam("id")String id) {
		
		User user= this.userservice.findByUserId(id);
		return user;
		
	}
	
	/**
	 * 编辑用户,添加用户
	 * @param paarams
	 * @return
	 */
	@PostMapping("/save")
	@ResponseBody
	public Map<Integer,String> savaUser(@RequestParam ("paarams")String paarams){
		
		User user = JSON.parseObject(paarams,User.class);
		
		// 如果id为空则新增
		if (user.getId() == null) {
			Map<Integer,String > mp = this.userservice.saveUser(user);
			return mp;
		}
		// 不为空就修改用户
		Map<Integer,String > mp = this.userservice.editUser(user);
		return mp;
	}
	
	/**
	 * 这里是根据id删除user
	 * @param id
	 * @return
	 */
	@PostMapping("/delete")
	@ResponseBody
	public Message deleteUser(@RequestParam("id") String id) {
		
		Message ms = this.userservice.deleteUserById(id);
		return ms;
	}
	
	
}
