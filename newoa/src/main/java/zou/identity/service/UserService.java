package zou.identity.service;

import zou.identity.domain.User;

public interface UserService {
   User findUser(String usn , String pwd) ;

    void UpdateLoginTime(User user);
}
