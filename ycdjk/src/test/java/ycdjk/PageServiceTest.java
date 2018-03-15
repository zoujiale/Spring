package ycdjk;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gzycdjk.commons.pojo.UserRole;
import com.gzycdjk.commons.vo.Message;
import com.gzycdjk.commons.vo.Page;
import com.gzycdjk.commons.vo.Pageable;
import com.gzycdjk.identity.dao.ArticleDao;
import com.gzycdjk.identity.dao.PermissionDao;
import com.gzycdjk.identity.dao.RoleDao;
import com.gzycdjk.identity.dao.UserDao;
import com.gzycdjk.identity.domain.Article;
import com.gzycdjk.identity.domain.Permission;
import com.gzycdjk.identity.domain.User;
import com.gzycdjk.identity.service.RoleService;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath*:spring/applicationContext*.xml")
public class PageServiceTest extends AbstractTransactionalJUnit4SpringContextTests{
	@Resource 
	ArticleDao articledao;
	
	@Resource
	UserDao userdao;
	@Resource
	RoleDao roledao;
	
	@Resource
	RoleService roleservice;
	
	@Resource
	PermissionDao permissiondao;
	
	
	@Test
	public void f() throws Exception {
		List<UserRole> parseArray = new ArrayList<>();
		UserRole userRole = new UserRole();
		User a  = new User();
		a.setId("4028802461ff93970161ffab3c2d0000");
		userRole.setUser(a);
		userRole.setRoleId("4028802461ff65e80161ff764cb30000");
		
		
		UserRole userRole1 = new UserRole();
		User a1  = new User();
		a.setId("1");
		userRole.setUser(a1);
		userRole.setRoleId("4028802461ff65e80161ff764cb30000");
		
		
		
		parseArray.add(userRole);
		parseArray.add(userRole1);
		Message ms = roleservice.UserBoundRole(parseArray);
		System.out.println(ms);
	}
	
	
	@Test
	public void testName() throws Exception {
		userdao.findUserQuery(new Pageable(5, 1),"4028802461ff65e80161ff764cb30000","咸鱼");
	}
	
	@Test
	public void sf() throws Exception {
		Permission permission = permissiondao.get(Permission.class, "2");
		Permission parent = permission.getParent();
		System.out.println(parent.getText());
	}
	
	
	
}
