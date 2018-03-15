package com.gzycdjk.identity.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.gzycdjk.commons.dao.impl.CommonDaoimpl;
import com.gzycdjk.identity.dao.PermissionDao;
import com.gzycdjk.identity.domain.Permission;

@Repository
public class PermissionDaoImpl extends CommonDaoimpl<Permission> implements PermissionDao {

	@Override
	public List<Permission> findParentsId() {
		Session session = this.getSession();
		
		return session.createQuery("from Permission ps where ps.parent is null").getResultList();
	}

	
	
}
