package com.db.services.footerservice;

import java.sql.Connection;

import com.db.Exception.DBException;
import com.db.models.app.contents.FooterModel;
import com.db.utility.AppUtils;

public interface FooterService {

	String UPDATE_QUERY_FOOTER = "UPDATE " + AppUtils.FOOTER_TABLE_NAME + " SET " + AppUtils.FOOTER_TABLE_CONTENT
			+ " = ?";

	String SELECT_QUERY_FOOTER = "SELECT * FROM " + AppUtils.FOOTER_TABLE_NAME + " LIMIT 1";

	FooterModel update(Connection connection, FooterModel model) throws DBException;

	FooterModel getFooter(Connection connection) throws DBException;

	static String getDynamicQueryForFotterUpdate(FooterModel footerModel) {
		String dynamic = UPDATE_QUERY_FOOTER;
		if (!AppUtils.isNullOrEmptyString(footerModel.getBackground()))
			dynamic += " , " + AppUtils.FOOTER_TABLE_BACKGROUND + "=?";
		if (!AppUtils.isNullOrEmptyString(footerModel.getIsActive()))
			dynamic += " , " + AppUtils.FOOTER_TABLE_ISACTIVE+" =?";
		if (!AppUtils.isNullOrEmptyString(footerModel.getIsBorder()))
			dynamic += " , " + AppUtils.FOOTER_TABLE_ISBORDER + "=?";
		if (!AppUtils.isNullOrEmptyString(footerModel.getIsClock()))
			dynamic += " , " + AppUtils.FOOTER_TABLE_CLOCK + "=?";
		if (!AppUtils.isNullOrEmptyString(footerModel.getIsRound()))
			dynamic += " , " + AppUtils.FOOTER_TABLE_ISROUND + "=?";
		if (!AppUtils.isNullOrEmptyString(footerModel.getContentTextColor()))
			dynamic += " , " + AppUtils.FOOTER_TABLE_CONTENT_TEXT_COLOR + "=?";
		if (!AppUtils.isNullOrEmptyString(footerModel.getContentTextFont()))
			dynamic += " , " + AppUtils.FOOTER_TABLE_CONTENT_TEXT_FONT + "=?";
		if (!AppUtils.isNullOrEmptyString(footerModel.getContentTextStyle()))
			dynamic += " , " + AppUtils.FOOTER_TABLE_CONTENT_TEXT_STYLE + "=?";
		if (!AppUtils.isNullOrZeroInteger(footerModel.getContentTextSize()))
			dynamic += " , " + AppUtils.FOOTER_TABLE_CONTENT_TEXT_SIZE + "=?";
		dynamic += " WHERE " + AppUtils.FOOTER_TABLE_ID + " = ?";
		return dynamic;
	}
}
