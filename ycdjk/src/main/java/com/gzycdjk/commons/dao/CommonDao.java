package com.gzycdjk.commons.dao;


import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateOperations;

import com.gzycdjk.commons.vo.Page;
import com.gzycdjk.commons.vo.Pageable;

/**
 * @ClassName: CommonDao 
 * @Description: 这是通用Dao层
 * @author zou
 * @date 2017年10月14日 上午10:49:29 
 */
public interface CommonDao<T> extends HibernateOperations {
	/**
	 * 获取条件查询器
	 * @return
	 */
	CriteriaBuilder getCriteriaBuilder();
	
	/**
	 * 拿到总数据
	 * @return
	 */
	Long CountNumber();
	
	
	/**
	 * 通用查询分页
	 * @param pageNumber
	 * @return
	 */
	
	Page<T> PaGin(Pageable pageable);
	/**
	 * 通用查询Hql
	 */
	Page<T> SqlGin(Pageable pageable,String Hql,String count);
	/**
	 * 用于执行sql语句
	 * @return
	 */
	Session getSession();

	
}
