package com.db.models.app.contents;

public class FooterModel {

	private String content;
	private String contentTextFont;
	private String contentTextStyle;
	private Integer contentTextSize;
	private String contentTextColor;
	private Integer id;
	private String background;
	private String isActive;
	private String isBorder;
	private String isRound;
	private String isClock;

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

	public String getIsClock() {
		return isClock;
	}

	public void setIsClock(String isClock) {
		this.isClock = isClock;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentTextFont() {
		return contentTextFont;
	}

	public void setContentTextFont(String contentTextFont) {
		this.contentTextFont = contentTextFont;
	}

	public String getContentTextStyle() {
		return contentTextStyle;
	}

	public void setContentTextStyle(String contentTextStyle) {
		this.contentTextStyle = contentTextStyle;
	}

	public Integer getContentTextSize() {
		return contentTextSize;
	}

	public void setContentTextSize(Integer contentTextSize) {
		this.contentTextSize = contentTextSize;
	}

	public String getContentTextColor() {
		return contentTextColor;
	}

	public void setContentTextColor(String contentTextColor) {
		this.contentTextColor = contentTextColor;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FooterModel [content=");
		builder.append(content);
		builder.append(", contentTextFont=");
		builder.append(contentTextFont);
		builder.append(", contentTextStyle=");
		builder.append(contentTextStyle);
		builder.append(", contentTextSize=");
		builder.append(contentTextSize);
		builder.append(", contentTextColor=");
		builder.append(contentTextColor);
		builder.append(", id=");
		builder.append(id);
		builder.append(", background=");
		builder.append(background);
		builder.append(", isActive=");
		builder.append(isActive);
		builder.append(", isBorder=");
		builder.append(isBorder);
		builder.append(", isRound=");
		builder.append(isRound);
		builder.append(", isClock=");
		builder.append(isClock);
		builder.append("]");
		return builder.toString();
	}

}
