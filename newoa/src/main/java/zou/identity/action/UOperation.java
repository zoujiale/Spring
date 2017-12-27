package zou.identity.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import zou.common.shiro.TokenManager;
import zou.common.vo.Message;
import zou.identity.domain.User;
import zou.identity.service.UserService;

@Controller
@RequestMapping("/operation")
@SessionAttributes(value = {"MESSAGE","USER_SESSION"})
public class UOperation {
	@Autowired
	UserService userservice; 
	
	@PostMapping
	public ModelAndView UpdateUser(User user,ModelMap mp) {
		ModelAndView view = new ModelAndView();
		Message us = userservice.UserSave(user);
		view.addObject("MESSAGE", us);
		mp.remove("USER_SESSION");
		User user2 = this.userservice.findUserByid(user.getId());
		view.addObject("USER_SESSION", user2);
		view.setViewName("redirect:/commons/index");
		return view;
	}
}
