package com.gzycdjk.identity.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.gzycdjk.commons.dao.impl.CommonDaoimpl;
import com.gzycdjk.commons.vo.Page;
import com.gzycdjk.commons.vo.Pageable;
import com.gzycdjk.identity.dao.RoleDao;
import com.gzycdjk.identity.domain.Role;
import com.gzycdjk.identity.domain.User;

@Repository
public class RoleDaoImpl extends CommonDaoimpl<Role> implements RoleDao {

	@Override
	public Page<Role> findRoleByQuery(String roleName) {
		
		CriteriaBuilder builder = this.getCriteriaBuilder();
		CriteriaQuery<Role> query = builder.createQuery(this.getentityClass());
		Root<Role> root = query.from(this.getentityClass());
		
		
		Session session = this.getSessionFactory().getCurrentSession();
		Long long1 = this.CountNumber();
		
		
		if (!"".equals(roleName)) {
			System.out.println(roleName);
			query.where(builder.like(root.get("name"), roleName));
		}
		List<Role> list = session.createQuery(query).getResultList();
		
		return new Page<Role>(0, long1.intValue(), long1, list);
	}

	

}
