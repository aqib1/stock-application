package com.db.business.salebusiness;

import java.sql.Connection;
import java.util.List;

import com.db.Exception.DBException;
import com.db.helper.DBHelper;
import com.db.models.SaleModel;
import com.db.models.product.ProductModel;
import com.db.services.authenticationservice.AuthService;
import com.db.services.authenticationservice.AuthServiceImpl;
import com.db.services.client.ClientService;
import com.db.services.client.ClientServiceImpl;
import com.db.services.product.ProductService;
import com.db.services.product.ProductServiceImpl;
import com.db.services.saleservice.SaleService;
import com.db.services.saleservice.SaleServiceImpl;
import com.db.utility.AppUtils;

public class SaleBusiness {
	private SaleService saleService = new SaleServiceImpl();
	private ProductService productService = new ProductServiceImpl();
	private AuthService authService = new AuthServiceImpl();
	private ClientService clientService = new ClientServiceImpl();

	public List<SaleModel> getAllSalePagginationForDays(int page, List<String> dates) throws DBException {
		if (AppUtils.isNullOrZeroInteger(page))
			throw new IllegalArgumentException("page cannot be null or zero");
		Connection connection = DBHelper.getInstance().getConnection();
		List<SaleModel> saleModels = null;
		try {
			saleModels = saleService.getAllSaleStatsInDate(connection, dates, page);
			if (AppUtils.isNullOrEmptyList(saleModels))
				return null;
			for (SaleModel saleModel : saleModels) {
				saleModel.setAuthModel(authService.selectById(connection, saleModel.getAuthModel()));
				saleModel.setClientModel(clientService.selectById(connection, saleModel.getClientModel()));
				saleModel.setProductModel(productService.getProductById(connection, saleModel.getProductModel()));
			}
		} finally {
			DBHelper.getInstance().clear();
		}
		return saleModels;
	}

	public List<SaleModel> getAllSalePaggination(int page) throws DBException {
		if (AppUtils.isNullOrZeroInteger(page))
			throw new IllegalArgumentException("page cannot be null or zero");
		Connection connection = DBHelper.getInstance().getConnection();
		List<SaleModel> saleModels = null;
		try {
			saleModels = saleService.getAllSaleStats(connection, page);
			if (AppUtils.isNullOrEmptyList(saleModels))
				return null;
			for (SaleModel saleModel : saleModels) {
				saleModel.setAuthModel(authService.selectById(connection, saleModel.getAuthModel()));
				saleModel.setClientModel(clientService.selectById(connection, saleModel.getClientModel()));
				saleModel.setProductModel(productService.getProductById(connection, saleModel.getProductModel()));
			}
		} finally {
			DBHelper.getInstance().clear();
		}
		return saleModels;
	}

	public SaleModel inserSale(SaleModel model) throws DBException {
		if (AppUtils.isNull(model))
			throw new IllegalArgumentException("sale model cannot be null");
		if (AppUtils.isNull(model.getClientModel()))
			throw new IllegalArgumentException("client model cannot be null");
		if (AppUtils.isNull(model.getProductModel()))
			throw new IllegalArgumentException("product model cannot be null");
		if (AppUtils.isNull(model.getAuthModel()))
			throw new IllegalArgumentException("auth model cannot be null");
		if (AppUtils.isNullOrZeroInteger(model.getAuthModel().getId()))
			throw new IllegalArgumentException("auth id cannot be null or zero");
		if (AppUtils.isNullOrZeroInteger(model.getClientModel().getId()))
			throw new IllegalArgumentException("client id cannot be null or zero");
		if (AppUtils.isNullOrZeroInteger(model.getProductModel().getId()))
			throw new IllegalArgumentException("product id cannot be null or zero");
		if (AppUtils.isNullOrEmptyString(model.getProductModel().getProductName()))
			throw new IllegalArgumentException("product name cannot be null or empty!!");
		if (AppUtils.isNullOrZeroInteger(model.getSalePrice()))
			throw new IllegalArgumentException("sale price cannot be null or zero");
		if (AppUtils.isNullOrZeroInteger(model.getSoldQuantity()))
			throw new IllegalArgumentException("sold quantity cannot be null or zero");
		if (AppUtils.isNullOrZeroInteger(model.getTotalBill()))
			throw new IllegalArgumentException("total bill cannot be null or zero");
		if (model.getProductModel().getProductQuantity() <= 0
				|| model.getSoldQuantity() > model.getProductModel().getProductQuantity())
			throw new IllegalArgumentException("product is out of stock!!!");
		SaleModel saleModel = null;
		try {
			Connection connection = DBHelper.getInstance().getConnection();
			model.setProfit(AppUtils.calculateProfit(model.getSalePrice(), model.getProductModel().getNetBalance()));
			saleModel = saleService.insertSale(connection, model);
			if (!AppUtils.isNull(saleModel)) {
				ProductModel productModel = model.getProductModel();
				productModel.setProductQuantity((productModel.getProductQuantity() - saleModel.getSoldQuantity()));
				productService.updateProduct(connection, productModel);
			}
		} finally {
			DBHelper.getInstance().clear();
		}
		return saleModel;
	}

	public int deleteAll() throws DBException {
		try {
			return saleService.deleteAll(DBHelper.getInstance().getConnection());
		} finally {
			DBHelper.getInstance().clear();
		}
	}

	public int deleteByProdId(int prodId) throws DBException {
		if (AppUtils.isNullOrZeroInteger(prodId))
			throw new IllegalArgumentException("product id cannot be null or zero");
		try {
			return saleService.deleteByProdId(DBHelper.getInstance().getConnection(), prodId);
		} finally {
			DBHelper.getInstance().clear();
		}
	}

	public int deleteByClientId(int clientId) throws DBException {
		if (AppUtils.isNullOrZeroInteger(clientId))
			throw new IllegalArgumentException("client id cannot be null or zero");
		try {
			return saleService.deleteByClientId(DBHelper.getInstance().getConnection(), clientId);
		} finally {
			DBHelper.getInstance().clear();
		}
	}
}
