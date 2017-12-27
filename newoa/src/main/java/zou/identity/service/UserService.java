package zou.identity.service;


import java.util.List;
import java.util.Set;

import zou.common.vo.Message;
import zou.identity.domain.Role;
import zou.identity.domain.User;

public interface UserService {
	User findUser(String usn , String pwd) ;

    void UpdateLoginTime(User user);
    
    User findUserByid(String id);
    
    List<Role> findRoleByUser();

	Message UserSave(User user);
	/**
	 * 看数据库的Username是否重用
	 * @param name
	 * @return
	 */
	Boolean CheckInUserName(String name,String id);
}
