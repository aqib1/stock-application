package com.db.services.userclientrelationservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.db.Exception.DBException;
import com.db.models.authentication.AuthModel;
import com.db.models.client.ClientModel;
import com.db.models.userclientrelationlogsmodel.UserClientRelationLogsModel;
import com.db.utility.AppUtils;

public class UserClientRelationServiceImpl implements UserClientRelationService {

	@Override
	public List<UserClientRelationLogsModel> getAllLogs(Connection connection, int page) throws DBException {
		List<UserClientRelationLogsModel> logs = null;
		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(UserClientRelationService.paggingQueryForSelect(page))) {
			if (resultSet.next() == false)
				return null;
			logs = new ArrayList<>();
			do {
				logs.add(getLogsFromResultSet(resultSet));
			} while (resultSet.next());
			return logs;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage(), e);
		}
	}

	private UserClientRelationLogsModel getLogsFromResultSet(ResultSet resultSet) throws SQLException {
		UserClientRelationLogsModel userClientRelationLogsModel = new UserClientRelationLogsModel();
		userClientRelationLogsModel.setId(resultSet.getInt(AppUtils.USER_CLIENT_RELATION_LOGS_ID));
		AuthModel authModel = new AuthModel();
		authModel.setId(resultSet.getInt(AppUtils.USER_CLIENT_RELATION_LOGS_USER_ID));
		userClientRelationLogsModel.setAuthModel(authModel);
		ClientModel clientModel = new ClientModel();
		clientModel.setId(resultSet.getInt(AppUtils.USER_CLIENT_RELATION_LOGS_CLIENT_ID));
		userClientRelationLogsModel.setClientModel(clientModel);
		userClientRelationLogsModel.setStatus(resultSet.getString(AppUtils.USER_CLIENT_RELATION_LOGS_STATUS));
		userClientRelationLogsModel.setLog_time(resultSet.getString(AppUtils.USER_CLIENT_RELATION_LOGS_LOG_TIME));
		return userClientRelationLogsModel;
	}

	@Override
	public int deleteAllLogs(Connection connection) throws DBException {
		try (Statement statement = connection.createStatement()) {
			return statement.executeUpdate(DELETE_ALL);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage(), e);
		}
	}

	@Override
	public UserClientRelationLogsModel insertLogs(Connection connection, UserClientRelationLogsModel logs)
			throws DBException {
		if (logs.getStatus().equals(AppUtils.DELETE_ALL_STATUS)) {
			if (deleteAllForStatusDA(connection) == 0) {
				throw new DBException("DA-Logs deletion failed!!, please contact developer");
			}
		}

		if (logs.getStatus().equals(AppUtils.DELETE_STATUS)) {
			if (deleteByClientId(connection, logs) == 0)
				throw new DBException("D-Logs deletion failed!!, please contact developer");
		}

		return insert(connection, logs);
	}

	private UserClientRelationLogsModel insert(Connection connection, UserClientRelationLogsModel logs)
			throws DBException {
		try (PreparedStatement preparedStatement = getPreparedStatmentForInsert(connection, logs)) {
			if (preparedStatement.executeUpdate() == 0)
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage(), e);
		}
		return logs;
	}

	private int deleteAllForStatusDA(Connection connection) throws DBException {
		try (Statement statement = connection.createStatement()) {
			return statement.executeUpdate(DELETE_ALL_FOR_DA_STATUS);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage(), e);
		}
	}

	private int deleteByClientId(Connection connection, UserClientRelationLogsModel log) throws DBException {
		int index = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_BY_CLIENT_ID);
			preparedStatement.setInt(++index, log.getClientModel().getId());
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage(), e);
		}
	}

	private PreparedStatement getPreparedStatmentForInsert(Connection connection, UserClientRelationLogsModel logs)
			throws SQLException {
		int index = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOGS);
		preparedStatement.setInt(++index, logs.getAuthModel().getId());
		preparedStatement.setInt(++index, logs.getClientModel().getId());
		preparedStatement.setString(++index, logs.getStatus());
		preparedStatement.setString(++index, LocalDateTime.now().toString());
		return preparedStatement;
	}

}
