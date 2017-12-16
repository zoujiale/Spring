package zou.identity.action;

import javax.jws.WebService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

/**
 * 这是登录页面s
 * @author zou
 *
 */
@Controller
@RequestMapping("/login")
public class LoginAction  {

	@GetMapping
	public String loginIn() {
		return "auth/login";
	}
	@RequestMapping("/loginout")
	public String loginout(WebRequest webrequest ) {
		return "redirect:/identity/login";
	}

}
