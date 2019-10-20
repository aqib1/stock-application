package com.db.services.authenticationservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.db.Exception.DBException;
import com.db.models.authentication.AuthModel;
import com.db.utility.AppUtils;

public class AuthServiceImpl implements AuthService {

	private AuthModel loginModelFromResultSet(ResultSet resultSet) throws SQLException {
		AuthModel model = new AuthModel();
		model.setId(resultSet.getInt(AppUtils.LOGIN_TABLE_ID));
		model.setAddress(resultSet.getString(AppUtils.LOGIN_TABLE_ADDRESS));
		model.setUsername(resultSet.getString(AppUtils.LOGIN_TABLE_USERNAME));
		model.setPassword(resultSet.getString(AppUtils.LOGIN_TABLE_PASSWORD));
		model.setContactNumber(resultSet.getString(AppUtils.LOGIN_TABLE_CONTACTNUMBER));
		model.setAge(resultSet.getInt(AppUtils.LOGIN_TABLE_AGE));
		model.setEmail(resultSet.getString(AppUtils.LOGIN_TABLE_EMAIL));
		model.setGender(resultSet.getString(AppUtils.LOGIN_TABLE_GENDER));
		model.setCreation_date(resultSet.getDate(AppUtils.LOGIN_TABLE_CREATION_DATE).toString());
		return model;
	}

	@Override
	public List<AuthModel> getAllLoginInfo(Connection connection) throws DBException {
		List<AuthModel> loginModels = null;
		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {
			if (resultSet.next() == false) {
				return null;
			} else {
				loginModels = new ArrayList<>();
				do {
					loginModels.add(loginModelFromResultSet(resultSet));
				} while (resultSet.next());
			}
		} catch (SQLException e) {
			throw new DBException(e.getMessage(), e);
		}
		return loginModels;
	}

	@Override
	public int deleteAllLoginInfo(Connection connection) throws DBException {
		int result = -1;
		try (Statement statement = connection.createStatement()) {
			result = statement.executeUpdate(DELETE_ALL);
		} catch (SQLException e) {
			throw new DBException(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public AuthModel getFirstLoginInfo(Connection connection) throws DBException {
		AuthModel loginModel = null;
		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SELECT_FIRST)) {
			if (resultSet.next() == false) {
				return null;
			} else {
				do {
					loginModel = loginModelFromResultSet(resultSet);
				} while (resultSet.next());
			}
		} catch (SQLException e) {
			throw new DBException(e.getMessage(), e);
		}

		return loginModel;
	}

	@Override
	public AuthModel updateLoginInfo(Connection connection, AuthModel model) throws DBException {
		try (PreparedStatement statement = getPreparedStatementForUpdate(connection, model)) {
			if (statement.executeUpdate() == 0)
				return null;
		} catch (SQLException e) {
			throw new DBException(e.getMessage(), e);
		}
		return model;
	}

	private PreparedStatement getPreparedStatementForUpdate(Connection connection, AuthModel model)
			throws SQLException {
		int index = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(AuthService.getDynamicUpdateOnIdQuery(model));
		preparedStatement.setString(++index, model.getPassword());
		if (!AppUtils.isNullOrEmptyString(model.getUsername())) {
			preparedStatement.setString(++index, model.getUsername());
		}
		if (!AppUtils.isNullOrEmptyString(model.getAddress())) {
			preparedStatement.setString(++index, model.getAddress());
		}
		if (!AppUtils.isNullOrEmptyString(model.getContactNumber())) {
			preparedStatement.setString(++index, model.getContactNumber());
		}
		if (!AppUtils.isNullOrEmptyString(model.getEmail())) {
			preparedStatement.setString(++index, model.getEmail());
		}
		if (!AppUtils.isNullOrEmptyString(model.getGender())) {
			preparedStatement.setString(++index, model.getGender());
		}
		if (!AppUtils.isNullOrZeroInteger(model.getAge())) {
			preparedStatement.setInt(++index, model.getAge());
		}
		preparedStatement.setInt(++index, model.getId());
		return preparedStatement;
	}

	@Override
	public AuthModel selectById(Connection connection, AuthModel model) throws DBException {
		AuthModel loginModel = null;
		try (PreparedStatement statement = getPreparedStatementForSelectById(connection, model);
				ResultSet resultSet = statement.executeQuery()) {
			if (resultSet.next() == false) {
				return null;
			} else {
				do {
					loginModel = loginModelFromResultSet(resultSet);
				} while (resultSet.next());
			}
		} catch (SQLException e) {
			throw new DBException(e.getMessage(), e);
		}

		return loginModel;
	}

	private PreparedStatement getPreparedStatementForSelectById(Connection connection, AuthModel model)
			throws SQLException {
		int index = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ON_ID);
		preparedStatement.setInt(++index, model.getId());
		return preparedStatement;
	}

	@Override
	public AuthModel insert(Connection connection, AuthModel model) {
		try (PreparedStatement statement = getPreparedStatementForInsert(connection, model)) {
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return model;
	}

	private PreparedStatement getPreparedStatementForInsert(Connection connection, AuthModel model)
			throws SQLException {
		int index = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOGIN);
		preparedStatement.setString(++index, model.getUsername());
		preparedStatement.setString(++index, model.getPassword());
		preparedStatement.setString(++index, model.getEmail());
		preparedStatement.setString(++index, model.getAddress());
		preparedStatement.setString(++index, model.getContactNumber());
		preparedStatement.setInt(++index, model.getAge());
		preparedStatement.setString(++index, model.getGender());
		return preparedStatement;
	}

	@Override
	public AuthModel selectByUserNameAndPassword(Connection connection, AuthModel model) throws DBException {
		AuthModel loginModel = null;
		try (PreparedStatement statement = getPreparedStatmentForSelectByUserNameAndPassword(connection, model);
				ResultSet resultSet = statement.executeQuery()) {
			if (resultSet.next() == false) {
				return null;
			} else {
				loginModel = loginModelFromResultSet(resultSet);
			}
		} catch (SQLException e) {
			throw new DBException(e.getMessage(), e);
		}

		return loginModel;
	}

	private PreparedStatement getPreparedStatmentForSelectByUserNameAndPassword(Connection connection, AuthModel model)
			throws SQLException {
		int index = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ON_USERNAME_AND_PASSWORD);
		preparedStatement.setString(++index, model.getUsername());
		preparedStatement.setString(++index, model.getPassword());
		return preparedStatement;
	}

}
