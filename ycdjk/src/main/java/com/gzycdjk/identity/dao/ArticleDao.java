package com.gzycdjk.identity.dao;

import com.gzycdjk.commons.dao.CommonDao;
import com.gzycdjk.commons.vo.Page;
import com.gzycdjk.identity.domain.Article;

public interface ArticleDao extends CommonDao<Article>{
	/**
	 * 分页
	 * @param pageNumber
	 * @return
	 */
	Page<Article> findByArticle(Integer pageNumber);

}
