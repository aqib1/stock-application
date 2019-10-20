package com.db.services.users;

import java.sql.Connection;
import java.util.List;

import com.db.Exception.DBException;
import com.db.models.users.UserModel;
import com.db.utility.AppUtils;

@Deprecated
public interface UserService {

	String SELECT_ALL = "SELECT * FROM " + AppUtils.USER_TABLE_NAME;
	String SELECT_BY_USERNAME_PASSWORD = "SELECT * FROM " + AppUtils.USER_TABLE_NAME + " WHERE "
			+ AppUtils.USER_TABLE_USERNAME + " = ? AND " + AppUtils.USER_TABLE_PASSWORD + " = ? ";
	String SELECT_BY_USERID = "SELECT * FROM " + AppUtils.USER_TABLE_NAME + " WHERE " + AppUtils.USER_TABLE_ID + " = ?";

	UserModel getUserByNameAndPassword(Connection connection, UserModel userModel) throws DBException;

	UserModel getUserById(Connection connection, UserModel userModel) throws DBException;

	List<UserModel> getAllUsers(Connection connection) throws DBException;
}
