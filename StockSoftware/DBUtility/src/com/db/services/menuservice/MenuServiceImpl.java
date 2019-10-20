package com.db.services.menuservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.db.Exception.DBException;
import com.db.models.menus.MenuModel;
import com.db.utility.AppUtils;

public class MenuServiceImpl implements MenuService {

	@Override
	public Map<String, MenuModel> getAllMenus(Connection connection) throws DBException {
		Statement statement = null;
		ResultSet resultSet = null;
		Map<String, MenuModel> data = new HashMap<>();
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SELECT_ALL);
			while (resultSet.next()) {
				MenuModel menuModel = new MenuModel();
				menuModel.setId(resultSet.getInt(AppUtils.MENUS_COLUMN_ID));
				menuModel.setTitle(resultSet.getString(AppUtils.MENUS_COLUMN_TITLE));
				menuModel.setKey(resultSet.getString(AppUtils.MENUS_COLUMN_KEY));
				data.put(menuModel.getKey(), menuModel);
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
		return data;
	}

	@Override
	public int updateMenu(Connection connection, MenuModel model) throws DBException {
		PreparedStatement preparedStatement = null;
		int i = -1;
		int index = 0;
		try {
			preparedStatement = connection.prepareStatement(UPDATE_MENU);
			preparedStatement.setString(++index, model.getTitle());
			preparedStatement.setString(++index, model.getKey());
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

}
