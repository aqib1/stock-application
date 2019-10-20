package com.db.services.sysparamservice;

import java.sql.Connection;

import com.db.Exception.DBException;
import com.db.models.sysparams.SysParamModel;
import com.db.utility.AppUtils;

public interface SysParamService {

	String SELECT_SYS_PARAM="SELECT * FROM "+AppUtils.SYSPARAMS_TABLE_NAME+ " WHERE "+AppUtils.SYSPARAMS_TABLE_PARAM+" =? AND "+AppUtils.SYSPARAMA_TABLE_ISACTIVE+" = '"+AppUtils.YES+"'";
	
	SysParamModel getSysParamModel (Connection connection, String param) throws DBException;
}
