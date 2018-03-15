package com.gzycdjk.identity.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.gzycdjk.commons.dao.impl.CommonDaoimpl;
import com.gzycdjk.commons.vo.Page;
import com.gzycdjk.identity.dao.ArticleDao;
import com.gzycdjk.identity.domain.Article;

@Repository
public class ArticleDaoImpl extends CommonDaoimpl<Article> implements ArticleDao{

	@Override
	public Page<Article> findByArticle(Integer pageNumber) {
		// 定义1页显示5条数据
		int pageSize = 6;
		
		Long countNumber = this.CountNumber();
		
		String hql = "from Article article order by  article.createDate desc";
		
		Session session = this.getSessionFactory().getCurrentSession();
		
	    List<Article> list = session.createQuery(hql).setFirstResult((1-pageNumber)*pageSize).setMaxResults(pageSize).getResultList();
		
		
		Page<Article> page = new Page<>(pageNumber, pageSize, countNumber, list);
		
		return page;
	}
}
