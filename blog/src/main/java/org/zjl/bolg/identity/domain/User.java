package org.zjl.bolg.identity.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

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
	
	@Column(name = "login_name")
	private String loginName;
	
	@Column(length = 50)
	private String password;
	
	@Column(name = "create_date")
	private Date createDate;
	
	/**
	 * 激活状态
	 */
	private Boolean enable;	
	
	/**
	 * 以枚举的内容存储，不以索引
	 */
	@Enumerated(EnumType.STRING)
	private Sex sex;
	
	private String email;
	
	@Column(length = 10 )
	private String username;
	
	@Column(name = "phone_number")
	private Integer phoneNumber;
	
	@OneToMany(mappedBy = "user")
	List<Guestbook> guestbook;
	
}