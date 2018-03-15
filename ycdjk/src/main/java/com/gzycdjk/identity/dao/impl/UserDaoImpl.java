package com.gzycdjk.identity.dao.impl;

import java.util.HashSet;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.tomcat.jni.Buffer;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzycdjk.commons.dao.impl.CommonDaoimpl;
import com.gzycdjk.commons.vo.Page;
import com.gzycdjk.commons.vo.Pageable;
import com.gzycdjk.identity.dao.RoleDao;
import com.gzycdjk.identity.dao.UserDao;
import com.gzycdjk.identity.domain.Role;
import com.gzycdjk.identity.domain.User;

@Repository
public class UserDaoImpl extends CommonDaoimpl<User> implements UserDao {
	@Autowired
	RoleDao roledao;

	@Override
	public Page<User> findUserByConditions(Pageable page, String name, String loginName) {

		// 当前页
		Integer num = page.getPageNum();
		// 数据
		Integer pageSize = page.getPageSize();

		CriteriaBuilder builder = this.getCriteriaBuilder();

		CriteriaQuery<Long> query2 = builder.createQuery(Long.class);
		Root<User> root2 = query2.from(this.getentityClass());

		CriteriaQuery<User> query = builder.createQuery(this.getentityClass());
		Root<User> root = query.from(this.getentityClass());
		if ("".equals(name) && "".equals(loginName)) {
			query.select(root);
			query2.select(builder.count(root2.get(this.getIdPropertyName())));
		} else if (!"".equals(name)) {
			query.where(builder.like(root.get("userName"), name));
			query2.select(builder.count(root2.get(getIdPropertyName())))
					.where(builder.like(root2.get("userName"), name));

		} else if (!"".equals(loginName)) {
			query.where(builder.like(root.get("loginName"), loginName));
			query2.select(builder.count(root2.get(this.getIdPropertyName())))
					.where(builder.like(root2.get("loginName"), loginName));
		} else {
			query.where(builder.or(builder.like(root.get("loginName"), loginName),
					builder.or(builder.like(root.get("userName"), name))));
			query2.select(builder.count(root2.get(this.getIdPropertyName())))
					.where(builder.or(builder.like(root2.get("loginName"), loginName),
							builder.or(builder.like(root2.get("userName"), name))));
		}
		Session session = this.getSessionFactory().getCurrentSession();
		Long countNumber = session.createQuery(query2).getResultList().get(0);

		List<User> resultList = session.createQuery(query).setFirstResult((num - 1) * pageSize).setMaxResults(pageSize)
				.getResultList();
		Page<User> page2 = new Page<>(num, pageSize, countNumber, resultList);
		return page2;
	}

	@Override
	public Page<User> findUserQuery(Pageable page, String id, String userName) {

		Integer pageNum = page.getPageNum();
		Integer pageSize = page.getPageSize();
		
		StringBuffer buffer = new StringBuffer();
		StringBuffer buffer2 = new StringBuffer();
		String hql,hq2;
		if (!"".equals(userName)) {
			buffer.append(" select us from Role rl left join rl.users us  inner join User ur on us.id = ur.id "
					+ " where rl.id = '")
					.append(id + "'").append(" and us.userName like '" + userName +"'");
			hql = buffer.toString();
			hq2 = buffer2.append("select count(us) from Role rl left join rl.users us  inner join User ur on us.id = ur.id where rl.id = '")
					.append(id + "'").append(" and us.userName like '" + userName +"'").toString();
		} else {
			buffer.append(" select us from Role rl left join rl.users us  inner join User ur on us.id = ur.id where rl.id = '")
					.append(id + "' ");
			hql = buffer.toString();
			hq2 = buffer2.append(" select count(us) from Role rl left join rl.users us  inner join User ur on us.id = ur.id where rl.id = '")
					.append(id + "'").toString();
		}
		Session session = this.getSessionFactory().getCurrentSession();
		Long countnumbern = (Long) session.createQuery(hq2).getResultList().get(0);
		List<User> resultList = session.createQuery(hql).setFirstResult((pageNum-1)*pageSize).setMaxResults(pageSize).getResultList();
		Page<User> page2 = new Page<>(pageNum, pageSize, countnumbern, resultList);
		return page2;
	}

	@Override
	public Page<User> findUserByRoleId(Pageable pga, String id, String userName) {
		
		Integer num = pga.getPageNum();
		Integer pageSize = pga.getPageSize();
		String sql = null,sql2 = null;
		if ("".equals(userName)) {
			sql = "from User us where us.id not in (select us.id from  Role rl  inner join rl.users us inner join us.roles url where url.id='"
					+id+ "')" 
					;
			sql2 = "select count(us) from User us where us.id not in (select us.id from  Role rl  inner join rl.users us inner join us.roles url where url.id='"
					+id+ "')" 
					;
		}else {
			sql = "from User us where us.id not in (select us.id from  Role rl  inner join rl.users us inner join us.roles url where url.id='"
					+id+ "')" + "and us.userName like '" + userName +"'" 
					;
			sql2 = "select count(us) from User us where us.id not in (select us.id from  Role rl  inner join rl.users us inner join us.roles url where url.id='"
					+id+ "')" + "and us.userName like '" + userName +"'" 
					;
		}
		Session session = this.getSessionFactory().getCurrentSession();
		Long countNumber =(Long) session.createQuery(sql2).getResultList().get(0);
		
		
		@SuppressWarnings("unchecked")
		List<User> resultList = session.createQuery(sql).setFirstResult((num-1)*pageSize).setMaxResults(pageSize).getResultList();
		Page<User> page = new Page<>(num, pageSize, countNumber, resultList);
		return page;
	}
}
