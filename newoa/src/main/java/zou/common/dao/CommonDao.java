package zou.common.dao;

import javax.persistence.criteria.CriteriaBuilder;

import org.springframework.orm.hibernate5.HibernateOperations;

/**
 * @ClassName: CommonDao 
 * @Description: 这是通用Dao层
 * @author zou
 * @date 2017年10月14日 上午10:49:29 
 */
public interface CommonDao<T> extends HibernateOperations {
	/**
	 * 获取条件查询器
	 * @return
	 */
	CriteriaBuilder getCriteriaBuilder();
	
	/**
	 * 拿到总数据
	 * @return
	 */
	Long CountNumber();
	

	
}
