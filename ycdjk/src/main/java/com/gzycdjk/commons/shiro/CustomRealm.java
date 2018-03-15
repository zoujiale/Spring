package com.gzycdjk.commons.shiro;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.gzycdjk.identity.domain.Permission;
import com.gzycdjk.identity.domain.Role;
import com.gzycdjk.identity.domain.User;
import com.gzycdjk.identity.service.RoleService;
import com.gzycdjk.identity.service.UserService;

/**
 * 
 * 自定义shiro权限认证
 * @author YCJKmr.zo
 *
 */

public class CustomRealm extends AuthorizingRealm {
	
	private static Logger LG = LogManager.getLogger(CustomRealm.class);
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private RoleService roleservice;
	/**
	 * 权限赋权
	 */
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		if (!ShiroManager.isAuthenticated()) {
			doClearCache(principals);
			ShiroManager.SessionLoginOut();
			return null;
		}
		User user = ShiroManager.getSessionUser();
		Set<Role>rl = this.roleservice.findRoleByUser(user);
		Set<String> roleName = new LinkedHashSet<String>();
		Set<String> rolePermission = new LinkedHashSet<String>();
		for (Role role : rl) {
			roleName.add(role.getName());
			
			Set<Permission> permissions = role.getPermissions();
			
			for (Permission permission : permissions) {
				rolePermission.add(permission.getPercode());
			}
		}
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(roleName);
		info.setStringPermissions(rolePermission);
		return info;
	}

	/**
	 * 登录认证
	 */
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		UsernamePasswordToken tpt = (UsernamePasswordToken) token;
		
		String pwd = CustomRealm.md5Pwd(new String (tpt.getPassword()), tpt.getUsername());
		
		LG.info("通过MD5加密" + pwd);
		
		User us = this.userservice.findByUserName(tpt.getUsername(),pwd);
		
		
		if (us ==null) {
			
			throw new AuthenticationException("用户名或者密码不正确");
		}else if (us.getEnable()== Boolean.FALSE) {
				throw new AuthenticationException("你的用户未激活,请联系管理员");
		}
			
		LG.info("登录成功" + us.getUserName());
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(us,us.getPassword(),ByteSource.Util.bytes(us.getLoginName()),this.getName());
		
		return simpleAuthenticationInfo;
	}
	
	
	/**
	 * Md5加密
	 * @param password
	 * @param salt
	 * @return
	 */
	public static String md5Pwd(String password, String salt) {  
	    String md5Pwd = new Md5Hash(password, salt).toHex();  
	    return md5Pwd;  
	}  

}
