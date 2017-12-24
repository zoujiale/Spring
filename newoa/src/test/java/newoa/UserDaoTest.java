package newoa;

import static org.junit.Assert.*;

import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import zou.identity.dao.RoleDao;
import zou.identity.dao.UserDao;
import zou.identity.domain.Permissions;
import zou.identity.domain.Role;
import zou.identity.domain.User;
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath*:spring/applicationContext*.xml")
public class UserDaoTest  extends AbstractTransactionalJUnit4SpringContextTests{
	@Resource
	UserDao userdao;
	@Resource
	RoleDao roledao;
	
	@Test
	public void testName() throws Exception {
			Role role = userdao.get(User.class, "12").getRole().get(0);
			System.out.println(role.getName());
	}
	
	
	@Test
	public void role() throws Exception {
		User user = userdao.get(User.class, "12");
		Set<Permissions> set = user.getRole().get(0).getPermissions();
		for (Permissions permissions : set) {
			System.out.println(permissions.getUrl());
		}
	}
}
