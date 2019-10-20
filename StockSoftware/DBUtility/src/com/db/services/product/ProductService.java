package com.db.services.product;

import java.sql.Connection;
import java.util.List;

import com.db.Exception.DBException;
import com.db.models.product.ProductModel;
import com.db.utility.AppUtils;

public interface ProductService {

	String SELECT_ALL = "SELECT * FROM " + AppUtils.PRODUCT_TABLE_NAME + " ORDER BY " + AppUtils.PRODUCT_TABLE_ID
			+ " ASC";
	String SELECT_BY_ID = "SELECT * FROM " + AppUtils.PRODUCT_TABLE_NAME + " WHERE " + AppUtils.PRODUCT_TABLE_ID
			+ " = ? ";
	String DELETE_ALL = "DELETE FROM " + AppUtils.PRODUCT_TABLE_NAME;

	String DELETE_BY_ID = DELETE_ALL + " WHERE " + AppUtils.PRODUCT_TABLE_ID + " = ?";

	String UPDATE_PRODUCT = "UPDATE " + AppUtils.PRODUCT_TABLE_NAME + " SET " + AppUtils.PRODUCT_TABLE_PROD_NAME
			+ " =? ";
	
	String SELECT_ALL_LIKE = "SELECT * FROM " + AppUtils.PRODUCT_TABLE_NAME + " WHERE " + AppUtils.PRODUCT_TABLE_PROD_NAME +" LIKE ? ";

	String INSERT_PRODUCT = "INSERT INTO " + AppUtils.PRODUCT_TABLE_NAME 
			+ " (" + AppUtils.PRODUCT_TABLE_PROD_NAME + ","
			+ AppUtils.PRODUCT_TABLE_PROD_QUANT + ", "
			+ AppUtils.PRODUCT_TABLE_BAR_CODE + ", "
			+ AppUtils.PRODUCT_TABLE_PRICE + ", "
			+ AppUtils.PRODUCT_TABLE_TAX + ", "
			+ AppUtils.PRODUCT_TABLE_COMP_NAME + ", "
			+ AppUtils.PRODUCT_TABLE_SUPL_NAME + ", "
			+ AppUtils.PRODUCT_TABLE_SUP_CONTACT + ", "
			+ AppUtils.PRODUCT_TABLE_SUP_ACCT + ", "
			+ AppUtils.PRODUCT_TABLE_PAYM_MTHD + ", "
			+ AppUtils.PRODUCT_TABLE_PAID_BLNC + ", "
			+ AppUtils.PRODUCT_TABLE_NET_BLNC + ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

	List<ProductModel> getALLProducts(Connection connection) throws DBException;

	int deleteProducts(Connection connection) throws DBException;

	int deleteProductById(Connection connection, ProductModel productModel) throws DBException;

	ProductModel updateProduct(Connection connection, ProductModel productModel) throws DBException;

	ProductModel getProductById(Connection connection, ProductModel productModel) throws DBException;
	
	ProductModel insertProduct(Connection connection, ProductModel productModel) throws DBException;

	List<ProductModel> getALLProductsLike(Connection connection, ProductModel productModel) throws DBException;
	
	static String getDynamicUpdateQuery(ProductModel productModel) {
		String dynamicQuery = UPDATE_PRODUCT;

		if (!AppUtils.isNullOrEmptyString(productModel.getBarCode()))
			dynamicQuery += ", " + AppUtils.PRODUCT_TABLE_BAR_CODE + " = ? ";
		if (!AppUtils.isNullOrZeroInteger(productModel.getProductQuantity()))
			dynamicQuery += ", " + AppUtils.PRODUCT_TABLE_PROD_QUANT + " =? ";
		if (!AppUtils.isNullOrZeroInteger(productModel.getPrice()))
			dynamicQuery += ", " + AppUtils.PRODUCT_TABLE_PRICE + " =? ";
		if (!AppUtils.isNullOrZeroInteger(productModel.getTax()))
			dynamicQuery += ", " + AppUtils.PRODUCT_TABLE_TAX + " =? ";
		if (!AppUtils.isNullOrEmptyString(productModel.getProductCompany()))
			dynamicQuery += ", " + AppUtils.PRODUCT_TABLE_COMP_NAME + " = ? ";
		if (!AppUtils.isNullOrEmptyString(productModel.getSupplierName()))
			dynamicQuery += ", " + AppUtils.PRODUCT_TABLE_SUPL_NAME + " = ? ";
		if (!AppUtils.isNullOrEmptyString(productModel.getSupplierContact()))
			dynamicQuery += ", " + AppUtils.PRODUCT_TABLE_SUP_CONTACT + " = ? ";
		if (!AppUtils.isNullOrEmptyString(productModel.getSupplierAccount()))
			dynamicQuery += ", " + AppUtils.PRODUCT_TABLE_SUP_ACCT + " = ? ";
		if (!AppUtils.isNullOrEmptyString(productModel.getPaymentMethod()))
			dynamicQuery += ", " + AppUtils.PRODUCT_TABLE_PAYM_MTHD + " = ? ";
		if (!AppUtils.isNullOrZeroInteger(productModel.getPaidBalance()))
			dynamicQuery += ", " + AppUtils.PRODUCT_TABLE_PAID_BLNC + " = ? ";
		if (!AppUtils.isNullOrZeroInteger(productModel.getNetBalance()))
			dynamicQuery += ", " + AppUtils.PRODUCT_TABLE_NET_BLNC + " = ? ";

		dynamicQuery += "where " + AppUtils.PRODUCT_TABLE_ID + " = ? ";
		return dynamicQuery;
	}

}
