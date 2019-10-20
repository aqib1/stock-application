package com.db.services.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.db.Exception.DBException;
import com.db.models.product.ProductModel;
import com.db.utility.AppUtils;

public class ProductServiceImpl implements ProductService {

	public List<ProductModel> getALLProductsLike(Connection connection, ProductModel productModel) throws DBException {
		List<ProductModel> productModels = null;
		try (PreparedStatement statement = getPreparedStatmentForLikeStat(connection, productModel);
				ResultSet resultSet = statement.executeQuery()) {
			if (resultSet.next() == false) {
				return null;
			} else {
				productModels = new ArrayList<>();
				do {
					ProductModel model = getProductModelFromResultSet(resultSet);
					productModels.add(model);
				} while (resultSet.next());
			}
		} catch (SQLException e) {
			throw new DBException(e.getMessage(), e);
		}
		return productModels;
	}

	private PreparedStatement getPreparedStatmentForLikeStat(Connection connection, ProductModel productModel)
			throws SQLException {
		int index = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LIKE);
		preparedStatement.setString(++index, "%" + productModel.getProductName() + "%");
		return preparedStatement;
	}

	@Override
	public List<ProductModel> getALLProducts(Connection connection) throws DBException {
		List<ProductModel> productModels = null;
		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {
			if (resultSet.next() == false) {
				return null;
			} else {
				productModels = new ArrayList<>();
				do {
					ProductModel model = getProductModelFromResultSet(resultSet);
					productModels.add(model);
				} while (resultSet.next());
			}
		} catch (SQLException e) {
			throw new DBException(e.getMessage(), e);
		}
		return productModels;
	}

	private ProductModel getProductModelFromResultSet(ResultSet resultSet) throws SQLException {
		ProductModel productModel = new ProductModel();
		productModel.setId(resultSet.getInt(AppUtils.PRODUCT_TABLE_ID));
		productModel.setProductName(resultSet.getString(AppUtils.PRODUCT_TABLE_PROD_NAME));
		productModel.setProductQuantity(resultSet.getInt(AppUtils.PRODUCT_TABLE_PROD_QUANT));
		productModel.setBarCode(resultSet.getString(AppUtils.PRODUCT_TABLE_BAR_CODE));
		productModel.setPrice(resultSet.getInt(AppUtils.PRODUCT_TABLE_PRICE));
		productModel.setTax(resultSet.getInt(AppUtils.PRODUCT_TABLE_TAX));
		productModel.setProductCompany(resultSet.getString(AppUtils.PRODUCT_TABLE_COMP_NAME));
		productModel.setSupplierName(resultSet.getString(AppUtils.PRODUCT_TABLE_SUPL_NAME));
		productModel.setSupplierContact(resultSet.getString(AppUtils.PRODUCT_TABLE_SUP_CONTACT));
		productModel.setSupplierAccount(resultSet.getString(AppUtils.PRODUCT_TABLE_SUP_ACCT));
		productModel.setPaymentMethod(resultSet.getString(AppUtils.PRODUCT_TABLE_PAYM_MTHD));
		productModel.setPaidBalance(resultSet.getInt(AppUtils.PRODUCT_TABLE_PAID_BLNC));
		productModel.setNetBalance(resultSet.getInt(AppUtils.PRODUCT_TABLE_NET_BLNC));
		return productModel;
	}

	@Override
	public int deleteProducts(Connection connection) throws DBException {
		int result = -1;
		try (Statement statement = connection.createStatement()) {
			result = statement.executeUpdate(DELETE_ALL);
		} catch (SQLException e) {
			throw new DBException(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public ProductModel updateProduct(Connection connection, ProductModel productModel) throws DBException {
		try (PreparedStatement preparedStatement = getDynamicPreparedStatement(connection, productModel)) {
			if (preparedStatement.executeUpdate() == 0)
				return null;
		} catch (SQLException e) {
			throw new DBException(e.getMessage(), e);
		}

		return productModel;
	}

	private PreparedStatement getDynamicPreparedStatement(Connection connection, ProductModel productModel)
			throws SQLException {
		int index = 0;
		PreparedStatement preparedStatement = null;
		preparedStatement = connection.prepareStatement(ProductService.getDynamicUpdateQuery(productModel));
		preparedStatement.setString(++index, productModel.getProductName());
		if (!AppUtils.isNullOrEmptyString(productModel.getBarCode()))
			preparedStatement.setString(++index, productModel.getBarCode());
		if (!AppUtils.isNullOrZeroInteger(productModel.getProductQuantity()))
			preparedStatement.setInt(++index, productModel.getProductQuantity());
		if (!AppUtils.isNullOrZeroInteger(productModel.getPrice()))
			preparedStatement.setInt(++index, productModel.getPrice());
		if (!AppUtils.isNullOrZeroInteger(productModel.getTax()))
			preparedStatement.setInt(++index, productModel.getTax());
		if (!AppUtils.isNullOrEmptyString(productModel.getProductCompany()))
			preparedStatement.setString(++index, productModel.getProductCompany());
		if (!AppUtils.isNullOrEmptyString(productModel.getSupplierName()))
			preparedStatement.setString(++index, productModel.getSupplierName());
		if (!AppUtils.isNullOrEmptyString(productModel.getSupplierContact()))
			preparedStatement.setString(++index, productModel.getSupplierContact());
		if (!AppUtils.isNullOrEmptyString(productModel.getSupplierAccount()))
			preparedStatement.setString(++index, productModel.getSupplierAccount());
		if (!AppUtils.isNullOrEmptyString(productModel.getPaymentMethod()))
			preparedStatement.setString(++index, productModel.getPaymentMethod());
		if (!AppUtils.isNullOrZeroInteger(productModel.getPaidBalance()))
			preparedStatement.setInt(++index, productModel.getPaidBalance());
		if (!AppUtils.isNullOrZeroInteger(productModel.getNetBalance()))
			preparedStatement.setInt(++index, productModel.getNetBalance());
		preparedStatement.setInt(++index, productModel.getId());
		return preparedStatement;
	}

	@Override
	public ProductModel getProductById(Connection connection, ProductModel productModel) throws DBException {
		int index = 0;
		PreparedStatement preparedStatement = null;
		ProductModel result = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(SELECT_BY_ID);
			preparedStatement.setInt(++index, productModel.getId());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next() == false) {
				return null;
			}
			result = getProductModelFromResultSet(resultSet);
		} catch (SQLException e) {
			throw new DBException(e.getMessage(), e);
		} finally {
			try {
				if (!AppUtils.isNull(resultSet))
					resultSet.close();
				if (!AppUtils.isNull(preparedStatement))
					preparedStatement.close();
			} catch (SQLException e) {
				throw new DBException(e.getMessage(), e);
			}
		}
		return result;
	}

	@Override
	public int deleteProductById(Connection connection, ProductModel productModel) throws DBException {
		int result = -1;
		int index = 0;
		try (PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
			statement.setInt(++index, productModel.getId());
			result = statement.executeUpdate();
		} catch (SQLException e) {
			throw new DBException(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public ProductModel insertProduct(Connection connection, ProductModel productModel) throws DBException {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = getDynamicPreparedStatementForInsert(connection, productModel);
			if (preparedStatement.executeUpdate() == 0)
				return null;
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet != null && resultSet.next())
				productModel.setId(resultSet.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage(), e);
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return productModel;
	}

	private PreparedStatement getDynamicPreparedStatementForInsert(Connection connection, ProductModel productModel)
			throws SQLException {
		int index = 0;
		PreparedStatement preparedStatement = null;
		preparedStatement = connection.prepareStatement(INSERT_PRODUCT);
		preparedStatement.setString(++index, productModel.getProductName());
		preparedStatement.setInt(++index, productModel.getProductQuantity());
		preparedStatement.setString(++index, productModel.getBarCode());
		preparedStatement.setInt(++index, productModel.getPrice());
		preparedStatement.setInt(++index, productModel.getTax());
		preparedStatement.setString(++index, productModel.getProductCompany());
		preparedStatement.setString(++index, productModel.getSupplierName());
		preparedStatement.setString(++index, productModel.getSupplierContact());
		preparedStatement.setString(++index, productModel.getSupplierAccount());
		preparedStatement.setString(++index, productModel.getPaymentMethod());
		preparedStatement.setInt(++index, productModel.getPaidBalance());
		preparedStatement.setInt(++index, productModel.getNetBalance());
		return preparedStatement;
	}

}
