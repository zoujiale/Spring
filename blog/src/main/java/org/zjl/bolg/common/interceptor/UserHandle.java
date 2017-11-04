package org.zjl.bolg.common.interceptor;

import org.zjl.bolg.identity.domain.User;

/**   
* @Title: UserHandle.java 
* @Package org.zjl.bolg.common.interceptor 
* @Description: 线程隔离
* @author 704572528@qq.com 
* @date 2017年11月4日 上午10:18:24 
* @version V1.0   
*/
public class UserHandle {

	private final static ThreadLocal<User> THREAD_USER = new ThreadLocal<>();
	
	/**
	 * 从线程里设置User
	 * @param user
	 */
	
	public static void setThreadUser(User user)
	{
			THREAD_USER.set(user);
	}
	
	
	/**
	 * 从线程拿到User对象
	 * @return
	 */
	
	public static User getLoginThreadUser() {
		
		return THREAD_USER.get();
		
	}
	
	/**
	 * 当请求结束，就清空线程
	 */
	
	public static void removeThreadUser() {
		THREAD_USER.remove();
	}
	
}
