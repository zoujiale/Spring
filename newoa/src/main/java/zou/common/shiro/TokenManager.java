package zou.common.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import zou.identity.domain.User;

/**
 * 
 * Shiro 的Session 管理
 * @author zou
 *
 */
public class TokenManager {
	
	
	
	
	/**
	 * 
	 * @return
	 */
	public static User getSessionUser() {
		User principal = (User) SecurityUtils.getSubject().getPrincipal();
		return principal;
	}
	
	/**
	 * 拿到Subject session
	 * @return
	 */
	public static Session getSession() {
		return SecurityUtils.getSubject().getSession(false);
	}
	
	/**
	 * SessionId
	 * @return
	 */
	public static String getSessionId() {
		return (String) getSession().getId();
	}
	
	/**
	 * 获取Session Value 的值
	 * @param key
	 * @return
	 */
	public static Object getattribute(Object key)
	{
		return getSession().getAttribute(key);
	}
	/**
	 * 退出登录
	 */
	public static void SessionOut() {
		SecurityUtils.getSubject().logout();
	}
	
	/**
	 * 把值放入登录用户里
	 * @param key
	 * @param value
	 */
	public static void addAttribute(Object key ,Object value)
	{
		getSession().setAttribute(key, value);
	}
}
