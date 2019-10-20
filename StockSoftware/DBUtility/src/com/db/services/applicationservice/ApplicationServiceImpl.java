package com.db.services.applicationservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.db.Exception.DBException;
import com.db.models.app.contents.ApplicationModel;
import com.db.utility.AppUtils;

public class ApplicationServiceImpl implements ApplicationService {

	@Override
	public ApplicationModel getApplication(Connection connection) throws DBException {
		Statement statement = null;
		ResultSet resultSet = null;
		ApplicationModel applicationModel = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SELECT_FIRST_APPLICATION);
			if (resultSet.next() == false) {
				return null;
			} else {
				do {
					applicationModel = new ApplicationModel();
					applicationModel.setApp_id(resultSet.getInt(AppUtils.APPLICATION_COLUMN_ID));
					applicationModel.setApp_name(resultSet.getString(AppUtils.APPLICATION_COLUMN_APP_NAME));
					applicationModel.setHeader_id(resultSet.getInt(AppUtils.APPLICATION_COLUMN_HEADER_ID));
					applicationModel.setFooter_id(resultSet.getInt(AppUtils.APPLICATION_COLUMN_FOOTER_ID));
					applicationModel.setWindow_name(resultSet.getString(AppUtils.APPLICATION_COLUMN_WINDOW_TITLE));
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
		return applicationModel;
	}

	@Override
	public int updateApplication(Connection connection, ApplicationModel model) throws DBException {
		int i = -1;

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(ApplicationService.GET_UPDATE_DYNAMIC_QUERY(model));
			updatePrepareStatementOverApplicationModel(preparedStatement, model);
			i = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DBException(e.getMessage(), e);
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				throw new DBException(e.getMessage(), e);
			}
		}
		return i;
	}

	private void updatePrepareStatementOverApplicationModel(PreparedStatement preparedStatement,
			ApplicationModel model) throws SQLException {
		int index = 0;
		preparedStatement.setString(++index, model.getApp_name());
		if (!AppUtils.isNull(model.getFooter_id()))
			preparedStatement.setInt(++index, model.getFooter_id());
		if (!AppUtils.isNull(model.getHeader_id()))
			preparedStatement.setInt(++index, model.getHeader_id());
		if (!AppUtils.isNullOrEmptyString(model.getWindow_name()))
			preparedStatement.setString(++index, model.getWindow_name());
		preparedStatement.setInt(++index, model.getApp_id());

	}

}
