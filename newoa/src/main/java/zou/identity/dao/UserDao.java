package zou.identity.dao;

import zou.common.dao.CommonDao;
import zou.common.vo.Page;
import zou.identity.domain.User;
public interface UserDao extends CommonDao<User> {

	Page<User> QueryFind(String search, int pageNo);

}
