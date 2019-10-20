package com.db.services.authenticationservice;

import java.sql.Connection;
import java.util.List;

import com.db.Exception.DBException;
import com.db.models.authentication.AuthModel;
import com.db.utility.AppUtils;

public interface AuthService {

	String INSERT_LOGIN = "INSERT INTO " + AppUtils.LOGIN_TABLE_NAME + "(" + AppUtils.LOGIN_TABLE_USERNAME + ","
			+ AppUtils.LOGIN_TABLE_PASSWORD + "," + AppUtils.LOGIN_TABLE_EMAIL + "," + AppUtils.LOGIN_TABLE_ADDRESS
			+ "," + AppUtils.LOGIN_TABLE_CONTACTNUMBER + "," + AppUtils.LOGIN_TABLE_AGE + ","
			+ AppUtils.LOGIN_TABLE_GENDER + ") VALUES(?,?,?,?,?,?,?)";

	String SELECT_ALL = "SELECT * FROM " + AppUtils.LOGIN_TABLE_NAME + " ORDER BY "+AppUtils.LOGIN_TABLE_ID+" DESC";

	String SELECT_FIRST = "SELECT * FROM " + AppUtils.LOGIN_TABLE_NAME + " LIMIT 1";

	String DELETE_ALL = "DELETE FROM " + AppUtils.LOGIN_TABLE_NAME;

	String SELECT_ON_ID = "SELECT * FROM " + AppUtils.LOGIN_TABLE_NAME + " WHERE " + AppUtils.LOGIN_TABLE_ID + " = ?";

	String UPDATE_LOGIN_ON_ID = "UPDATE " + AppUtils.LOGIN_TABLE_NAME + " SET " + AppUtils.LOGIN_TABLE_PASSWORD + " = ?";
	
	String SELECT_ON_USERNAME_AND_PASSWORD = "SELECT * FROM " + AppUtils.LOGIN_TABLE_NAME +" WHERE "+ AppUtils.LOGIN_TABLE_USERNAME+" = ? AND "+AppUtils.LOGIN_TABLE_PASSWORD+" = ? ";

	AuthModel insert(Connection connection, AuthModel model);

	List<AuthModel> getAllLoginInfo(Connection connection) throws DBException;

	int deleteAllLoginInfo(Connection connection) throws DBException;

	AuthModel getFirstLoginInfo(Connection connection) throws DBException;

	AuthModel updateLoginInfo(Connection connection, AuthModel loginModel) throws DBException;

	AuthModel selectById(Connection connection, AuthModel loginModel) throws DBException;
	
	AuthModel selectByUserNameAndPassword(Connection connection, AuthModel loginModel) throws DBException;

	/*******Dynamic query creation for update by id*********/
	static String getDynamicUpdateOnIdQuery(AuthModel loginModel) {
		String dynamicQuery = UPDATE_LOGIN_ON_ID;
		if (!AppUtils.isNullOrEmptyString(loginModel.getUsername())) {
			dynamicQuery += ", " + AppUtils.LOGIN_TABLE_USERNAME + "= ?";
		}
		if (!AppUtils.isNullOrEmptyString(loginModel.getAddress())) {
			dynamicQuery += ", " + AppUtils.LOGIN_TABLE_ADDRESS + "= ?";
		}
		if (!AppUtils.isNullOrEmptyString(loginModel.getContactNumber())) {
			dynamicQuery += ", " + AppUtils.LOGIN_TABLE_CONTACTNUMBER + "= ?";
		}
		if (!AppUtils.isNullOrEmptyString(loginModel.getEmail())) {
			dynamicQuery += ", " + AppUtils.LOGIN_TABLE_EMAIL + "= ?";
		}
		if (!AppUtils.isNullOrEmptyString(loginModel.getGender())) {
			dynamicQuery += ", " + AppUtils.LOGIN_TABLE_GENDER + "= ?";
		}
		if (!AppUtils.isNullOrZeroInteger(loginModel.getAge())) {
			dynamicQuery += ", " + AppUtils.LOGIN_TABLE_AGE + "= ?";
		}

		dynamicQuery += " WHERE " + AppUtils.LOGIN_TABLE_ID + "= ?";
		return dynamicQuery;
	}
	
	
}
