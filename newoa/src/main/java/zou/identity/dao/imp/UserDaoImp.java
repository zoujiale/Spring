package zou.identity.dao.imp;

import org.springframework.stereotype.Repository;

import zou.common.dao.imp.CommonDaoimpl;
import zou.identity.dao.UserDao;
import zou.identity.domain.User;

@Repository
public class UserDaoImp extends CommonDaoimpl<User> implements UserDao {

}
