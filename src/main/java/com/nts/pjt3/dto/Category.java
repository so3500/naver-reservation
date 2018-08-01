package com.nts.pjt3.dto;

public class Category {

	private int id;
	private int count;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return String.format("Category [id=%s, count=%s, name=%s]", id, count, name);
	}

}
