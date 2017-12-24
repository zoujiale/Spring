package zou.identity.service;


import java.util.List;
import java.util.Set;

import zou.identity.domain.Role;
import zou.identity.domain.User;

public interface UserService {
	User findUser(String usn , String pwd) ;

    void UpdateLoginTime(User user);
    
    List<Role> findRoleByUser();

}
