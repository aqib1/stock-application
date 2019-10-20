package com.db.business.applicationbusiness;

import com.db.Exception.DBException;
import com.db.helper.DBHelper;
import com.db.models.app.contents.ApplicationModel;
import com.db.services.applicationservice.ApplicationService;
import com.db.services.applicationservice.ApplicationServiceImpl;
import com.db.utility.AppUtils;

public class ApplicationBusiness {

	private ApplicationService service = new ApplicationServiceImpl();

	public ApplicationModel getApplication() throws DBException {
		ApplicationModel model = service.getApplication(DBHelper.getInstance().getConnection());
		DBHelper.getInstance().clear();
		if (AppUtils.isNull(model))
			throw new IllegalArgumentException("application model is null in database");
		return model;
	}

	public int updateApplicationModel(ApplicationModel model) throws DBException {
		if (AppUtils.isNull(model)) {
			throw new IllegalArgumentException("application model cannot be null!!!");
		}
		if (AppUtils.isNullOrEmptyString(model.getApp_name())) {
			throw new IllegalArgumentException("app name cannot be null or empty!!!");
		}
		int update = service.updateApplication(DBHelper.getInstance().getConnection(), model);
		DBHelper.getInstance().clear();
		return update;
	}

}
