package com.gzycdjk.identity.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gzycdjk.commons.vo.Message;
import com.gzycdjk.commons.vo.Page;
import com.gzycdjk.identity.domain.Article;
import com.gzycdjk.identity.service.ArticleService;

/**
 * 新闻发布模块
 * @author YCJKmr.zo
 *
 */

@Controller
@RequestMapping("/news")
public class NewsAction  {
	
	
	@Resource
	private ArticleService articleservice;
	
	/**
	 * 把公司新闻内容分页处理
	 * @param pageNumber
	 * @return
	 */
	@GetMapping
	public ModelAndView getAllNews(
			@RequestParam( value = "pageNum" ,required = false) Integer pageNumber
			
			) {
		
		if (pageNumber == null) {
			pageNumber = 1;
		}
		
		Page<Article> articles = this.articleservice.findQueryArticle(pageNumber);
		ModelAndView view = new ModelAndView();
		view.setViewName("/news/index");
		view.addObject("article", articles);
		
		return view;
		
	}
	
	/**
	 * 插入新闻
	 * @param article
	 * @return
	 */
	@PostMapping
	public ModelAndView editArticle(Article article,@RequestParam(value = "file" ,required=false) MultipartFile file,HttpServletRequest req ) {
		Message ms= this.articleservice.saveArticle(article,file,req);
		ModelAndView view = new ModelAndView();
		if (ms.getState() == false) {
			view.addObject("message", ms.getMessage());
			return view;
		}
		view.setViewName("redirect:/identity/news/edit");
		
		return view;
		
	}
	/**
	 * 根据id 删除文章
	 * @param id
	 * @return
	 */
	
	@RequestMapping("/delete/{id}")
	public ModelAndView deleteArticle(@PathVariable ("id")String id) {
		
		this.articleservice.deleteByArticle(id);
		return new ModelAndView("redirect:/identity/news/edit");
		
	}
	
	/**
	 * 跳转新闻添加页面
	 */
	@RequestMapping(value  = "/edit" , method = RequestMethod.GET)
	public ModelAndView getEdit(@RequestParam(value = "pageNumber" ,required = false)Integer pageNumber){
		if (pageNumber==null) {
			pageNumber = 1;
		}
		ModelAndView view = new ModelAndView();
		
		Page<Article> article = this.articleservice.findQueryArticle(pageNumber);
		view.addObject("article", article);
		view.setViewName("/news/operation");
		
		return view;
	}
	
	
	
}
