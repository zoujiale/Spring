package zou.common.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/index")
public class IndexAction {

	@GetMapping
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");

		return mav;
	}
}
