package com.db.business.headerbusiness;

import com.db.Exception.DBException;
import com.db.helper.DBHelper;
import com.db.models.app.contents.HeaderModel;
import com.db.services.headerservice.HeaderService;
import com.db.services.headerservice.HeaderServiceImpl;
import com.db.utility.AppUtils;

public class HeaderBusiness {
	private HeaderService headerService = new HeaderServiceImpl();

	public HeaderModel updateHeader(HeaderModel model) throws DBException {
		if (AppUtils.isNull(model))
			throw new IllegalArgumentException("null model object is not allowed");
		if (AppUtils.isNullOrEmptyString(model.getTitle()))
			throw new IllegalArgumentException("title null or empty is not allowed");
		if (AppUtils.isNullOrZeroInteger(model.getId()))
			throw new IllegalArgumentException("id null or zero is not allowed");

		HeaderModel headerModel = headerService.updateHeader(DBHelper.getInstance().getConnection(), model);
		DBHelper.getInstance().clear();
		if (AppUtils.isNull(headerModel))
			throw new IllegalArgumentException("Header model recieved null from database!!");
		return headerModel;
	}

	public HeaderModel getHeader() throws DBException {
		HeaderModel headerModel = headerService.getHeader(DBHelper.getInstance().getConnection());
		DBHelper.getInstance().clear();
		if (AppUtils.isNull(headerModel))
			throw new IllegalArgumentException("Header model recieved null from database!!");
		return headerModel;
	}
}
