package com.db.business.authentication;

import java.util.List;

import com.db.Exception.DBException;
import com.db.helper.DBHelper;
import com.db.models.authentication.AuthModel;
import com.db.services.authenticationservice.AuthService;
import com.db.services.authenticationservice.AuthServiceImpl;
import com.db.utility.AppUtils;

public class AuthBusiness {

	private AuthService authService = new AuthServiceImpl();

	public AuthModel insert(AuthModel model) {
		if (AppUtils.isNull(model)) {
			throw new IllegalArgumentException("AuthModel cannot be null");
		}
		if (AppUtils.isNullOrEmptyString(model.getUsername())) {
			throw new IllegalArgumentException("Username cannot be null");
		}
		if (AppUtils.isNullOrEmptyString(model.getPassword())) {
			throw new IllegalArgumentException("Password cannot be null");
		}
		if (AppUtils.isNullOrEmptyString(model.getC_password())) {
			throw new IllegalArgumentException("Confirm password cannot be null");
		}
		if (!model.getPassword().equals(model.getC_password())) {
			throw new IllegalArgumentException("Password & confirm password should be equal");
		}
		AuthModel m = authService.insert(DBHelper.getInstance().getConnection(), model);
		DBHelper.getInstance().clear();
		return m;
	}

	public List<AuthModel> getAllLoginInfo() throws DBException {
		List<AuthModel> listOfAuthModels = authService.getAllLoginInfo(DBHelper.getInstance().getConnection());
		DBHelper.getInstance().clear();
		if (AppUtils.isNullOrEmptyList(listOfAuthModels))
			throw new IllegalArgumentException("Login auths are empty in database");
		return listOfAuthModels;
	}

	public int deleteAllLoginInfo() throws DBException {
		int result = authService.deleteAllLoginInfo(DBHelper.getInstance().getConnection());
		DBHelper.getInstance().clear();
		return result;
	}

	public AuthModel getFirstLoginInfo() throws DBException {
		AuthModel authModel = authService.getFirstLoginInfo(DBHelper.getInstance().getConnection());
		DBHelper.getInstance().clear();
		return authModel;
	}

	public AuthModel updateLoginInfo(AuthModel loginModel) throws DBException {
		if (AppUtils.isNull(loginModel)) {
			throw new IllegalArgumentException("AuthModel cannot be null");
		}
		if (AppUtils.isNullOrEmptyString(loginModel.getPassword())) {
			throw new IllegalArgumentException("password cannot be null");
		}
		if (AppUtils.isNullOrZeroInteger(loginModel.getId())) {
			throw new IllegalArgumentException("Id cannot be null or zero");
		}

		loginModel = authService.updateLoginInfo(DBHelper.getInstance().getConnection(), loginModel);
		DBHelper.getInstance().clear();
		return loginModel;
	}

	public AuthModel selectById(AuthModel loginModel) throws DBException {
		if (AppUtils.isNull(loginModel)) {
			throw new IllegalArgumentException("AuthModel cannot be null");
		}
		if (AppUtils.isNullOrZeroInteger(loginModel.getId())) {
			throw new IllegalArgumentException("Id cannot be null or zero");
		}
		loginModel = authService.selectById(DBHelper.getInstance().getConnection(), loginModel);
		DBHelper.getInstance().clear();
		if(AppUtils.isNull(loginModel))
			throw new IllegalArgumentException("Login model recieved null from database!!");
		return loginModel;
	}

	public AuthModel selectByUserNameAndPassword(AuthModel model) throws DBException {
		if (AppUtils.isNull(model)) {
			throw new IllegalArgumentException("AuthModel cannot be null");
		}
		if (AppUtils.isNullOrEmptyString(model.getUsername())) {
			throw new IllegalArgumentException("Username cannot be null");
		}
		if (AppUtils.isNullOrEmptyString(model.getPassword())) {
			throw new IllegalArgumentException("Password cannot be null");
		}
		model = authService.selectByUserNameAndPassword(DBHelper.getInstance().getConnection(), model);
		DBHelper.getInstance().clear();
		return model;
	}
}
