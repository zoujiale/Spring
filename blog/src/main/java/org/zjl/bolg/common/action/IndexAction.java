package org.zjl.bolg.common.action;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.zjl.bolg.common.service.IndexService;
import org.zjl.bolg.identity.domain.Article;

/**
 * @ClassName: IndexAction
 * @Description: 首页的跳转。
 * @author zou
 * @date 2017年10月17日 下午4:01:41
 * 
 */
@Controller
@RequestMapping("/index")
@SessionAttributes("sessionArticle")
public class IndexAction {	

	@Resource
	private IndexService indexservice; 
	
	/**
	 * 回到主页面
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(value="pageNumber" ,required = false)Integer pagenumber ) {
		ModelAndView modelview = new ModelAndView();
		
		if (pagenumber==null) {
			pagenumber = 0;
		}else {
		}
		
	   Page<Article> article = this.indexservice.findArticle(pagenumber);
		
		modelview.addObject("sessionArticle", article);
		modelview.setViewName("user/index");
	   
		return modelview;
	}

	

}
