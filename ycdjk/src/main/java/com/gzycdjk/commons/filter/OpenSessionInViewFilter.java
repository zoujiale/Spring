package com.gzycdjk.commons.filter;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessResourceFailureException;

public class OpenSessionInViewFilter extends org.springframework.orm.hibernate5.support.OpenSessionInViewFilter{
	@SuppressWarnings("deprecation")
	protected Session openSession(SessionFactory sessionFactory) throws DataAccessResourceFailureException {
		try {
			Session session = sessionFactory.openSession();
			session.setFlushMode(FlushMode.COMMIT);
			return session;
		}
		catch (HibernateException ex) {
			throw new DataAccessResourceFailureException("Could not open Hibernate Session", ex);
		}
	}
}
