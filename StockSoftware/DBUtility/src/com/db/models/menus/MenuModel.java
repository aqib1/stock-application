package com.db.models.menus;

public class MenuModel {

	private Integer id;
	private String title;
	private String key;

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return title;
	}
}
