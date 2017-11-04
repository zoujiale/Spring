package org.zjl.bolg.identity.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.zjl.bolg.common.dao.impl.CommonDaoimpl;
import org.zjl.bolg.identity.dao.RoleDao;
import org.zjl.bolg.identity.domain.Role;

/**   
* @Title: ArticleDaoImpl.java 
* @Package org.zjl.bolg.identity.dao.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author 704572528@qq.com 
* @date 2017年10月20日 下午2:22:25 
* @version V1.0   
*/
@Repository
public class RoleDaoimpl extends CommonDaoimpl<Role> implements RoleDao{

	
	/**
	 * 只要博主身份
	 */
	
	@Override
	public List<Role> getBlogger() {
		CriteriaBuilder builder = this.getCriteriaBuilder();
		CriteriaQuery<Role> query = builder.createQuery(Role.class);
		Root<Role> root = query.from(Role.class);
		query.select(root).where(
				builder.and(
						builder.equal(root.get("name"), "博主"))
				);
		
		List<Role> list = this.getSessionFactory().getCurrentSession().createQuery(query).getResultList();
		
		return list;
	}

	
}
