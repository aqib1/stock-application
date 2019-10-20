package com.db.models.sysparams;

public class SysParamModel {

	private Integer id;
	private String param;
	private String isAllow;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getIsAllow() {
		return isAllow;
	}

	public void setIsAllow(String isAllow) {
		this.isAllow = isAllow;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SysParamModel [id=");
		builder.append(id);
		builder.append(", param=");
		builder.append(param);
		builder.append(", isAllow=");
		builder.append(isAllow);
		builder.append("]");
		return builder.toString();
	}

}
