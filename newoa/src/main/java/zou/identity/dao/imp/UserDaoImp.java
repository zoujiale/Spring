package zou.identity.dao.imp;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import zou.common.dao.imp.CommonDaoimpl;
import zou.common.vo.Page;
import zou.identity.dao.UserDao;
import zou.identity.domain.User;

@Repository
public class UserDaoImp extends CommonDaoimpl<User> implements UserDao {

	@Override
	public Page<User> QueryFind(String search, int pageNo) {
		/*if (search.equals("无效")) {
			search = "false";
		} else if (search.equals("有效")) {
			search = "true";
		}*/
		
		// 默认 一次显示5条数据 
		int pageContorl = 5;
		String search2 = "%" + search + "%";

		CriteriaBuilder builder = this.getCriteriaBuilder();
		// 返回的类型
		CriteriaQuery<User> query = builder.createQuery(getentityClass());
		Root<User> root = query.from(getentityClass());
		
		if (search == null) {
			query.select(root);
		}
		else {
		  query.select(root);
		query.where(
				builder.or(builder.like(root.get("phoneNumber"), search2),builder.like(root.get("loginName"), search2),builder.like(root.get("username"),search2))
				);
		}
		Long countNumber = this.CountNumber();
		
		Session session = this.getSessionFactory().getCurrentSession();
		List<User> list = session.createQuery(query).setFirstResult((pageNo-1)*pageContorl).setMaxResults(pageContorl).getResultList();
		Page<User> pg=  new Page<>(pageNo, pageContorl, countNumber, list);
		return pg;
	}

}
