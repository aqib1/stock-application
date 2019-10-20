package com.db.services.screenservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.db.Exception.DBException;
import com.db.models.screens.ScreenModel;
import com.db.utility.AppUtils;

public class ScreenServiceImpl implements ScreenService {
	
	private ScreenModel getScreenModelFromResultSet(ResultSet resultSet) throws SQLException{
		ScreenModel screenModel = new ScreenModel();
		screenModel.setId(resultSet.getInt(AppUtils.SCREEN_TABLE_ID));
		screenModel.setTitle(resultSet.getString(AppUtils.SCREEN_TABLE_TITLE));
		screenModel.setKey(resultSet.getString(AppUtils.SCREEN_TABLE_KEY));
		screenModel.setMenu_id(resultSet.getInt(AppUtils.SCREEN_TABLE_MENU_ID));
		return screenModel;
	}

	@Override
	public Map<String, ScreenModel> getAllScreensByMenuId(Connection connection, Integer menu_id) throws DBException {
		PreparedStatement preparedStatement = null;
		Map<String, ScreenModel> screenModels = null;
		ResultSet resultSet = null;
		int index = 0;
		try {
			preparedStatement = connection.prepareStatement(GET_ALL_SCREEN_BY_MENU_ID);
			preparedStatement.setInt(++index, menu_id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next() == false) {
				return null;
			} else {
				screenModels = new HashMap<>();
				do {
					ScreenModel model = getScreenModelFromResultSet(resultSet);
					screenModels.put(model.getKey(), model);
				} while (resultSet.next());

			}
		} catch (SQLException e) {
			throw new DBException(e.getMessage(), e);
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				throw new DBException(e.getMessage(), e);
			}

		}
		return screenModels;
	}

	@Override
	public ScreenModel updateScreenModel(Connection connection, ScreenModel screenModel) throws DBException {
		PreparedStatement preparedStatement = null;
		int index = 0;
		try {
			preparedStatement = connection.prepareStatement(UPDATE_SCREEN_TITLE);
			preparedStatement.setString(++index, screenModel.getTitle());
			preparedStatement.setString(++index, screenModel.getKey());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DBException(e.getMessage(), e);
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				throw new DBException(e.getMessage(), e);
			}

		}
		return screenModel;
	}

}
