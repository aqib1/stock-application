package com.db.models.sysparams;

public enum SysParams {
	ALLOW_AUTH_PARAM("#AllowAuthParam");

	private String param;

	private SysParams(String param) {
		this.param = param;
	}
	
	public String getParam() {
		return param;
	}
}
