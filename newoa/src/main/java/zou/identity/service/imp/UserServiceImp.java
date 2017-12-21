package zou.identity.service.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zou.identity.dao.UserDao;
import zou.identity.domain.User;
import zou.identity.service.UserService;

@Service
@Transactional
public class UserServiceImp implements UserService {

	private static Logger LOG = LogManager.getLogger(UserServiceImp.class);
	@Autowired
	UserDao userdao;

	@Override
	public User findUser(String usn, String pwd)   {
		User user = new User();
		user.setLoginName(usn);

		List<User> list = userdao.findByExample(user);

		if (!list.isEmpty()) { // 找到对应账户名
			
			LOG.info("找到User");
			// 加密
				User us = list.get(0);
				LOG.info("拿到User");
				String password = us.getPassword();
				if (password.equals(pwd)) {
					return us;
				}
				LOG.info("Md5转换失败");
			}
			
		return null;
	}

	@Override
	public void UpdateLoginTime(User user) {
		userdao.save(user);

	}
}
