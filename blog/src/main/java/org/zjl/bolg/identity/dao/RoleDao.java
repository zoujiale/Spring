package org.zjl.bolg.identity.dao;

import java.util.List;

import org.zjl.bolg.common.dao.CommonDao;
import org.zjl.bolg.identity.domain.Role;
import org.zjl.bolg.identity.domain.User;

/**   
* @Title: ArticleDao.java 
* @Package org.zjl.bolg.identity.dao 
* @Description: TODO(用一句话描述该文件做什么) 
* @author 704572528@qq.com 
* @date 2017年10月20日 下午2:20:23 
* @version V1.0   
*/
public interface  RoleDao extends CommonDao<Role> {

	/**
	 * 就找博主
	 */
	  List<Role>getBlogger();

}
