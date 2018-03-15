package com.gzycdjk.identity.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.gzycdjk.commons.vo.Message;
import com.gzycdjk.commons.vo.Page;
import com.gzycdjk.identity.domain.Article;

public interface ArticleService {
	/**
	 * 根据页码调整新闻
	 * @param pageNumber
	 * @return
	 */
	Page<Article> findQueryArticle(Integer pageNumber);
	
	
	/**
	 * 保存新闻,新闻图片
	 * @param article
	 * @param file 
	 * @param req 
	 * @return
	 */
	
	Message saveArticle(Article article, MultipartFile file, HttpServletRequest req);
	
	/**
	 * 根据Id删除新闻文章
	 * @param id
	 */
	void deleteByArticle(String id);



}
