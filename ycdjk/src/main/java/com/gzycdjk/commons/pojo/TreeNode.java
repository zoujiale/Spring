package com.gzycdjk.commons.pojo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class TreeNode {
	private String id;
	private String orderNumber;
	private String text;
	private List<TreeNode>nodes;
	private String parentId;

}
