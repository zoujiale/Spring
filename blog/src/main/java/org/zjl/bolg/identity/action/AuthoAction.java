package org.zjl.bolg.identity.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** 
* @ClassName: AuthoAction 
* @Description: 授权管理，登陆等操作
* @author zou
* @date 2017年10月14日 上午11:41:26 
* 
*/
@Controller
@RequestMapping("/auth")
public class AuthoAction {
	
	@RequestMapping(method = RequestMethod.GET)
	public String login(){
		return "auth/login";
	}
}
