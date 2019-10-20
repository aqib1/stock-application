package com.db.services.applicationservice;

import java.sql.Connection;

import com.db.Exception.DBException;
import com.db.models.app.contents.ApplicationModel;
import com.db.utility.AppUtils;

public interface ApplicationService {

	String SELECT_FIRST_APPLICATION = "SELECT * FROM " + AppUtils.APPLICATION_TABLE + " LIMIT 1";
	String UPDATE_APPLICATION = "UPDATE " + AppUtils.APPLICATION_TABLE + " SET " + AppUtils.APPLICATION_COLUMN_APP_NAME
			+ " = ? ";

	ApplicationModel getApplication(Connection connection) throws DBException;

	int updateApplication(Connection connection, ApplicationModel model) throws DBException;

	static String GET_UPDATE_DYNAMIC_QUERY(ApplicationModel model) {
		String dynamicQuery = UPDATE_APPLICATION;
		if (!AppUtils.isNullOrZeroInteger(model.getFooter_id()))
			dynamicQuery += ", " + AppUtils.APPLICATION_COLUMN_FOOTER_ID + "= ?";
		if (!AppUtils.isNullOrZeroInteger(model.getHeader_id()))
			dynamicQuery += ", " + AppUtils.APPLICATION_COLUMN_HEADER_ID + "= ?";
		if (!AppUtils.isNullOrEmptyString(model.getWindow_name()))
			dynamicQuery += ", " + AppUtils.APPLICATION_COLUMN_WINDOW_TITLE + " = ?";
		dynamicQuery += " WHERE " + AppUtils.APPLICATION_COLUMN_ID + " = ? ";
		return dynamicQuery;
	}
}
