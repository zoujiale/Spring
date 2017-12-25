package zou.identity.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/down")
public class DownAction {
	@GetMapping
	public ModelAndView getDownList() {
		return new ModelAndView("/down/list");
	}
}
