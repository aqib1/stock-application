package com.db.services.saleservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.db.Exception.DBException;
import com.db.models.SaleModel;
import com.db.models.authentication.AuthModel;
import com.db.models.client.ClientModel;
import com.db.models.product.ProductModel;
import com.db.utility.AppUtils;

public class SaleServiceImpl implements SaleService {

	private PreparedStatement getPreparedStatementForInsert(Connection connection, SaleModel saleModel)
			throws SQLException {
		int index = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SALE);
		preparedStatement.setInt(++index, saleModel.getClientModel().getId());
		preparedStatement.setInt(++index, saleModel.getProductModel().getId());
		preparedStatement.setInt(++index, saleModel.getAuthModel().getId());
		preparedStatement.setString(++index, saleModel.getSallerName());
		preparedStatement.setInt(++index, saleModel.getSalePrice());
		preparedStatement.setInt(++index, saleModel.getSoldQuantity());
		preparedStatement.setString(++index, LocalDate.now().toString());
		preparedStatement.setDouble(++index, saleModel.getProfit());
		preparedStatement.setInt(++index, saleModel.getTotalBill());
		return preparedStatement;
	}

	@Override
	public SaleModel insertSale(Connection connection, SaleModel saleModel) throws DBException {
		try (PreparedStatement preparedStatement = getPreparedStatementForInsert(connection, saleModel)) {
			if (preparedStatement.executeUpdate() == 0)
				return null;
			ResultSet resultSet = null;
			try {
				resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet != null && resultSet.next())
					saleModel.setId(resultSet.getInt(1));
			} finally {
				if (!AppUtils.isNull(resultSet)) {
					resultSet.close();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage(), e);
		}

		return saleModel;
	}

	@Override
	public List<SaleModel> getAllSaleStats(Connection connection, int page) throws DBException {
		List<SaleModel> logs = null;
		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SaleService.paggingQueryForSelect(page))) {
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

	private SaleModel getLogsFromResultSet(ResultSet resultSet) throws SQLException {
		SaleModel saleModel = new SaleModel();
		saleModel.setId(resultSet.getInt(AppUtils.SALE_TABLE_ID));
		ClientModel clientModel = new ClientModel();
		clientModel.setId(resultSet.getInt(AppUtils.SALE_TABLE_CLIENT_ID));
		saleModel.setClientModel(clientModel);
		ProductModel productModel = new ProductModel();
		productModel.setId(resultSet.getInt(AppUtils.SALE_TABLE_PRODUCT_ID));
		saleModel.setProductModel(productModel);
		saleModel.setClientModel(clientModel);
		AuthModel authModel = new AuthModel();
		authModel.setId(resultSet.getInt(AppUtils.SALE_TABLE_USER_ID));
		saleModel.setAuthModel(authModel);
		saleModel.setSallerName(resultSet.getString(AppUtils.SALE_TABLE_SUPP_NAME));
		saleModel.setSalePrice(resultSet.getInt(AppUtils.SALE_TABLE_SOLD_PRICE));
		saleModel.setSoldQuantity(resultSet.getInt(AppUtils.SALE_TABLE_SOLD_QUANTITY));
		saleModel.setProfit(resultSet.getDouble(AppUtils.SALE_TABLE_PERCENT_PROFIT));
		saleModel.setDateOfSale(resultSet.getString(AppUtils.SALE_TABLE_DATE));
		saleModel.setTotalBill(resultSet.getInt(AppUtils.SALE_TABLE_TOTAL_BILL));
		return saleModel;
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

	private PreparedStatement getPreparedStatmentForSelectByDays(Connection connection, List<String> dates, int page)
			throws SQLException {
		int index = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(SaleService.selectAllInQuery(dates, page));
		for (String date : dates) {
			preparedStatement.setString(++index, date);
		}
		return preparedStatement;
	}

	@Override
	public List<SaleModel> getAllSaleStatsInDate(Connection connection, List<String> dates, int page)
			throws DBException {
		List<SaleModel> logs = null;
		try (PreparedStatement preparedStatement = getPreparedStatmentForSelectByDays(connection, dates, page);
				ResultSet resultSet = preparedStatement.executeQuery()) {
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

	private PreparedStatement getPreparedStatmentForDeleteByClientId(Connection connection, int clientId)
			throws SQLException {
		int index = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_BY_CLIENT_ID);
		preparedStatement.setInt(++index, clientId);
		return preparedStatement;
	}

	@Override
	public int deleteByClientId(Connection connection, int clientId) throws DBException {
		try (PreparedStatement preparedStatement = getPreparedStatmentForDeleteByClientId(connection, clientId)) {
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage(), e);
		}
	}

	private PreparedStatement getPreparedStatmentForDeleteByProdId(Connection connection, int prodId)
			throws SQLException {
		int index = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_BY_PROD_ID);
		preparedStatement.setInt(++index, prodId);
		return preparedStatement;
	}

	@Override
	public int deleteByProdId(Connection connection, int prodId) throws DBException {
		try (PreparedStatement preparedStatement = getPreparedStatmentForDeleteByProdId(connection, prodId)) {
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage(), e);
		}
	}

}
