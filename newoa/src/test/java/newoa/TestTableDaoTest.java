package newoa;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import zou.identity.dao.UserDao;
import zou.identity.domain.User;

// 指定JUnit的运行器为Spring的扩展
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath*:spring/applicationContext*.xml")
public class TestTableDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	private UserDao dao;

	@Test
	public void c() {
		User tt = new User();
		tt.setId("xxxxx");

		dao.save(tt);
	}

	
}
