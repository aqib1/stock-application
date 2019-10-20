package com.db.services.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.db.Exception.DBException;
import com.db.models.client.ClientModel;
import com.db.utility.AppUtils;

public class ClientServiceImpl implements ClientService {

	private ClientModel getClientModelByResultSet(ResultSet resultSet) throws SQLException {
		ClientModel clientModel = new ClientModel();
		clientModel.setId(resultSet.getInt(AppUtils.CLIENT_TABLE_ID));
		clientModel.setName(resultSet.getString(AppUtils.CLIENT_TABLE_USERNAME));
		clientModel.setAge(resultSet.getInt(AppUtils.CLIENT_TABLE_AGE));
		clientModel.setGender(resultSet.getString(AppUtils.CLIENT_TABLE_GENDER));
		clientModel.setAccountNumber(resultSet.getString(AppUtils.CLIENT_TABLE_ACCOUNT_NUMBER));
		clientModel.setContactNo(resultSet.getString(AppUtils.CLIENT_TABLE_CONTACT_NUMBER));
		clientModel.setDate(resultSet.getString(AppUtils.CLIENT_TABLE_DATE));
		clientModel.setCountry(resultSet.getString(AppUtils.CLIENT_TABLE_COUNTRY));
		clientModel.setDistrict(resultSet.getString(AppUtils.CLIENT_TABLE_DISTRICT));
		clientModel.setState(resultSet.getString(AppUtils.CLIENT_TABLE_STATE));
		clientModel.setAddress(resultSet.getString(AppUtils.CLIENT_TABLE_ADDRESS));
		clientModel.setEmail(resultSet.getString(AppUtils.CLIENT_TABLE_EMAIL));
		clientModel.setReferralName(resultSet.getString(AppUtils.CLIENT_TABLE_REF_NAME));
		clientModel.setReferralAddr(resultSet.getString(AppUtils.CLIENT_TABLE_REF_ADDR));
		return clientModel;
	}

