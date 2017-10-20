package org.zjl.bolg.identity.action;

import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.zjl.bolg.common.until.CaptchaServiceSingleton;
import org.zjl.bolg.common.until.Md5Encryption;
import org.zjl.bolg.common.vi.Message;
import org.zjl.bolg.identity.domain.User;
import org.zjl.bolg.identity.service.LoginService;

/** 
* @ClassName: AuthoAction 
* @Description: 授权管理，登陆等操作
* @author zou
* @date 2017年10月14日 上午11:41:26 
* 
*/
@Controller
@RequestMapping("/auth")
@SessionAttributes("userAll")
public class AuthoAction {
	
	@Resource
	private LoginService loginservice;
	
	@RequestMapping(method = RequestMethod.GET)
	public String login(){
		return "auth/login";
	}
	
	
	/**
	 * 登陆的Ajax操作
	 * 
	 * @param username
	 *            登陆的名称
	 * @param password
	 *            密码
	 * @param code
	 *            验证码
	 * @param session
	 *            HttpSession 为了获取Session里的验证码
	 * @param model
	 *            为了把登陆资料传递到主页面
	 * @return
	 */

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Message login(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("code") String code, HttpServletRequest req, Model model) {

		Message ms = new Message();
		
		// 拿到session的id
		String sessionCode = req.getSession().getId();

		// 用sessionid 跟 传过来的code 回返回是不是激活成功
		Boolean codeTr = CaptchaServiceSingleton.getInstance().validateResponseForID(sessionCode, code);
		System.out.println(codeTr);
		// 判断我的验证码是否跟提交的验证码一致
		if (codeTr == false) {
			ms.setState(false);
			ms.setMessage("验证码错误");
			return ms;
		}

		Boolean LoginName = this.loginservice.getuserName(username);
		if (LoginName == false) {
			ms.setState(false);
			ms.setMessage("账户或者密码输入错误");
			return ms;
			
		}
		
		// 加密密码去数据库校验
		try {
			// 加密前先吧前台传递过来的去除下空格
			String Md5 = Md5Encryption.getmd5Encryption(password.trim());
			
			// 确定账户名存在 然后看密码是否也正确
			User userAll = this.loginservice.getuserAll(Md5, LoginName);

			if (userAll != null) {
				ms.setState(true);
				// 验证账号密码均正确把Session带到主页面
				model.addAttribute("userAll", userAll);
				return ms;
				
			}
				ms.setState(false);
				ms.setMessage("账户或者密码输入错误");
				return ms;
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 *  清空UserSession
	 * @return
	 * 跳转回主页面
	 */
	
	@RequestMapping("/loginout")
	public String loginOut(SessionStatus status) {
		status.setComplete();
		return "redirect:/common/index";
	}
}
