package com.db.models.userprodrelation;

import com.db.models.authentication.AuthModel;
import com.db.models.product.ProductModel;

public class UserProdRelationModel {

	private int id;
	private AuthModel authModel;
	private ProductModel productModel;
	private String status;
	private String timestamp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AuthModel getAuthModel() {
		return authModel;
	}

	public void setAuthModel(AuthModel authModel) {
		this.authModel = authModel;
	}

	public ProductModel getProductModel() {
		return productModel;
	}

	public void setProductModel(ProductModel productModel) {
		this.productModel = productModel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserProdRelationModel [id=");
		builder.append(id);
		builder.append(", authModel=");
		builder.append(authModel);
		builder.append(", productModel=");
		builder.append(productModel);
		builder.append(", status=");
		builder.append(status);
		builder.append(", timestamp=");
		builder.append(timestamp);
		builder.append("]");
		return builder.toString();
	}

}