	@Override
	public ClientModel getClientById(Connection connection, Integer id) throws DBException {
		int index = 0;
		PreparedStatement preparedStatement = null;
		ClientModel clientModel = null;
		try {
			preparedStatement = connection.prepareStatement(SELECT_ALL_BY_CLIENT_ID);
			preparedStatement.setInt(++index, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next() == false) {
				return null;
			} else {
				clientModel = getClientModelByResultSet(resultSet);
			}
		} catch (SQLException e) {
			throw new DBException(e.getMessage(), e);
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				throw new DBException(e.getMessage(), e);
			}
		}
		return clientModel;
	}

	@Override
	public List<ClientModel> getAllClients(Connection connection) throws DBException {
		List<ClientModel> listClientModels = null;
		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {
			if (resultSet.next() == false) {
				return null;
			} else {
				listClientModels = new ArrayList<>();
				do {
					ClientModel clientModel = getClientModelByResultSet(resultSet);
					listClientModels.add(clientModel);
				} while (resultSet.next());
			}

		} catch (SQLException e) {
			throw new DBException(e.getMessage(), e);
		}
		return listClientModels;
	}

	@Override
	public ClientModel updateClient(Connection connection, ClientModel model) throws DBException {
		try (PreparedStatement preparedStatement = getDynamicPreparedStatement(connection, model)) {
			if (preparedStatement.executeUpdate() == 0) {
				return null;
			}
		} catch (SQLException e) {
			throw new DBException(e.getMessage(), e);
		}
		return model;
	}

	private PreparedStatement getDynamicPreparedStatement(Connection connection, ClientModel clientModel)
			throws SQLException {
		PreparedStatement preparedStatement = connection
				.prepareStatement(ClientService.getUpdateClientDynamicQuery(clientModel));
		int index = 0;
		preparedStatement.setString(++index, clientModel.getName());
		if (!AppUtils.isNullOrEmptyString(clientModel.getAddress()))
			preparedStatement.setString(++index, clientModel.getAddress());
		if (!AppUtils.isNullOrEmptyString(clientModel.getContactNo()))
			preparedStatement.setString(++index, clientModel.getContactNo());
		if (!AppUtils.isNullOrEmptyString(clientModel.getAccountNumber()))
			preparedStatement.setString(++index, clientModel.getAccountNumber());
		if (!AppUtils.isNullOrEmptyString(clientModel.getDate()))
			preparedStatement.setString(++index, clientModel.getDate());
		if (!AppUtils.isNullOrEmptyString(clientModel.getEmail()))
			preparedStatement.setString(++index, clientModel.getEmail());
		if (!AppUtils.isNullOrEmptyString(clientModel.getDistrict()))
			preparedStatement.setString(++index, clientModel.getDistrict());
		if (!AppUtils.isNullOrEmptyString(clientModel.getState()))
			preparedStatement.setString(++index, clientModel.getState());
		if (!AppUtils.isNullOrZeroInteger(clientModel.getAge()))
			preparedStatement.setInt(++index, clientModel.getAge());
		if (!AppUtils.isNullOrEmptyString(clientModel.getCountry()))
			preparedStatement.setString(++index, clientModel.getCountry());
		if (!AppUtils.isNullOrEmptyString(clientModel.getReferralName()))
			preparedStatement.setString(++index, clientModel.getReferralName());
		if (!AppUtils.isNullOrEmptyString(clientModel.getReferralAddr()))
			preparedStatement.setString(++index, clientModel.getReferralAddr());
		if (!AppUtils.isNullOrEmptyString(clientModel.getGender()))
			preparedStatement.setString(++index, clientModel.getGender());
		preparedStatement.setInt(++index, clientModel.getId());
		return preparedStatement;
	}

	public ClientModel inserClient(Connection connection, ClientModel clientModel) throws DBException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = getDynamicPreparedStatementForInsert(connection, clientModel);
			if (preparedStatement.executeUpdate() == 0)
				return null;
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet != null && resultSet.next())
				clientModel.setId(resultSet.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage(), e);
		} finally {
			try {
				if (!AppUtils.isNull(resultSet))
					resultSet.close();
				if (!AppUtils.isNull(preparedStatement))
					preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DBException(e.getMessage(), e);
			}
		}
		return clientModel;
	}

	private PreparedStatement getDynamicPreparedStatementForInsert(Connection connection, ClientModel clientModel)
			throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT);
		int index = 0;
		preparedStatement.setString(++index, clientModel.getName());
		preparedStatement.setString(++index, clientModel.getContactNo());
		preparedStatement.setString(++index, clientModel.getEmail());
		preparedStatement.setInt(++index, clientModel.getAge());
		preparedStatement.setString(++index, clientModel.getGender());
		preparedStatement.setString(++index, clientModel.getAccountNumber());
		preparedStatement.setString(++index,
				AppUtils.isNullOrEmptyString(clientModel.getDate()) ? LocalDateTime.now().toString()
						: clientModel.getDate());
		preparedStatement.setString(++index, clientModel.getCountry());
		preparedStatement.setString(++index, clientModel.getDistrict());
		preparedStatement.setString(++index, clientModel.getState());
		preparedStatement.setString(++index, clientModel.getAddress());
		preparedStatement.setString(++index, clientModel.getReferralName());
		preparedStatement.setString(++index, clientModel.getReferralAddr());
		return preparedStatement;
	}

	@Override
	public ClientModel selectById(Connection connection, ClientModel model) throws DBException {
		int index = 0;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(SELECT_BY_ID);
			preparedStatement.setInt(++index, model.getId());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next() == false)
				return null;
			return getClientModelByResultSet(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException(e.getMessage(), e);
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DBException(e.getMessage(), e);
			}

		}

	}

	private PreparedStatement getPreparedStatmentForDelete(Connection connection, ClientModel clientModel)
			throws SQLException {
		int index = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
		preparedStatement.setInt(++index, clientModel.getId());
		return preparedStatement;
	}

	@Override
	public ClientModel deleteById(Connection connection, ClientModel model) throws DBException {
		try (PreparedStatement preparedStatement = getPreparedStatmentForDelete(connection, model)) {
			if (preparedStatement.executeUpdate() == 0)
				return null;
			return model;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage(), e);
		}

	}

	@Override
	public int deleteAll(Connection connection) throws DBException {
		try (Statement statement = connection.createStatement()) {
			return statement.executeUpdate(DELETE_ALL);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage(), e);
		}
	}

	private PreparedStatement getPreparedStatementForLikeSelect(Connection connection, ClientModel clientModel)
			throws SQLException {
		int index = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LIKE);
		preparedStatement.setString(++index, "%" + clientModel.getName() + "%");
		return preparedStatement;
	}

	@Override
	public List<ClientModel> getAllClientsLike(Connection connection, ClientModel clientModel) throws DBException {
		List<ClientModel> listClientModels = null;
		try (PreparedStatement statement = getPreparedStatementForLikeSelect(connection, clientModel);
				ResultSet resultSet = statement.executeQuery()) {
			if (resultSet.next() == false) {
				return null;
			} else {
				listClientModels = new ArrayList<>();
				do {
					ClientModel c = getClientModelByResultSet(resultSet);
					listClientModels.add(c);
				} while (resultSet.next());
			}

		} catch (SQLException e) {
			throw new DBException(e.getMessage(), e);
		}
		return listClientModels;

	}
}
