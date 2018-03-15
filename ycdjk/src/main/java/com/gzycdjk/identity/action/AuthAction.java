package com.gzycdjk.identity.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gzycdjk.commons.vo.Message;
import com.gzycdjk.identity.domain.User;

@Controller
@RequestMapping("/auth")
@SessionAttributes("user")
public class AuthAction {

	private static Logger LG = LogManager.getLogger();
	

	/**
	 * 打开登录网站
	 * 
	 * @return
	 */

	@GetMapping
	public ModelAndView getlogin() {
		return new ModelAndView("/auth/index");
	}
	
	/**
	 * 登录的验证
	 * @param username
	 * @param password
	 * @param req
	 * @return
	 */
	@PostMapping
	@ResponseBody
	public Message loginIn(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpServletRequest req,@RequestParam(value = "remember",required = false) String remember,Model md
			) {
		Boolean rememberState = false;
		
		// 如果勾选记住你设置为true
		if ("1".equals(remember)) {
			rememberState = true;
		}
		
		Message ms = new Message();
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(rememberState);
		
		
		//拿到访问之前的url,因为后台管理页面太少这个先搁置
		String requestUri = WebUtils.getRequestUri(req);
		
		if (StringUtils.isEmpty(requestUri)) {
			requestUri = req.getContextPath() + "/commons/index";
		}
		
		
		// 经过Shiro的自定义验证
		try {
			
			subject.login(token);
			User principal = (User) subject.getPrincipal();
			md.addAttribute("user", principal);
			LG.info("添加用户到Session里面" + principal.getUserName());
			ms.setState(true);
			ms.setMessage(requestUri);
		} catch (AuthenticationException e) {
			ms.setMessage(e.getMessage());
			ms.setState(false);
		}

		return ms;
	}
	/**
	 * token的清楚功能
	 * @return
	 */
	@RequestMapping("/loginout")
	public String loginOut(ModelMap mp) {
		
		Subject subject = SecurityUtils.getSubject();
		
		subject.logout();
		mp.remove("user");
		return "redirect:/identity/auth/";
	}
}
