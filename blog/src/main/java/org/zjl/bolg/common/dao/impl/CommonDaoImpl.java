package org.zjl.bolg.common.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.zjl.bolg.common.dao.CommonDao;


/**
 * @ClassName: CommonDaoImpl 
 * @Description: 通用Dao层
 * @author zou
 * @date 2017年10月14日 上午11:10:02 
 */
public abstract class CommonDaoImpl<T> extends HibernateTemplate implements CommonDao<T> {

	/**
	 * 获取条件查询器
	 */
	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		Session session = super.getSessionFactory().getCurrentSession();
		return session.getCriteriaBuilder();
	}

}
