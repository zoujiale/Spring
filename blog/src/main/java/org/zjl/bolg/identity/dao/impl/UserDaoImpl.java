package org.zjl.bolg.identity.dao.impl;

import org.springframework.stereotype.Repository;
import org.zjl.bolg.common.dao.impl.CommonDaoImpl;
import org.zjl.bolg.identity.dao.UserDao;
import org.zjl.bolg.identity.domain.User;


/**
 * @ClassName: UserDaoImpl 
 * @Description: 查询User的数据
 * @author zou
 * @date 2017年10月14日 上午11:20:39 
 */
@Repository
public class UserDaoImpl extends CommonDaoImpl<User> implements UserDao {

}
