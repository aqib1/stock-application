package com.db.services.saleservice;

import java.sql.Connection;
import java.util.List;

import com.db.Exception.DBException;
import com.db.models.SaleModel;
import com.db.utility.AppUtils;

public interface SaleService {

	int PAGGING_LIMIT = 50;

	String INSERT_SALE = "INSERT INTO " + AppUtils.SALE_TABLE_NAME + " (" + AppUtils.SALE_TABLE_CLIENT_ID + ","
			+ AppUtils.SALE_TABLE_PRODUCT_ID + "," + AppUtils.SALE_TABLE_USER_ID + "," + AppUtils.SALE_TABLE_SUPP_NAME
			+ "," + AppUtils.SALE_TABLE_SOLD_PRICE + "," + AppUtils.SALE_TABLE_SOLD_QUANTITY + ","
			+ AppUtils.SALE_TABLE_DATE + "," + AppUtils.SALE_TABLE_PERCENT_PROFIT + "," + AppUtils.SALE_TABLE_TOTAL_BILL
			+ ") VALUES (?,?,?,?,?,?,?,?,?)";

	String SELECT_ALL = "SELECT * FROM " + AppUtils.SALE_TABLE_NAME + " ORDER BY " + AppUtils.SALE_TABLE_ID + " DESC ";

	String SELECT_ALL_IN = "SELECT * FROM " + AppUtils.SALE_TABLE_NAME + " WHERE " + AppUtils.SALE_TABLE_DATE + " IN ";

	String DELETE_ALL_BY_CLIENT_ID = "DELETE FROM " + AppUtils.SALE_TABLE_NAME + " WHERE "
			+ AppUtils.SALE_TABLE_CLIENT_ID + "=?";

	String DELETE_ALL_BY_PROD_ID = "DELETE FROM " + AppUtils.SALE_TABLE_NAME + " WHERE "
			+ AppUtils.SALE_TABLE_PRODUCT_ID + "=?";

	String DELETE_ALL = "DELETE FROM " + AppUtils.SALE_TABLE_NAME;

	static String selectAllInQuery(List<String> dates, int page) {
		String query = SELECT_ALL_IN;
		query += "(";
		for (int x = 0; x < dates.size(); x++) {
			if (x == dates.size() - 1) {
				query += "?)";
			} else {
				query += "?,";
			}
		}
		query += " ORDER BY " + AppUtils.SALE_TABLE_ID + " DESC";
		int OFFSET = page <= 1 ? 0 : ((page - 1) * PAGGING_LIMIT);
		query += " LIMIT " + PAGGING_LIMIT + " OFFSET " + OFFSET;
		return query;
	}

	static String paggingQueryForSelect(int page) {
		int OFFSET = page <= 1 ? 0 : ((page - 1) * PAGGING_LIMIT);
		return SELECT_ALL + " LIMIT " + PAGGING_LIMIT + " OFFSET " + OFFSET;
	}

	List<SaleModel> getAllSaleStatsInDate(Connection connection, List<String> dates, int page) throws DBException;

	SaleModel insertSale(Connection connection, SaleModel saleModel) throws DBException;

	List<SaleModel> getAllSaleStats(Connection connection, int page) throws DBException;

	int deleteAll(Connection connection) throws DBException;
	
	int deleteByClientId(Connection connection, int clientId) throws DBException;
	
	int deleteByProdId(Connection connection, int prodId) throws DBException;
}
