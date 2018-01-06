package zou.identity.dao.imp;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import zou.common.dao.imp.CommonDaoimpl;
import zou.identity.dao.RoleDao;
import zou.identity.domain.Role;
@Repository
public class RoleDaoImp extends CommonDaoimpl<Role> implements RoleDao {

	@Override
	public List<Role> findbyContent(String context12) {
		CriteriaBuilder builder = this.getCriteriaBuilder();
		CriteriaQuery<Role> query = builder.createQuery(this.getentityClass());
		
		Root<Role> root = query.from(this.getentityClass());
		
		query.where(builder.or(builder.like(root.get("type"), context12),
					builder.like(root.get("name"), context12)
				));
		Session session = this.getSessionFactory().getCurrentSession();
		List<Role> list = session.createQuery(query).list();
		return list;
	}

	

}
