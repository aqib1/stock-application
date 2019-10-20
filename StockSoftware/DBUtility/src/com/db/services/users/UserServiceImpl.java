package com.db.services.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.db.Exception.DBException;
import com.db.models.users.UserModel;
import com.db.utility.AppUtils;

@SuppressWarnings("deprecation")
public class UserServiceImpl implements UserService {

	private PreparedStatement getPreparedStatmentForUser(Connection connection, UserModel userModel)
			throws SQLException {
		int index = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_USERNAME_PASSWORD);
		preparedStatement.setString(++index, userModel.getName());
		preparedStatement.setString(++index, userModel.getPassword());
		return preparedStatement;
	}

	public UserModel getUserByNameAndPassword(Connection connection, UserModel userModel) throws DBException {
		UserModel model = null;

		try (PreparedStatement statement = getPreparedStatmentForUser(connection, userModel);
				ResultSet resultSet = statement.executeQuery();) {
			if (resultSet.next() == false)
				return null;
			model = getUserDataFromResultSet(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage(), e);
		}

		return model;
	}

	public List<UserModel> getAllUsers(Connection connection) throws DBException {
		List<UserModel> users = null;
		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {
			if (resultSet.next()) {
				return null;
			} else {
				users = new ArrayList<>();
				do {
					users.add(getUserDataFromResultSet(resultSet));
				} while (resultSet.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage(), e);
		}

		return users;
	}

	private UserModel getUserDataFromResultSet(ResultSet resultSet) throws SQLException {
		UserModel userModel = new UserModel();
		userModel.setId(resultSet.getInt(AppUtils.USER_TABLE_ID));
		userModel.setName(resultSet.getString(AppUtils.USER_TABLE_USERNAME));
		userModel.setPassword(resultSet.getString(AppUtils.USER_TABLE_PASSWORD));
		userModel.setCreationDate(resultSet.getString(AppUtils.USER_TABLE_CREATION_DATE));
		return userModel;
	}

	@Override
	public UserModel getUserById(Connection connection, UserModel userModel) throws DBException {
		UserModel model = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int index = 0;
		try {
			statement = connection.prepareStatement(SELECT_BY_USERID);
			statement.setInt(++index, userModel.getId());
			resultSet = statement.executeQuery();
			if (resultSet.next() == false)
				return null;
			model = getUserDataFromResultSet(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage(), e);
		} finally {
			try {
				resultSet.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DBException(e.getMessage(), e);
			}
		}

		return model;
	}
}
