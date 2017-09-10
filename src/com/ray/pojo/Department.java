package com.ray.pojo;

public class Department {
	private int id ;
	private String name;
	private int parentid;
	private int order;
	
	
	
	public Department(int id, String name, int parentid) {
		super();
		this.id = id;
		this.name = name;
		this.parentid = parentid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	
	
}
