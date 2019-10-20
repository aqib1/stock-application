package com.db.models;

import com.db.models.authentication.AuthModel;
import com.db.models.client.ClientModel;
import com.db.models.product.ProductModel;

public class SaleModel {

	private Integer id;
	private ClientModel clientModel;
	private ProductModel productModel;
	private AuthModel authModel;
	private String sallerName;
	private Integer salePrice;
	private Integer soldQuantity;
	private Double profit;
	private String dateOfSale;
	private Integer totalBill;

	public Integer getTotalBill() {
		return totalBill;
	}

	public void setTotalBill(Integer totalBill) {
		this.totalBill = totalBill;
	}

	public AuthModel getAuthModel() {
		return authModel;
	}

	public void setAuthModel(AuthModel authModel) {
		this.authModel = authModel;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ClientModel getClientModel() {
		return clientModel;
	}

	public void setClientModel(ClientModel clientModel) {
		this.clientModel = clientModel;
	}

	public ProductModel getProductModel() {
		return productModel;
	}

	public void setProductModel(ProductModel productModel) {
		this.productModel = productModel;
	}

	public String getSallerName() {
		return sallerName;
	}

	public void setSallerName(String sallerName) {
		this.sallerName = sallerName;
	}

	public Integer getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Integer salePrice) {
		this.salePrice = salePrice;
	}

	public Integer getSoldQuantity() {
		return soldQuantity;
	}

	public void setSoldQuantity(Integer soldQuantity) {
		this.soldQuantity = soldQuantity;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public String getDateOfSale() {
		return dateOfSale;
	}

	public void setDateOfSale(String dateOfSale) {
		this.dateOfSale = dateOfSale;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SaleModel [id=");
		builder.append(id);
		builder.append(", clientModel=");
		builder.append(clientModel);
		builder.append(", productModel=");
		builder.append(productModel);
		builder.append(", authModel=");
		builder.append(authModel);
		builder.append(", sallerName=");
		builder.append(sallerName);
		builder.append(", salePrice=");
		builder.append(salePrice);
		builder.append(", soldQuantity=");
		builder.append(soldQuantity);
		builder.append(", profit=");
		builder.append(profit);
		builder.append(", dateOfSale=");
		builder.append(dateOfSale);
		builder.append("]");
		return builder.toString();
	}

}
