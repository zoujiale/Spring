package com.gzycdjk.commons.until;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.gzycdjk.commons.pojo.TreeNode;
/**
 * 生成树的工具类
 * @author YCJKmr.zo
 *
 */
public class TreeUntil {
	public static List<TreeNode> getTreeNodeList(Map<String, TreeNode> treeNode){
		List<TreeNode> tr = new LinkedList<>();
		for (String tn : treeNode.keySet()) {
			TreeNode node = treeNode.get(tn);
			if (node.getParentId()==null || node.getParentId() =="") {
				// 一级菜单
				tr.add(node);
			}else {
				if (treeNode.get(node.getParentId()).getNodes()==null) {
					System.out.println(treeNode.get(node.getParentId()).getNodes());
					treeNode.get(node.getParentId()).setNodes(new ArrayList<TreeNode>());
				}
				 treeNode.get(node.getParentId()).getNodes().add(node);
			}
			
		}
		return tr;
		
	}
}
