package com.db.business.userprodrelationbusiness;

import java.sql.Connection;
import java.util.List;

import com.db.Exception.DBException;
import com.db.helper.DBHelper;
import com.db.models.authentication.AuthModel;
import com.db.models.product.ProductModel;
import com.db.models.userprodrelation.UserProdRelationModel;
import com.db.services.authenticationservice.AuthService;
import com.db.services.authenticationservice.AuthServiceImpl;
import com.db.services.product.ProductService;
import com.db.services.product.ProductServiceImpl;
import com.db.services.userprodrelationservice.UserProdRelationService;
import com.db.services.userprodrelationservice.UserProdRelationServiceImpl;
import com.db.utility.AppUtils;

public class UserProdRelationBusiness {

	private UserProdRelationService userProdRelationService = new UserProdRelationServiceImpl();
	private ProductService productService = new ProductServiceImpl();
	private AuthService authService = new AuthServiceImpl();

	public int inserLogs(UserProdRelationModel model) throws DBException {
		if (AppUtils.isNull(model)) {
			throw new IllegalArgumentException("userprodrel model cannot be null!!!");
		}
		if (AppUtils.isNull(model.getAuthModel())) {
			throw new IllegalArgumentException("user model cannot be null!!!");
		}
		if (AppUtils.isNullOrZeroInteger(model.getAuthModel().getId())) {
			throw new IllegalArgumentException("user id cannot be null or zero!!!");
		}
		if (AppUtils.isNull(model.getProductModel())) {
			throw new IllegalArgumentException("product model cannot be null!!!");
		}
		if (AppUtils.isNullOrZeroInteger(model.getProductModel().getId())) {
			throw new IllegalArgumentException("product id cannot be null or zero!!!");
		}
		int result = userProdRelationService.insertLogs(DBHelper.getInstance().getConnection(), model);
		DBHelper.getInstance().clear();
		return result;
	}

	public List<UserProdRelationModel> getAllProdLogs(int pagging) throws DBException {
		Connection connection = DBHelper.getInstance().getConnection();
		List<UserProdRelationModel> data = null;
		try {
			data = userProdRelationService.getAllProdLogs(connection, pagging);
			if (AppUtils.isNullOrEmptyList(data))
				return null;
			for (UserProdRelationModel model : data) {
				AuthModel auth = authService.selectById(connection, model.getAuthModel());
				if (AppUtils.isNull(auth)) {
					throw new IllegalArgumentException("user does not exists against logs!!!");
				}
				model.setAuthModel(auth);
				ProductModel prod = productService.getProductById(connection, model.getProductModel());
				if (AppUtils.isNull(prod) && (model.getStatus().equals(AppUtils.DELETE_STATUS)
						|| model.getStatus().equals(AppUtils.DELETE_ALL_STATUS))) {
					model.setProductModel(model.getProductModel());
				} else if (!AppUtils.isNull(prod))
					model.setProductModel(prod);
				else
					throw new IllegalArgumentException("product error placed while fetching logs");
			}
		} finally {
			DBHelper.getInstance().clear();
		}
		return data;
	}

	public int deleteAll() throws DBException {
		int result = userProdRelationService.deleteAll(DBHelper.getInstance().getConnection());
		DBHelper.getInstance().clear();
		return result;
	}
}
