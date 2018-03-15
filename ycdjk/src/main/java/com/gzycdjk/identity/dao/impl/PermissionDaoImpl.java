package com.gzycdjk.identity.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder;
import com.gzycdjk.commons.dao.impl.CommonDaoimpl;
import com.gzycdjk.identity.dao.PermissionDao;
import com.gzycdjk.identity.domain.Permission;

@Repository
public class PermissionDaoImpl extends CommonDaoimpl<Permission> implements PermissionDao {

	

	@Override
	public List<Permission> findTopPermission() {
		CriteriaBuilder criteriaBuilder = this.getCriteriaBuilder();
		CriteriaQuery<Permission> query = criteriaBuilder.createQuery(this.getentityClass());
		Root<Permission> root = query.from(this.getentityClass());
		query.where(criteriaBuilder.isNull(root.get("parent"))).orderBy(criteriaBuilder.asc(root.get("orderNumber")));
		
		
		Session session = this.getSession();
		
		return session.createQuery(query).getResultList();
	}

	
	
}
