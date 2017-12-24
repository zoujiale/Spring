package zou.identity.domain;

import java.util.List;

import javax.persistence.Entity;
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

@Entity
@Table( name ="blog_role_permissions")
@Getter@Setter
public class Permissions {
	@Id
	@GenericGenerator(strategy="uuid" , name = "uuid")
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "uuid")
	private String id;
	
	private String name;
	
	private String url;
	@ManyToMany()
	@JoinTable(name = "role_Permissions",
			joinColumns = {@JoinColumn(name = "p_id")},
			inverseJoinColumns = {@JoinColumn(name="role_id")}
			
			)
	private List<Role> roles;
}
