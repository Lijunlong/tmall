package com.tmall.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 树型工具类 
 * Created by Mr.Li on 2019/08/12
 */
public class TreeUtil {
	public TreeUtil() {
		super();
	}
	/**
	 * 两层循环实现建树
	 * 
	 * @param treeNodes 传入的树节点列表
	 * @return
	 */
	public static <T extends TreeNode> List<T> bulid(List<T> treeNodes, Object root) {
		List<T> trees = new ArrayList<T>();
		for (T treeNode : treeNodes) {
			if (root.equals(treeNode.getPid())) {
				trees.add(treeNode);
			}
			for (T it : treeNodes) {
				if (it.getPid() == treeNode.getId()) {
					if (treeNode.getChildren() == null) {
						treeNode.setChildren(new ArrayList<TreeNode>());
					}
					treeNode.add(it);
				}
			}
		}
		return trees;
	}

	/**
	 * 使用递归方法建树
	 * 
	 * @param treeNodes
	 * @return
	 */
	public static <T extends TreeNode> List<T> buildByRecursive(List<T> treeNodes, Object root) {
		List<T> trees = new ArrayList<T>();
		for (T treeNode : treeNodes) {
			if (root.equals(treeNode.getPid())) {
				trees.add(findChildren(treeNode, treeNodes));
			}
		}
		return trees;
	}

	/**
	 * 递归查找子节点
	 * 
	 * @param treeNodes
	 * @return
	 */
	public static <T extends TreeNode> T findChildren(T treeNode, List<T> treeNodes) {
		for (T it : treeNodes) {
			if (treeNode.getId() == it.getPid()) {
				if (treeNode.getChildren() == null) {
					treeNode.setChildren(new ArrayList<TreeNode>());
				}
				treeNode.add(findChildren(it, treeNodes));
			}
		}
		return treeNode;
	}

	class TreeNode {
		protected Long id;
		protected Long pid;
		protected List<TreeNode> children = new ArrayList<TreeNode>();
		public void add(TreeNode node) {
			children.add(node);
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Long getPid() {
			return pid;
		}
		public void setPid(Long pid) {
			this.pid = pid;
		}
		public List<TreeNode> getChildren() {
			return children;
		}
		public void setChildren(List<TreeNode> children) {
			this.children = children;
		}
	}
}
