package com.gzycdjk.identity.domain;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.core.annotation.Order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ycdjk_permission")
@Getter@Setter
public class Permission {

	@Id
	@GenericGenerator(name = "uuid" ,strategy = "uuid")
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "uuid")
	private String id;
	
	/**
	 * 权限的名称
	 */
	private String text;
	
	/**
	 * 访问url地址
	 */
	private String url;
	
	/**
	 * 权限代码字符串
	 */
	private String percode;
	
	/*
	 * 二级目录
	 */
	@OneToMany(mappedBy = "parent" )
	@JsonProperty(value = "nodes")
	@OrderBy(value = "orderNumber")
	private List<Permission> child;
	
	/**
	 * 上级
	 */
	@ManyToOne()
	@JsonBackReference
	@JoinColumn(name = "parent_id")
	private Permission parent;
	
	/**
	 * 排序的序号
	 */
	@Column(name = "order_number")
	private String orderNumber;
	

	/**
	 * 有权使用角色的菜单
	 */
	@JsonBackReference
	@ManyToMany
	@JoinTable( name = "ycdjk_permission_role",joinColumns = { @JoinColumn(name = "permission_id")},
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
}
