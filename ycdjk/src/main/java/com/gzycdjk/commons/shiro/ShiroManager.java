package com.gzycdjk.commons.shiro;

import org.apache.shiro.SecurityUtils;

import com.gzycdjk.identity.domain.User;

/**
 * Shiro的Session管理
 * @author YCJKmr.zo
 *
 */

public class ShiroManager {
	
	
	public static  User getSessionUser() {
		return (User)SecurityUtils.getSubject().getPrincipal();
	}
	
	public static void SessionLoginOut() {
		SecurityUtils.getSubject().logout();
	}
	
	public static  Boolean isAuthenticated() {
		return SecurityUtils.getSubject().isAuthenticated();
		
	}
}
