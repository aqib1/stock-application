package com.db.models.app.contents;

public class HeaderModel {

	private Integer id;
	private String background;
	private String isActive;
	private String isBorder;
	private String isRound;
	private String title;
	private String titleFont;
	private Integer titleSize;
	private String titleColor;
	private String titleStyle;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsBorder() {
		return isBorder;
	}

	public void setIsBorder(String isBorder) {
		this.isBorder = isBorder;
	}

	public String getIsRound() {
		return isRound;
	}

	public void setIsRound(String isRound) {
		this.isRound = isRound;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleFont() {
		return titleFont;
	}

	public void setTitleFont(String titleFont) {
		this.titleFont = titleFont;
	}

	public Integer getTitleSize() {
		return titleSize;
	}

	public void setTitleSize(Integer titleSize) {
		this.titleSize = titleSize;
	}

	public String getTitleColor() {
		return titleColor;
	}

	public void setTitleColor(String titleColor) {
		this.titleColor = titleColor;
	}

	public String getTitleStyle() {
		return titleStyle;
	}

	public void setTitleStyle(String titleStyle) {
		this.titleStyle = titleStyle;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HeaderModel [id=");
		builder.append(id);
		builder.append(", background=");
		builder.append(background);
		builder.append(", isActive=");
		builder.append(isActive);
		builder.append(", isBorder=");
		builder.append(isBorder);
		builder.append(", isRound=");
		builder.append(isRound);
		builder.append(", title=");
		builder.append(title);
		builder.append(", titleFont=");
		builder.append(titleFont);
		builder.append(", titleSize=");
		builder.append(titleSize);
		builder.append(", titleColor=");
		builder.append(titleColor);
		builder.append(", titleStyle=");
		builder.append(titleStyle);
		builder.append("]");
		return builder.toString();
	}

}
