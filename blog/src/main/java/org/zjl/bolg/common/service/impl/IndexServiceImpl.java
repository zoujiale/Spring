package org.zjl.bolg.common.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.zjl.bolg.common.service.IndexService;
import org.zjl.bolg.identity.dao.UserDao;
import org.zjl.bolg.identity.domain.User;

/**   
* @Title: IndexServiceImpl.java 
* @Package org.zjl.bolg.common.service.impl 
* @Description:  查询用户
* @author 704572528@qq.com 
* @date 2017年10月17日 下午5:03:08 
* @version V1.0   
*/
@Service
@Transactional
public class IndexServiceImpl implements IndexService {

	@Resource
	private UserDao userdao;
	/**
	 * 判定是否存在这个用户名的人
	 */
	@Override
	public Boolean getuserName(String username) {
		User user = new User();
		user.setLoginName(username);
		user.setEnable(true);
	    List<User> name = userdao.findByExample(user);
	    if (name.size()<0) {
			return false;
		}
	    return true;
	    
	}
	
	/**
	 * 验证密码，验证LoginName 是否存在
	 */
	@Override
	public User getuserAll(String password, Boolean excuteLoginName) {
		if (excuteLoginName = true) {
			User us  = new User();
			
			us.setPassword(password);
			System.out.println(password  + "加密后的数据");
			List<User> example = userdao.findByExample(us);
			if (example.size()>0) {
				return example.get(0);
			}
		}
		
		return null;
	}

	

}
