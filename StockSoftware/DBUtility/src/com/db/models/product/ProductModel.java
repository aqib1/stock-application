package com.db.models.product;

public class ProductModel {

	private int id;
	private String productName;
	private int productQuantity;
	private String barCode;
	private int price;
	private int tax;
	private String productCompany;
	private String supplierName;
	private String supplierContact;
	private String supplierAccount;
	private String paymentMethod;
	private int paidBalance;
	private int netBalance;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getTax() {
		return tax;
	}

	public void setTax(int tax) {
		this.tax = tax;
	}

	public String getProductCompany() {
		return productCompany;
	}

	public void setProductCompany(String productCompany) {
		this.productCompany = productCompany;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierContact() {
		return supplierContact;
	}

	public void setSupplierContact(String supplierContact) {
		this.supplierContact = supplierContact;
	}

	public String getSupplierAccount() {
		return supplierAccount;
	}

	public void setSupplierAccount(String supplierAccount) {
		this.supplierAccount = supplierAccount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public int getPaidBalance() {
		return paidBalance;
	}

	public void setPaidBalance(int paidBalance) {
		this.paidBalance = paidBalance;
	}

	public int getNetBalance() {
		return netBalance;
	}

	public void setNetBalance(int netBalance) {
		this.netBalance = netBalance;
	}

	@Override
	public String toString() {
		return productName;
	}
	
	
}
