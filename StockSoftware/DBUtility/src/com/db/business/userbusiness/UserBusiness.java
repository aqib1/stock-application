package com.db.business.userbusiness;

import java.util.List;

import com.db.Exception.DBException;
import com.db.helper.DBHelper;
import com.db.models.users.UserModel;
import com.db.services.users.UserService;
import com.db.services.users.UserServiceImpl;
import com.db.utility.AppUtils;

@Deprecated
public class UserBusiness {

	private UserService userService = new UserServiceImpl();

	public UserModel getUserByIdAndName(UserModel userModel) throws DBException {
		if (AppUtils.isNull(userModel)) {
			throw new IllegalArgumentException("user model cannot be null");
		}
		if (AppUtils.isNullOrEmptyString(userModel.getName())) {
			throw new IllegalArgumentException("user name cannot be null or empty");
		}
		if (AppUtils.isNullOrEmptyString(userModel.getPassword())) {
			throw new IllegalArgumentException("user password cannot be null or empty");
		}

		UserModel user = userService.getUserByNameAndPassword(DBHelper.getInstance().getConnection(), userModel);
		DBHelper.getInstance().clear();
		return user;
	}

	public UserModel getUserById(UserModel userModel) throws DBException {
		if (AppUtils.isNull(userModel)) {
			throw new IllegalArgumentException("user model cannot be null");
		}
		if (AppUtils.isNullOrZeroInteger(userModel.getId())) {
			throw new IllegalArgumentException("user id cannot be null or zero");
		}
		UserModel model = userService.getUserById(DBHelper.getInstance().getConnection(), userModel);
		DBHelper.getInstance().clear();
		return model;
	}

	public List<UserModel> getAllUsers() throws DBException {
		List<UserModel> users = userService.getAllUsers(DBHelper.getInstance().getConnection());
		DBHelper.getInstance().clear();
		return users;
	}

}
