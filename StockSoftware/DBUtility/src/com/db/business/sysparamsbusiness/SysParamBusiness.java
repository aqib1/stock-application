package com.db.business.sysparamsbusiness;

import com.db.Exception.DBException;
import com.db.helper.DBHelper;
import com.db.models.sysparams.SysParamModel;
import com.db.models.sysparams.SysParams;
import com.db.services.sysparamservice.SysParamService;
import com.db.services.sysparamservice.SysParamServiceImpl;
import com.db.utility.AppUtils;

public class SysParamBusiness {

	private SysParamService sysParamService = new SysParamServiceImpl();

	public boolean isAuthenticationRequired() throws DBException {
		boolean allow = false;
		SysParamModel model = sysParamService.getSysParamModel(DBHelper.getInstance().getConnection(),
				SysParams.ALLOW_AUTH_PARAM.getParam());
		DBHelper.getInstance().clear();
		if (!AppUtils.isNull(model))
			allow = true;
		return allow;
	}
}
