package com.gzycdjk.identity.domain;

import java.util.Date;
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

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ycdjk_role")
@Getter@Setter
public class Role {
	@Id
	@GenericGenerator(name = "uuid" ,strategy = "uuid")
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "uuid")
	private String id;
	
	private String name;
	
	/**
	 * 激活状态
	 */
	private Boolean enable;
	@JSONField(serialize = false) 
	@ManyToMany
	@JoinTable(name = "ycdjk_role_user",joinColumns = {@JoinColumn(name = "role_id")},
	inverseJoinColumns = {@JoinColumn(name = "user_id")})
	private List<User> users;
	
	@JSONField(serialize = false) 
	@ManyToMany(mappedBy = "roles",fetch =FetchType.EAGER)
	private Set<Permission> permissions;
	
	/*
	 * 备注
	 */
	private String remark;
	
	/**
	 * 创建日期
	 */
	@JSONField(format = "yyyy-MM-dd")  
	@Column( name = "create_date_time")
	private Date createDateTime;
}
