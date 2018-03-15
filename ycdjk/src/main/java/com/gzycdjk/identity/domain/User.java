package com.gzycdjk.identity.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;

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

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ycdjk_user")
@Getter@Setter
public class User {
	
	@Id
	@GenericGenerator(name = "uuid" ,strategy = "uuid")
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "uuid")
	@Column(name = "id")
	private String  id;
	
	/**
	 * 登录密码
	 */
	private String password;
	/**
	 * 登录名
	 */
	@Column(name = "login_Name")
	private String loginName;
	
	/**
	 * 用户名
	 */
	@Column( name = "user_Name")
	private String userName;
	
	/**
	 * 激活状态
	 */
	private Boolean enable;
	
	
	/**
	 * 角色对应的角色列表
	 */
	@JsonBackReference
	@ManyToMany(mappedBy = "users")
	private Set<Role> roles; 
	
	private String email;
	
	@Column(name = "phone_Number" , length = 12 )
	private String phoneNumber;
	
	private Integer qq;
	
	/**
	 * 性别
	 */
	@Enumerated(EnumType.STRING)
	private Sex sex;
	
	/**
	 * 出生日期
	 */
	private Date birthdate;
	
	
}
