package zou.identity.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

/**   
* @Title: Role.java 
* @Package org.zjl.bolg.identity.domain 
* @Description: 在博客上的角色
* @author 704572528@qq.com 
* @date 2017年10月20日 下午1:09:36 
* @version V1.0   
*/
@Entity
@Table( name = "blog_role")
@Getter@Setter
public class Role {
	@Id
	@GenericGenerator(strategy="uuid" , name = "uuid")
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "uuid")
	private String id;
	
	private String name;
	
	
	@Column(length = 6)
	private  String type;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "blog_user_role",
			joinColumns = {@JoinColumn(name = "role_id") },
			inverseJoinColumns = {@JoinColumn(name = "user_id")}
			)
	private List<User> users = new ArrayList<>();
	@ManyToMany(mappedBy = "roles",fetch=FetchType.LAZY)
	private Set<Permissions> permissions;
}
