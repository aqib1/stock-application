package com.db.models.screencontents;

public class ScreenContentModel {

	private Integer id;
	private String title;
	private String key;
	private Integer screen_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getScreen_id() {
		return screen_id;
	}

	public void setScreen_id(Integer screen_id) {
		this.screen_id = screen_id;
	}

	@Override
	public String toString() {
		return title;
	}

}
