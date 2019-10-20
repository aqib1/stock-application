package com.db.models.client;

public class ClientModel {
	private int id;
	private String name;
	private String contactNo;
	private String email;
	private Integer age;
	private String gender;
	private String accountNumber;
	private String date;
	private String country;
	private String district;
	private String state;
	private String address;
	private String referralName;
	private String referralAddr;

	public void setReferralAddr(String referralAddr) {
		this.referralAddr = referralAddr;
	}

	public void setReferralName(String referralName) {
		this.referralName = referralName;
	}

	public String getReferralName() {
		return referralName;
	}

	public String getReferralAddr() {
		return referralAddr;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String eimal) {
		this.email = eimal;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return name;
	}

}
