package com.gzycdjk.commons.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexAction {
	
	/**
	 * 获得首页
	 * @return
	 */
	
	@GetMapping
	public String getindex() {
		return "/index/index";
	}
}
