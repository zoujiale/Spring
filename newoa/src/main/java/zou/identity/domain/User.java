package zou.identity.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;



/**
 * @ClassName: User 
 * @Description: 存储博客用户信息
 * @author zou
 * @date 2017年10月13日 下午2:27:50 
 */

@Entity	
@Table(name = "blog_user")
@Getter@Setter
public class User {
	@Id
	@GenericGenerator(name = "uuid" ,strategy = "uuid")
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "uuid")
	private String id;
	
	private String email;
	
	@Column(length = 50)
	private String password;
	
	@Column(name = "create_date")
	private Date createDate;
	
	/**
	 * 激活状态
	 */
	@Column(length = 10)
	private Boolean enable;	
	
	/**
	 * 以枚举的内容存储，不以索引
	 */
	@Enumerated(EnumType.STRING)
	private Sex sex;
	
	
	
	@Column(length = 10 )
	private String username;
	
	@Column(name = "phone_number" , length = 13)
	private Integer phoneNumber;
	

	@Column(name = "Login_NAME")
	private String loginName;
	
	/**
	 * 登入时间
	 */
	@Column(name = "login_in")
	@DateTimeFormat(style = "yyyy-MM-dd HH:mm:ss")
	private  Date loginIn;
	
	/**
	 * 退出登录
	 */
	@Column(name = "login_out")
	private  Date loginOut;
	
	/**
	 * 根据用户查询用户所拥有的角色
	 */
	@ManyToMany(mappedBy = "users")
	private List<Role> role;



	
}
