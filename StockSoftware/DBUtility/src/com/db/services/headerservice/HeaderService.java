package com.db.services.headerservice;

import java.sql.Connection;

import com.db.Exception.DBException;
import com.db.models.app.contents.HeaderModel;
import com.db.utility.AppUtils;

public interface HeaderService {

	String SELECT_QUERY_HEADER = "SELECT * FROM " + AppUtils.HEADER_TABLE_NAME + " LIMIT 1";
	String UPDATE_QUERY_HEADER = "UPDATE " + AppUtils.HEADER_TABLE_NAME + " SET " + AppUtils.HEADER_TABLE_TITLE
			+ " = ?";

	static String getDynamicQueryForHeaderUpdate(HeaderModel headerModel) {
		String dynamicQuery = UPDATE_QUERY_HEADER;
		if (!AppUtils.isNullOrEmptyString(headerModel.getBackground())) {
			dynamicQuery += " , " + AppUtils.HEADER_TABLE_BACKGROUND + " = ?";
		}
		if (!AppUtils.isNullOrEmptyString(headerModel.getIsActive())) {
			dynamicQuery += " , " + AppUtils.HEADER_TABLE_ISACTIVE + " = ?";
		}
		if (!AppUtils.isNullOrEmptyString(headerModel.getIsRound())) {
			dynamicQuery += " , " + AppUtils.HEADER_TABLE_ISROUND + " = ?";
		}
		if (!AppUtils.isNullOrEmptyString(headerModel.getIsBorder())) {
			dynamicQuery += " , " + AppUtils.HEADER_TABLE_ISBORDER+ " = ?";
		}
		if (!AppUtils.isNullOrEmptyString(headerModel.getTitleColor())) {
			dynamicQuery += " , " + AppUtils.HEADER_TABLE_TITLE_COLOR + " = ?";
		}
		if (!AppUtils.isNullOrEmptyString(headerModel.getTitleFont())) {
			dynamicQuery += " , " + AppUtils.HEADER_TABLE_TITLE_FONT + " = ?";
		}
		if (!AppUtils.isNullOrEmptyString(headerModel.getTitleStyle())) {
			dynamicQuery += " , " + AppUtils.HEADER_TABLE_TITLE_STYLE+ " = ?";
		}
		if (!AppUtils.isNullOrZeroInteger(headerModel.getTitleSize())) {
			dynamicQuery += " , " + AppUtils.HEADER_TABLE_TITLE_SIZE + " = ?";
		}
		
		dynamicQuery += " WHERE "+AppUtils.HEADER_TABLE_ID + " = ?";
		return dynamicQuery;
	}

	HeaderModel getHeader(Connection connection) throws DBException;

	HeaderModel updateHeader(Connection connection, HeaderModel model) throws DBException;
}
