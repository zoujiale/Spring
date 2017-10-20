package org.zjl.bolg.identity.service;

import org.zjl.bolg.identity.domain.User;

/** 
* @ClassName: LoginService 
* @Description: 页面的Service逻辑层
* @author zou
* @date 2017年10月17日 下午4:20:52 
*  
*/
public interface LoginService {
	/**
	 * 判断是否存在用户名
	 * @param username
	 * @return
	 */
	Boolean getuserName(String username);
	
	
	User getuserAll(String password, Boolean excuteLoginName);
	
}
