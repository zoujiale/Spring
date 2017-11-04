package org.zjl.bolg.common.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.zjl.bolg.common.service.IndexService;
import org.zjl.bolg.identity.dao.ArticleDao;
import org.zjl.bolg.identity.dao.RoleDao;
import org.zjl.bolg.identity.domain.Article;
import org.zjl.bolg.identity.domain.Role;
import org.zjl.bolg.identity.domain.User;

/**   
* @Title: IndexServiceimpl.java 
* @Package org.zjl.bolg.common.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author 704572528@qq.com 
* @date 2017年10月20日 下午2:16:40 
* @version V1.0   
*/
@Service
@Transactional
public class IndexServiceimpl implements IndexService {
	
	@Resource
	private ArticleDao articledao;
	@Resource
	private RoleDao roledao;
	/**
	 * 将博文分页
	 */
	@Override
	public Page<Article> findArticle(int pageNumber) {
		//  一页 6 条数据
		Pageable pageable = new PageRequest(pageNumber, 6);
		
		
		Long count = articledao.CountNumber();
		
		// 查询到博主身份
		User user = roledao.getBlogger().get(0).getUsers().get(0);
		
		Page<Article> page = articledao.getPage(pageable, count,user);
		
		return page;
	}
	
}
