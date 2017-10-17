package org.zjl.bolg.common.action;

import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.zjl.bolg.common.service.IndexService;
import org.zjl.bolg.common.until.CaptchaServiceSingleton;
import org.zjl.bolg.common.until.Md5Encryption;
import org.zjl.bolg.common.vi.Message;
import org.zjl.bolg.identity.domain.User;

import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;

/**
 * @ClassName: IndexAction
 * @Description: 首页的跳转。登陆的操作
 * @author zou
 * @date 2017年10月17日 下午4:01:41
 * 
 */
@Controller
@RequestMapping("/index")
@SessionAttributes("userAll")
public class IndexAction {

	@Resource
	private IndexService indexservice;

	/**
	 * 回到主页面
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "user/index";
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
			ms.setMessage("验证码为空或者错误");
			return ms;
		}

		Boolean LoginName = this.indexservice.getuserName(username);
		boolean checkLogin = true;
		if (LoginName = false) {
			checkLogin = false;
			ms.setState(false);
			ms.setMessage("账户或者密码输入错误");
			return ms;
			
		}
		
		// 加密密码去数据库校验
		try {
			// 加密前先吧前台传递过来的去除下空格
			String Md5 = Md5Encryption.getmd5Encryption(password.trim());
			
			// 确定账户名存在 然后看密码是否也正确
			User userAll = this.indexservice.getuserAll(Md5, checkLogin);

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

}
