package zou.common.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import zou.common.shiro.TokenManager;
import zou.identity.domain.User;
import zou.identity.service.UserService;

public class MyRealm extends AuthorizingRealm {

	@Autowired
	private UserService userservice;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 登录验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authtoken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authtoken;
		String password = new String (token.getPassword());
		System.out.println(password);

		User user = userservice.findUser(token.getUsername(), password);
		if (user == null) {
			throw new AuthenticationException("登录或密码错误");
		} else if (user.getEnable() == false) {
			throw new DisabledAccountException("你的用户已被禁用");
		} else {
			TokenManager.addAttribute("USER_SESSION", user);
		}

		return new SimpleAuthenticationInfo(user, user.getPassword(), getName());

	}

	public static String md5Pwd(String password, String salt) {
		// TODO Auto-generated method stub
		String md5Pwd = new Md5Hash(password, salt).toHex();
		return md5Pwd;
	}

}
