package zou.identity.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import zou.common.vo.Message;
import zou.common.vo.Page;
import zou.identity.domain.Role;
import zou.identity.domain.User;
import zou.identity.service.RoleService;
import zou.identity.service.UserService;

@Controller
@RequestMapping("/role")
public class RoleAction {
	
	@Resource
	RoleService roleservice;
	@Autowired
	UserService userservice;
	/**
	 * 拿到全部角色信息，跟用户
	 * @return
	 */
	
	@GetMapping
	public ModelAndView getAllRole(@RequestParam(value = "findContent",
			required = false		)String findContent,
			@RequestParam(value = "pageNumber",
			required = false		)Integer pageNumber
			) {
		if (pageNumber ==null) {
			pageNumber = 1;
		}
		
		if ("".equals(findContent)) {
			findContent = null;
		}
		List<Role> roles = roleservice.findContent(findContent);
		Page<User> user = userservice.findAllUser(findContent, pageNumber);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("Role", roles);
		
		modelAndView.addObject("user", user);
		
		modelAndView.addObject("keyword",findContent);
		modelAndView.setViewName("role/roleoperation");
		return modelAndView;
	}
	/**
	 * 添加角色
	 * @param type
	 * @param name
	 * @return
	 */
	@PostMapping
	@ResponseBody
	public Message addRole(@RequestParam("type")String type,
			@RequestParam("name")String name
			) {
		Message ms = this.roleservice.addRole(type,name);
		return ms;
	}
	
	@GetMapping(value = "/edit/{id}")
	public ModelAndView editRole(@PathVariable("id")String id) {
		this.roleservice.editRole(id);
		return new ModelAndView("redirect:/identity/role");
		
	}
	
}
