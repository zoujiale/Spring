package zou.common.dao.imp;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateTemplate;

import zou.common.dao.CommonDao;


/**
 * @ClassName: CommonDaoImpl 
 * @Description: 通用Dao层
 * @author zou
 * @date 2017年10月14日 上午11:10:02 
 */
public abstract class CommonDaoimpl<T> extends HibernateTemplate implements CommonDao<T> {

	
	/**
	 * 
	 * 获取泛型
	 * @return
	 */
	
	public Class<T> getentityClass(){
		Class<?> cla = this.getClass();
		
		ParameterizedType type = (ParameterizedType)cla.getGenericSuperclass();
		
		Type[] types = type.getActualTypeArguments();
		
		@SuppressWarnings("unchecked")
		Class<T> result = (Class<T>) types[0];
		return result;	
	}
	
	
	/**
	 * 获得使用了<code>@Id</code>注解的属性的名称
	 * 
	 * @return
	 */
	protected String getIdPropertyName() {
		Class<?> cla = this.getentityClass();
		// System.out.println("实体类型：" + cla);
		String name = getIdPropertyName(cla);
		return name;
	}

	private String getIdPropertyName(Class<?> cla) {
		// 获得里面使用了 @Id 注解的成员变量，返回变量的名称

		// 获得类里面定义的全部成员变量，包括私有的。但是并不会获得父类定义的成员变量。
		Field[] fields = cla.getDeclaredFields();
		for (Field field : fields) {

			// 获取Id注解，如果成员变量没有使用Id注解，返回null
			Id id = field.getAnnotation(Id.class);
			if (id != null) {
				// 不为null，表示这个成员变量就是主键
				return field.getName();
			}
		}

		// 如果上面没有找到包含id的字段，则查找父类包含id的字段
		Class<?> parent = cla.getSuperclass();
		if (parent == Object.class) {
			// 如果已经是Object类了，则不递归获取
			return null;
		} else {
			// 不是Object则一直向上递归获取，直到有Id注解的成员变量则返回
			return getIdPropertyName(parent);
		}
	}
	
	
	/**
	 * 获取条件查询器
	 */
	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		Session session = super.getSessionFactory().getCurrentSession();
		return session.getCriteriaBuilder();
	}
	
	/**
	 * 拿到Id注解的总页数
	 */
	
	@Override
	public Long CountNumber() {
		
		CriteriaBuilder builder = this.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<T> root = query.from(this.getentityClass());
		query.select(builder.count(root.get(getIdPropertyName())));
		
		Session session = this.getSessionFactory().getCurrentSession();
		
		Long count = session.createQuery(query).getResultList().get(0);
		
		return count;
	}
	
	
}
