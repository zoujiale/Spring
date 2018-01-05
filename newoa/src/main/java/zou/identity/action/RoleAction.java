package zou.identity.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import zou.identity.domain.Role;
import zou.identity.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleAction {
	
	@Resource
	RoleService roleservice;
	
	@GetMapping
	public ModelAndView getAllRole() {
		ModelAndView modelAndView = new ModelAndView();
		List<Role> roles = roleservice.findAllRole();
		modelAndView.addObject("Role", roles);
		modelAndView.setViewName("role/roleoperation");
		return modelAndView;
	}
}
