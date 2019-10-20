package com.db.services.screencontentservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.db.Exception.DBException;
import com.db.models.screencontents.ScreenContentModel;
import com.db.utility.AppUtils;

public class ScreenContentServiceImpl implements ScreenContentsService {

	@Override
	public Map<String, ScreenContentModel> getAllByScreenId(Connection connection, Integer screen_id) throws DBException {
		PreparedStatement preparedStatement = null;
		Map<String, ScreenContentModel> screenContentModels = null;
		ResultSet resultSet = null;
		int index = 0;
		try {
			preparedStatement = connection.prepareStatement(SELECT_ALL_BY_SCREEN_ID);
			preparedStatement.setInt(++index, screen_id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next() == false) {
				return null;
			} else {
				screenContentModels = new HashMap<>();
				do {
					ScreenContentModel model = getScreenContentModelFromResultSet(resultSet);
					screenContentModels.put(model.getKey(), model);
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
		return screenContentModels;
	}

	private ScreenContentModel getScreenContentModelFromResultSet(ResultSet resultSet) throws SQLException {
		ScreenContentModel screenContentModel = new ScreenContentModel();
		screenContentModel.setId(resultSet.getInt(AppUtils.SCREEN_CONTENTS_TABLE_ID));
		screenContentModel.setTitle(resultSet.getString(AppUtils.SCREEN_CONTENTS_TABLE_TITLE));
		screenContentModel.setKey(resultSet.getString(AppUtils.SCREEN_CONTENTS_TABLE_KEY));
		screenContentModel.setScreen_id(resultSet.getInt(AppUtils.SCREEN_CONTENTS_TABLE_SCREEN_ID));
		return screenContentModel;
	}

	@Override
	public ScreenContentModel updateScreenContentModel(Connection connection, ScreenContentModel screenContentModel) throws DBException {
		PreparedStatement preparedStatement = null;
		int index = 0;
		try {
			preparedStatement = connection.prepareStatement(UPDATE_SCREEN_CONTENTS_TITLE_BY_KEY);
			preparedStatement.setString(++index, screenContentModel.getTitle());
			preparedStatement.setString(++index, screenContentModel.getKey());
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
		return screenContentModel;
	}

}
