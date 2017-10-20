package org.zjl.bolg.identity.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: Article 
 * @Description: 博客的博文信息
 * @author zou
 * @date 2017年10月14日 上午10:18:22 
 */

@Entity
@Table(name = "blog_article")
@Data
public class Article {
	@Id
	@GenericGenerator(strategy = "uuid",name = "uuid")
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "uuid")
	private String id;
	
	/**
	 * 博客标题
	 */
	private String headline;
	/**
	 * 记录主要内容
	 */
	@Column( name = "main_body")
	private String mainBody;
	
	@Column( name = "create_date")
	private Date createDate;
	
	/**
	 * 这记录二级标题
	 */
	@Column( name = "sub_title")
	private String subTitle;
	
	@ManyToOne()
	private User user;
}
