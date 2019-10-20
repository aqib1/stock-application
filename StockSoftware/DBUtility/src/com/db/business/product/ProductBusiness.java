package com.db.business.product;

import java.util.List;

import com.db.Exception.DBException;
import com.db.helper.DBHelper;
import com.db.models.product.ProductModel;
import com.db.services.product.ProductService;
import com.db.services.product.ProductServiceImpl;
import com.db.utility.AppUtils;

public class ProductBusiness {
	private ProductService productService = new ProductServiceImpl();

	public List<ProductModel> getALLProducts() throws DBException {
		List<ProductModel> data = productService.getALLProducts(DBHelper.getInstance().getConnection());
		DBHelper.getInstance().clear();
		return data;
	}

	public int deleteProducts() throws DBException {
		int result = productService.deleteProducts(DBHelper.getInstance().getConnection());
		DBHelper.getInstance().clear();
		return result;
	}

	public int deleteProductById(ProductModel productModel) throws DBException {
		if(AppUtils.isNull(productModel))
			throw new IllegalArgumentException("product model cannot be null!!");
		if(AppUtils.isNullOrZeroInteger(productModel.getId()))
			throw new IllegalArgumentException("product id cannot be null or zero!!");
		int result = productService.deleteProductById(DBHelper.getInstance().getConnection(), productModel);
		DBHelper.getInstance().clear();
		return result;
	}

	public ProductModel updateProduct(ProductModel productModel) throws DBException {
		if(AppUtils.isNull(productModel))
			throw new IllegalArgumentException("product model cannot be null!!");
		if(AppUtils.isNullOrEmptyString(productModel.getProductName()))
			throw new IllegalArgumentException("product name cannot be null or empty!!");
		if(AppUtils.isNullOrZeroInteger(productModel.getId()))
			throw new IllegalArgumentException("product id cannot be null or zero!!");
		
		productService.updateProduct(DBHelper.getInstance().getConnection(), productModel);
		DBHelper.getInstance().clear();
		return productModel;
	}

	public ProductModel getProductById(ProductModel productModel) throws DBException {
		ProductModel model = productService.getProductById(DBHelper.getInstance().getConnection(), productModel);
		DBHelper.getInstance().clear();
		return model;
	}

	public ProductModel insertProduct(ProductModel productModel) throws DBException {
		if(AppUtils.isNull(productModel))
			throw new IllegalArgumentException("product model cannot be null!!");
		if(AppUtils.isNullOrEmptyString(productModel.getProductName()))
			throw new IllegalArgumentException("product name cannot be null or empty!!");
		if(AppUtils.isNullOrEmptyString(productModel.getPaymentMethod()))
			throw new IllegalArgumentException("Payment method cannot be null or empty!!");
		if(AppUtils.isNullOrZeroInteger(productModel.getPrice()))
			throw new IllegalArgumentException("price cannot be null or zero!!");
		
		ProductModel model = productService.insertProduct(DBHelper.getInstance().getConnection(), productModel);
		DBHelper.getInstance().clear();
		return model;
	}
	
	public List<ProductModel> getALLProductsLike(ProductModel productModel) throws DBException {
		if(AppUtils.isNull(productModel))
			throw new IllegalArgumentException("product model cannot be null!!");
		List<ProductModel> data = productService.getALLProductsLike(DBHelper.getInstance().getConnection(), productModel);
		DBHelper.getInstance().clear();
		return data;
	}
}
