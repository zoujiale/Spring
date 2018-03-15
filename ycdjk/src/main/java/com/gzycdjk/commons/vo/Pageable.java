package com.gzycdjk.commons.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 拿到前台属性分页,跟Page搭配使用
 * @author YCJKmr.zou
 *
 */
@Getter@Setter
public class Pageable {
	
	public Pageable() {
		super();
	}
	private Integer pageSize;
	public Pageable(Integer pageSize, Integer pageNum) {
		super();
		this.pageSize = pageSize;
		this.pageNum = pageNum;
	}
	private Integer pageNum;

}
