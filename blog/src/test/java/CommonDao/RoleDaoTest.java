package CommonDao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zjl.bolg.identity.dao.RoleDao;
import org.zjl.bolg.identity.domain.Role;
import org.zjl.bolg.identity.domain.User;

/**   
* @Title: RoleDaoTest.java 
* @Package CommonDao 
* @Description: TODO(用一句话描述该文件做什么) 
* @author 704572528@qq.com 
* @date 2017年11月4日 上午10:52:27 
* @version V1.0   
*/

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath*:/spring/spring*.xml")
public class RoleDaoTest extends AbstractTransactionalJUnit4SpringContextTests{
		@Resource
		private RoleDao roledao;
	
	
		@Test
		public void testName() throws Exception {
			List<Role> list = roledao.getBlogger();
			Role role = list.get(0);
			System.out.println(role.getUsers().size());
		}
}
