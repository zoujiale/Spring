package com.gzycdjk.identity.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;


/**
 * 这里是新闻中心
 * @author YCJKmr.zo
 *
 */


@Entity	
@Table(name="ycdjk_news")
@Getter@Setter
public class Article {
	
	@Id
	@GenericGenerator(name = "uuid" ,strategy = "uuid")
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "uuid")
	private String id;
	/**
	 * 标题
	 */
	private String title;
	
	@Column(name = "create_date")
	private Date createDate;
	/**
	 * 来源网站
	 */
	private String source;
	
	/**
	 * 简单描述
	 */
	@Column(name = "simple_Descripe")
	private String simpleDescripe;
	
	/**
	 * 图片名字
	 */
	@Column(name = "image_name")
	private String imageName;
	
	/**
	 * 正文内容
	 */
	@Column( name = "main_body")
	private String mainBody;
	
	@Column (name = "image_url")
	private String imageUrl;

}
