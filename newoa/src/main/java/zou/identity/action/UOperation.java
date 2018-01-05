package zou.identity.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import zou.common.vo.Message;
import zou.common.vo.Page;
import zou.identity.domain.User;
import zou.identity.service.UserService;

@Controller
@RequestMapping("/operation")
@SessionAttributes(value = {"MESSAGE","USER_SESSION"})
public class UOperation {
	@Autowired
	UserService userservice; 
	

	
	
	@GetMapping
	public ModelAndView getAllUser(
			@RequestParam(value = "keyword", required = false) String keyword, //
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber//
			) {
		if (pageNumber == null) {
			// 默认第0页，因为使用了spring-data-commons里面的分页对象，第一页是从0开始的
			pageNumber = 1;
		} else {
			// 页面传过来的时候，已经就是减少了1的，可以直接使用。
			// 如果页面传过来的是从1开始的页码，则需要在这里减一！
		}

		if ("".equals(keyword)) {
			// 关键字是一个空字符，不需要使用关键字查询
			keyword = null;
		}

		
		Page<User> user2 = userservice.findAllUser(keyword,pageNumber);
		
		ModelAndView view = new ModelAndView();
		
		view.setViewName("/operation/information");
		
		view.addObject("user", user2);
		return view;
	}
	
	
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
	
	@RequestMapping(value = "/edittrue/{id}", method = RequestMethod.GET)
	public ModelAndView editStateTrue(@PathVariable("id")String id) {
		System.out.println(id);
		userservice.editState(id);
		return new ModelAndView ("redirect:/identity/operation");
	}
	
	@RequestMapping(value = "/editflase/{id}", method = RequestMethod.GET)
	public ModelAndView editStateFlase(@PathVariable("id")String id) {
		userservice.editStateFalse(id);
		return new ModelAndView ("redirect:/identity/operation");
	}
}