package zou.identity.dao;

import java.util.List;

import zou.common.dao.CommonDao;
import zou.identity.domain.Role;
public interface RoleDao extends CommonDao<Role> {

	List<Role> findbyContent(String context12);

}
