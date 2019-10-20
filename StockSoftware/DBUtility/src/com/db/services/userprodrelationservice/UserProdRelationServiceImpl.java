package com.db.services.userprodrelationservice;

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
import com.db.models.product.ProductModel;
import com.db.models.userprodrelation.UserProdRelationModel;
import com.db.utility.AppUtils;

public class UserProdRelationServiceImpl implements UserProdRelationService {

	@Override
	public List<UserProdRelationModel> getAllProdLogs(Connection connection, int pagging) throws DBException {
		Statement statement = null;
		ResultSet resultSet = null;
		List<UserProdRelationModel> data = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(UserProdRelationService.paggingQueryForSelect(pagging));
			if (resultSet.next() == false) {
				return null;
			} else {
				data = new ArrayList<>();
				do {
					UserProdRelationModel model = getModelFromResultSet(resultSet);
					data.add(model);
				} while (resultSet.next());
			}
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

		return data;
	}

	private UserProdRelationModel getModelFromResultSet(ResultSet resultSet) throws SQLException {
		UserProdRelationModel userProdRelationModel = new UserProdRelationModel();
		AuthModel userModel = new AuthModel();
		ProductModel productModel = new ProductModel();
		userProdRelationModel.setId(resultSet.getInt(AppUtils.USER_PROD_RELATION_TABLE_ID));
		userModel.setId(resultSet.getInt(AppUtils.USER_PROD_RELATION_LOGS_USER_ID));
		userProdRelationModel.setAuthModel(userModel);
		productModel.setId(resultSet.getInt(AppUtils.USER_PROD_RELATION_TABLE_PROD_ID));
		userProdRelationModel.setProductModel(productModel);
		userProdRelationModel.setStatus(resultSet.getString(AppUtils.USER_PROD_RELATION_LOGS_STATUS));
		userProdRelationModel.setTimestamp(resultSet.getString(AppUtils.USER_PROD_RELATION_LOGS_LOG_TIME));
		return userProdRelationModel;
	}

	@Override
	public int insertLogs(Connection connection, UserProdRelationModel model) throws DBException {
		if (model.getStatus().equals(AppUtils.DELETE_ALL_STATUS)) {
			if (deleteAllForStatusDA(connection) == 0) {
				throw new DBException("DA-Logs deletion failed!!, please contact developer");
			}
		}
		if (model.getStatus().equals(AppUtils.DELETE_STATUS)) {
			if (deleteByProdId(connection, model) == 0)
				throw new DBException("D-Logs deletion failed!!, please contact developer");
		}
		try (PreparedStatement preparedStatement = getPreparedStatementByUserProdRelModel(connection, model)) {
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage(), e);
		}
	}

	private int deleteByProdId(Connection connection, UserProdRelationModel model) throws DBException {
		int index = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_PRODUCT_ID);
			preparedStatement.setInt(++index, model.getProductModel().getId());
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage(), e);
		}
	}

	private int deleteAllForStatusDA(Connection connection) throws DBException {
		try (Statement statement = connection.createStatement()) {
			return statement.executeUpdate(DELETE_ALL_FOR_D_STATUS);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage(), e);
		}
	}

	private PreparedStatement getPreparedStatementByUserProdRelModel(Connection connection, UserProdRelationModel model)
			throws SQLException {
		int index = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOGS);
		preparedStatement.setInt(++index, model.getAuthModel().getId());
		preparedStatement.setInt(++index, model.getProductModel().getId());
		preparedStatement.setString(++index, model.getStatus());
		preparedStatement.setString(++index, LocalDateTime.now().toString());
		return preparedStatement;
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
}
