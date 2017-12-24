package zou.common.shiro.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import zou.common.shiro.TokenManager;
import zou.identity.domain.Permissions;
import zou.identity.domain.Role;
import zou.identity.domain.User;
import zou.identity.service.RoleService;
import zou.identity.service.UserService;

public class MyRealm extends AuthorizingRealm {

	@Autowired
	private UserService userservice;
	@Autowired
	private RoleService roleservice;
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 拿到登录的User
		User user = TokenManager.getSessionUser();
		Set<String> role= roleservice.findRoleByUser(user);
		// 查询用户所拥有的角色
		info.setRoles(role);
		List<Role> list = roleservice.findRoleByUser(user.getId());
		Set<String> Permissionslist = new HashSet<>();
		for (Role roles : list) {
			Set<Permissions> dd = roles.getPermissions();
			for (Permissions permissions : dd) {
				Permissionslist.add(permissions.getUrl());
			}
		}
		info.setStringPermissions(Permissionslist);
		return info;
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

	

}
