package zou.identity.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.octo.captcha.service.image.ImageCaptchaService;

import zou.common.shiro.TokenManager;
import zou.common.until.CaptchaServiceSingleton;
import zou.common.vo.Message;

/**
 * 这是登录页面s
 * @author zou
 *
 */
@Controller
@RequestMapping("/login")
public class LoginAction  {

	@GetMapping
	public String login() {
		return "auth/login";
	}
	
	@PostMapping
	@ResponseBody
	public Message loginIn(@RequestParam("username")String username,
			@RequestParam("password")String passord,
			@RequestParam("code")String code,HttpServletRequest req,Model md) {
			String code1 = code.trim();
			String id = TokenManager.getSessionId();
			

			
			
			
			Boolean boolean1 = CaptchaServiceSingleton.getInstance().validateResponseForID(id, code1);
			
		
			Message ms = new Message();
			
			if (boolean1 == false && code.trim() != null) {
				ms.setMessage("验证码错误,请重新输入");
				ms.setState(false);
				return ms;
			}
			
			Subject subject = SecurityUtils.getSubject();
			
			UsernamePasswordToken token = new UsernamePasswordToken(username,passord);
		
			// 拿到上次请求了什么url
			SavedRequest request = WebUtils.getSavedRequest(req);
			
			String requestUrl = null;
			if (request!=null) {
				 requestUrl = request.getRequestUrl();
			}
			
			if (StringUtils.isEmpty(requestUrl)) {
				requestUrl = req.getContextPath() + "/commons/index";
			}
			
			try {
				 subject.login(token);
				 ms.setMessage(requestUrl);
				 ms.setState(true);
				 return ms;
			}catch (DisabledAccountException e) {
				ms.setMessage(e.getLocalizedMessage());
				ms.setState(false);
				return ms;
			}
			catch (AuthenticationException e) {
				ms.setMessage(e.getLocalizedMessage());
				ms.setState(false);
				return ms;
			}
			
	}
	
	@RequestMapping("/loginout")
	public String loginout( ) {
		TokenManager.SessionOut();
		return "redirect:/identity/login";
	}

}
