package com.db.services.userclientrelationservice;

import java.sql.Connection;
import java.util.List;

import com.db.Exception.DBException;
import com.db.models.userclientrelationlogsmodel.UserClientRelationLogsModel;
import com.db.utility.AppUtils;

public interface UserClientRelationService {

	int PAGGING_LIMIT = 50;
	String SELECT_ALL = "SELECT * FROM " + AppUtils.USER_CLIENT_RELATION_LOGS_TABLE + " ORDER BY "
			+ AppUtils.USER_CLIENT_RELATION_LOGS_ID + " DESC ";
	String DELETE_ALL = "DELETE FROM " + AppUtils.USER_CLIENT_RELATION_LOGS_TABLE;
	String INSERT_LOGS = "INSERT INTO " + AppUtils.USER_CLIENT_RELATION_LOGS_TABLE + " ("
			+ AppUtils.USER_CLIENT_RELATION_LOGS_USER_ID + " , " + AppUtils.USER_CLIENT_RELATION_LOGS_CLIENT_ID + " , "
			+ AppUtils.USER_CLIENT_RELATION_LOGS_STATUS + " , " + AppUtils.USER_CLIENT_RELATION_LOGS_LOG_TIME
			+ ") VALUES (?,?,?,?)";
	String DELETE_ALL_FOR_DA_STATUS = DELETE_ALL + " WHERE " + AppUtils.USER_CLIENT_RELATION_LOGS_STATUS + "!= '"
			+ AppUtils.DELETE_ALL_STATUS + "' AND " + AppUtils.USER_CLIENT_RELATION_LOGS_STATUS + " != '"
			+ AppUtils.DELETE_STATUS + "'";
	String DELETE_ALL_BY_CLIENT_ID = DELETE_ALL + " WHERE " + AppUtils.USER_CLIENT_RELATION_LOGS_CLIENT_ID + "=?";

	static String paggingQueryForSelect(int page) {
		int OFFSET = page <= 1 ? 0 : ((page - 1) * PAGGING_LIMIT);
		return SELECT_ALL + " LIMIT " + PAGGING_LIMIT + " OFFSET " + OFFSET;
	}

	List<UserClientRelationLogsModel> getAllLogs(Connection connection, int page) throws DBException;

	int deleteAllLogs(Connection connection) throws DBException;

	UserClientRelationLogsModel insertLogs(Connection connection, UserClientRelationLogsModel logs) throws DBException;
}
