package com.db.models.app.contents;

public class ApplicationModel {

	private Integer app_id;
	private String app_name;
	private String window_name;
	private Integer footer_id;
	private Integer header_id;

	public String getWindow_name() {
		return window_name;
	}

	public void setWindow_name(String window_name) {
		this.window_name = window_name;
	}

	public Integer getApp_id() {
		return app_id;
	}

	public void setApp_id(Integer app_id) {
		this.app_id = app_id;
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public Integer getFooter_id() {
		return footer_id;
	}

	public void setFooter_id(Integer footer_id) {
		this.footer_id = footer_id;
	}

	public Integer getHeader_id() {
		return header_id;
	}

	public void setHeader_id(Integer header_id) {
		this.header_id = header_id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ApplicationModel [app_id=");
		builder.append(app_id);
		builder.append(", app_name=");
		builder.append(app_name);
		builder.append(", footer_id=");
		builder.append(footer_id);
		builder.append(", header_id=");
		builder.append(header_id);
		builder.append("]");
		return builder.toString();
	}

}
