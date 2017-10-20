package org.zjl.bolg.common.service;

import org.springframework.data.domain.Page;
import org.zjl.bolg.identity.domain.Article;

/**   
* @Title: IndexService.java 
* @Package org.zjl.bolg.common.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author 704572528@qq.com 
* @date 2017年10月20日 下午2:09:45 
* @version V1.0   
*/
public interface IndexService {
	/**
	 * 博文分页
	 * @param pageNumber 传过来的页码
	 * 
	 * @return
	 */
	
	
	Page<Article> findArticle(int pageNumber);

}
