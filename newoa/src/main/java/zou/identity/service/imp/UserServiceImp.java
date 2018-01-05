package zou.identity.service.imp;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zou.common.vo.Message;
import zou.common.vo.Page;
import zou.identity.dao.UserDao;
import zou.identity.domain.Role;
import zou.identity.domain.User;
import zou.identity.service.UserService;

@Service
@org.springframework.transaction.annotation.Transactional
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

	@Override
	public List<Role> findRoleByUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message UserSave(User user) {
		// 查询是否重复 true 可修改
		Boolean boolean1 = CheckInUserName(user.getUsername(),user.getId());
		
		Message message = new Message();
		User a = userdao.get(User.class, user.getId());
	
		
		if (boolean1 == false) {
			message.setMessage("用户名的重复请修改");
			message.setState(false);
			return message;
		}
		a.setPassword(user.getPassword());
		a.setEmail(user.getEmail());
		a.setPhoneNumber(user.getPhoneNumber());
		a.setUsername(user.getUsername());
		
		message.setMessage("修改成功");
		message.setState(true);
		return message;
	}

	@Override
	public Boolean CheckInUserName(String name,String id) {
		User user = new User();
		user.setUsername(name);
		user.setEnable(true);
		// 查询重复username
		List<User> list = userdao.findByExample(user);
		
		User user2 = this.findUserByid(id);
		
		if (list.isEmpty()) {
			return true;
		}
		// 会不会跟当前user用户名一致
		for (User users : list) {
			if (users.getId().equals(user2.getId())) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public User findUserByid(String id) {
		return userdao.get(User.class, id);
	}



	@Override
	public void editState(String id) {
		User user = this.findUserByid(id);
		user.setEnable(true);
	}

	@Override
	public Page<User> findAllUser(String search, Integer pageNo) {
		Page<User> pg= userdao.QueryFind(search,pageNo);
		return pg;
	}

	@Override
	public void editStateFalse(String id) {
		User user = this.findUserByid(id);
		user.setEnable(false);
	}

	
}
