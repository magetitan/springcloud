package com.my.springcloud.common.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Tree<T> {
	
	private String id;
	private String name;
	private String parentId;
	private T t;
	private List<Tree<T>> children;
	
	
	public Tree() {
		super();
	}
	
	public Tree(String id) {
		super();
		this.id = id;
	}

	public Tree(String id, String name, String parentId, T t) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.t = t;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	public List<Tree<T>> getChildren() {
		return children;
	}
	public void setChildren(List<Tree<T>> children) {
		this.children = children;
	}
	public static <E> Tree<E> getTree(Function<String, List<Tree<E>>> fun,Tree<E> parent){
		List<Tree<E>> list = fun.apply(parent.getId());
		parent.setChildren(list);
		if(list!=null&&list.size()>0) {
			list.stream().forEach(tree -> getTree(fun, tree));
		}
		return parent;
	}
	public static <E> Tree<E> tree(Function<String, List<E>> fun,Tree<E> parent) {
		List<E> es = fun.apply(parent.getId());
		List<Tree<E>> trees = new ArrayList<Tree<E>>(); 
		if(es!=null&&es.size()>0) {
			es.stream().forEach(e -> {
	 			try {
					String id = (String) e.getClass().getMethod("getId").invoke(e);
					String name = (String) e.getClass().getMethod("getId").invoke(e);
					String parendId = (String) e.getClass().getMethod("getId").invoke(e);
					Tree<E> tree = new Tree<E>(id,name,parendId,e);
					trees.add(tree);
					tree(fun, tree);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			});
		}
		parent.setChildren(trees);
		return parent;
	}
}
