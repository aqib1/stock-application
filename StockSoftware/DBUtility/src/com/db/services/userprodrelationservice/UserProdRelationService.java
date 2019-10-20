package com.db.services.userprodrelationservice;

import java.sql.Connection;
import java.util.List;

import com.db.Exception.DBException;
import com.db.models.userprodrelation.UserProdRelationModel;
import com.db.utility.AppUtils;

public interface UserProdRelationService {
	Integer PAGING_LIST_LIMIT = 50;
	String SELECT_ALL = "SELECT * FROM " + AppUtils.USER_PROD_RELATION_TABLE_NAME + " ORDER BY "
			+ AppUtils.USER_PROD_RELATION_TABLE_ID + " DESC ";
	String INSERT_LOGS = "INSERT INTO " + AppUtils.USER_PROD_RELATION_TABLE_NAME + " ( "
			+ AppUtils.USER_PROD_RELATION_LOGS_USER_ID + "," + AppUtils.USER_PROD_RELATION_TABLE_PROD_ID + ","
			+ AppUtils.USER_PROD_RELATION_LOGS_STATUS + "," + AppUtils.USER_PROD_RELATION_LOGS_LOG_TIME
			+ ") VALUES (?,?,?,?)";
	String DELETE_ALL = "DELETE FROM " + AppUtils.USER_PROD_RELATION_TABLE_NAME;

	String DELETE_ALL_FOR_D_STATUS = DELETE_ALL + " WHERE " + AppUtils.USER_PROD_RELATION_LOGS_STATUS + " != '"
			+ AppUtils.DELETE_ALL_STATUS + "' AND " + AppUtils.USER_PROD_RELATION_LOGS_STATUS + " != '"
			+ AppUtils.DELETE_STATUS + "'";
	String DELETE_BY_PRODUCT_ID = DELETE_ALL + " WHERE " + AppUtils.USER_PROD_RELATION_TABLE_PROD_ID + " =?";

	static String paggingQueryForSelect(int page) {
		int OFFSET = page <= 1 ? 0 : ((page - 1) * PAGING_LIST_LIMIT);
		return SELECT_ALL + " LIMIT " + PAGING_LIST_LIMIT + " OFFSET " + OFFSET;
	}

	List<UserProdRelationModel> getAllProdLogs(Connection connection, int pagging) throws DBException;

	int insertLogs(Connection connection, UserProdRelationModel model) throws DBException;

	int deleteAll(Connection connection) throws DBException;
}
