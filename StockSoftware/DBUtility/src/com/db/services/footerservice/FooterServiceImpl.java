package com.db.services.footerservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.db.Exception.DBException;
import com.db.models.app.contents.FooterModel;
import com.db.utility.AppUtils;

public class FooterServiceImpl implements FooterService {

	@Override
	public FooterModel update(Connection connection, FooterModel model) throws DBException {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = getPreparedStatmntQueryForFooterUpdate(connection, model);
			if (preparedStatement.executeUpdate() == 0)
				return null;
		} catch (SQLException e) {
			throw new DBException(e.getMessage(), e);
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				throw new DBException(e.getMessage(), e);
			}
		}
		return model;
	}

	private PreparedStatement getPreparedStatmntQueryForFooterUpdate(Connection connection, FooterModel model)
			throws SQLException {
		PreparedStatement preparedStatement = connection
				.prepareStatement(FooterService.getDynamicQueryForFotterUpdate(model));
		int index = 0;
		preparedStatement.setString(++index, model.getContent());
		if (!AppUtils.isNullOrEmptyString(model.getBackground()))
			preparedStatement.setString(++index, model.getBackground());
		if (!AppUtils.isNullOrEmptyString(model.getIsActive()))
			preparedStatement.setString(++index, model.getIsActive());
		if (!AppUtils.isNullOrEmptyString(model.getIsBorder()))
			preparedStatement.setString(++index, model.getIsBorder());
		if (!AppUtils.isNullOrEmptyString(model.getIsClock()))
			preparedStatement.setString(++index, model.getIsClock());
		if (!AppUtils.isNullOrEmptyString(model.getIsRound()))
			preparedStatement.setString(++index, model.getIsRound());
		if (!AppUtils.isNullOrEmptyString(model.getContentTextColor()))
			preparedStatement.setString(++index, model.getContentTextColor());
		if (!AppUtils.isNullOrEmptyString(model.getContentTextFont()))
			preparedStatement.setString(++index, model.getContentTextFont());
		if (!AppUtils.isNullOrEmptyString(model.getContentTextStyle()))
			preparedStatement.setString(++index, model.getContentTextStyle());
		if (!AppUtils.isNullOrZeroInteger(model.getContentTextSize()))
			preparedStatement.setInt(++index, model.getContentTextSize());
		preparedStatement.setInt(++index, model.getId());
		return preparedStatement;
	}

	@Override
	public FooterModel getFooter(Connection connection) throws DBException {
		Statement statement = null;
		ResultSet resultSet = null;
		FooterModel footerModel = new FooterModel();
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SELECT_QUERY_FOOTER);
			if (resultSet.next() == false) {
				return null;
			} else {
				do {
					footerModel.setId(resultSet.getInt(AppUtils.FOOTER_TABLE_ID));
					footerModel.setBackground(resultSet.getString(AppUtils.FOOTER_TABLE_BACKGROUND));
					footerModel.setIsBorder(resultSet.getString(AppUtils.FOOTER_TABLE_ISBORDER));
					footerModel.setIsActive(resultSet.getString(AppUtils.FOOTER_TABLE_ISACTIVE));
					footerModel.setIsClock(resultSet.getString(AppUtils.FOOTER_TABLE_CLOCK));
					footerModel.setIsRound(resultSet.getString(AppUtils.FOOTER_TABLE_ISROUND));
					footerModel.setContent(resultSet.getString(AppUtils.FOOTER_TABLE_CONTENT));
					footerModel.setContentTextColor(resultSet.getString(AppUtils.FOOTER_TABLE_CONTENT_TEXT_COLOR));
					footerModel.setContentTextFont(resultSet.getString(AppUtils.FOOTER_TABLE_CONTENT_TEXT_FONT));
					footerModel.setContentTextSize(resultSet.getInt(AppUtils.FOOTER_TABLE_CONTENT_TEXT_SIZE));
					footerModel.setContentTextStyle(resultSet.getString(AppUtils.FOOTER_TABLE_CONTENT_TEXT_STYLE));
				} while (resultSet.next());
			}
		} catch (SQLException e) {
			throw new DBException(e.getMessage(), e);
		} finally {
			try {
				resultSet.close();
				statement.close();
			} catch (SQLException e) {
				throw new DBException(e.getMessage(), e);
			}
		}

		return footerModel;
	}

}
