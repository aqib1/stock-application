package com.db.services.headerservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import com.db.Exception.DBException;
import com.db.models.app.contents.HeaderModel;
import com.db.utility.AppUtils;

public class HeaderServiceImpl implements HeaderService {

	@Override
	public HeaderModel getHeader(Connection connection) throws DBException {
		HeaderModel model = new HeaderModel();
		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SELECT_QUERY_HEADER)) {
			if (resultSet.next() == false) {
				return null;
			} else {
				do {
					model.setId(resultSet.getInt(AppUtils.HEADER_TABLE_ID));
					model.setBackground(resultSet.getString(AppUtils.HEADER_TABLE_BACKGROUND));
					model.setIsActive(resultSet.getString(AppUtils.HEADER_TABLE_ISACTIVE));
					model.setIsBorder(resultSet.getString(AppUtils.HEADER_TABLE_ISBORDER));
					model.setIsRound(resultSet.getString(AppUtils.HEADER_TABLE_ISROUND));
					model.setTitle(resultSet.getString(AppUtils.HEADER_TABLE_TITLE));
					model.setTitleColor(resultSet.getString(AppUtils.HEADER_TABLE_TITLE_COLOR));
					model.setTitleFont(resultSet.getString(AppUtils.HEADER_TABLE_TITLE_FONT));
					model.setTitleSize(resultSet.getInt(AppUtils.HEADER_TABLE_TITLE_SIZE));
					model.setTitleStyle(resultSet.getString(AppUtils.HEADER_TABLE_TITLE_STYLE));
				} while (resultSet.next());
			}

		} catch (SQLException e) {
			throw new DBException(e.getMessage(), e);
		}
		return model;
	}

	@Override
	public HeaderModel updateHeader(Connection connection, HeaderModel model) throws DBException {
		if (Objects.isNull(model))
			return null;
		try (PreparedStatement preparedStatement = getUpdateHeaderPreparedStatement(connection, model)) {
			if (preparedStatement.executeUpdate() == 0) {
				return null;
			}
		} catch (SQLException e) {
			throw new DBException(e.getMessage(), e);
		}
		return model;
	}

	private PreparedStatement getUpdateHeaderPreparedStatement(Connection connection, HeaderModel headerModel)
			throws SQLException {
		PreparedStatement preparedStatement = null;
		int index = 0;
		preparedStatement = connection.prepareStatement(HeaderService.getDynamicQueryForHeaderUpdate(headerModel));
		preparedStatement.setString(++index, headerModel.getTitle());
		if (!AppUtils.isNullOrEmptyString(headerModel.getBackground())) {
			preparedStatement.setString(++index, headerModel.getBackground());
		}
		if (!AppUtils.isNullOrEmptyString(headerModel.getIsActive())) {
			preparedStatement.setString(++index, headerModel.getIsActive());
		}
		if (!AppUtils.isNullOrEmptyString(headerModel.getIsRound())) {
			preparedStatement.setString(++index, headerModel.getIsRound());
		}
		if (!AppUtils.isNullOrEmptyString(headerModel.getIsBorder())) {
			preparedStatement.setString(++index, headerModel.getIsBorder());
		}
		if (!AppUtils.isNullOrEmptyString(headerModel.getTitleColor())) {
			preparedStatement.setString(++index, headerModel.getTitleColor());
		}
		if (!AppUtils.isNullOrEmptyString(headerModel.getTitleFont())) {
			preparedStatement.setString(++index, headerModel.getTitleFont());
		}
		if (!AppUtils.isNullOrEmptyString(headerModel.getTitleStyle())) {
			preparedStatement.setString(++index, headerModel.getTitleStyle());
		}
		if (!AppUtils.isNullOrZeroInteger(headerModel.getTitleSize())) {
			preparedStatement.setInt(++index, headerModel.getTitleSize());
		}

		preparedStatement.setInt(++index, headerModel.getId());

		return preparedStatement;
	}
}
