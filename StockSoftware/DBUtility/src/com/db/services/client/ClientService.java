package com.db.services.client;

import java.sql.Connection;
import java.util.List;

import com.db.Exception.DBException;
import com.db.models.client.ClientModel;
import com.db.utility.AppUtils;

public interface ClientService {

	String SELECT_ALL = "SELECT * FROM " + AppUtils.CLIENT_TABLE_NAME + " ORDER BY " + AppUtils.CLIENT_TABLE_ID
			+ " ASC";
	String SELECT_ALL_BY_CLIENT_ID = "SELECT * FROM " + AppUtils.CLIENT_TABLE_USERNAME + " WHERE "
			+ AppUtils.CLIENT_TABLE_ID + " = ? ORDER BY " + AppUtils.CLIENT_TABLE_ID + " ASC";
	String UPDATE_CLIENT_DATA = "UPDATE " + AppUtils.CLIENT_TABLE_NAME + " SET " + AppUtils.CLIENT_TABLE_USERNAME
			+ " = ? ";
	String INSERT_CLIENT = "INSERT INTO " + AppUtils.CLIENT_TABLE_NAME + " " + "(" + AppUtils.CLIENT_TABLE_USERNAME
			+ ", " + AppUtils.CLIENT_TABLE_CONTACT_NUMBER + ", " + AppUtils.CLIENT_TABLE_EMAIL + ", "
			+ AppUtils.CLIENT_TABLE_AGE + ", " + AppUtils.CLIENT_TABLE_GENDER + ", "
			+ AppUtils.CLIENT_TABLE_ACCOUNT_NUMBER + ", " + AppUtils.CLIENT_TABLE_DATE + ", "
			+ AppUtils.CLIENT_TABLE_COUNTRY + ", " + AppUtils.CLIENT_TABLE_DISTRICT + ", " + AppUtils.CLIENT_TABLE_STATE
			+ ", " + AppUtils.CLIENT_TABLE_ADDRESS + ", " + AppUtils.CLIENT_TABLE_REF_NAME + ", "
			+ AppUtils.CLIENT_TABLE_REF_ADDR + " ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";

	String SELECT_BY_ID = "SELECT * FROM " + AppUtils.CLIENT_TABLE_NAME + " WHERE " + AppUtils.CLIENT_TABLE_ID
			+ " =? ORDER BY " + AppUtils.CLIENT_TABLE_ID + " ASC";
	String DELETE_BY_ID = "DELETE FROM "+AppUtils.CLIENT_TABLE_NAME +" WHERE "+AppUtils.CLIENT_TABLE_ID + " =? ";

	String DELETE_ALL = "DELETE FROM "+AppUtils.CLIENT_TABLE_NAME;
	
	String SELECT_ALL_LIKE = "SELECT * FROM " + AppUtils.CLIENT_TABLE_NAME + " WHERE " + AppUtils.CLIENT_TABLE_USERNAME +" LIKE ? ";
	
	ClientModel getClientById(Connection connection, Integer id) throws DBException;

	List<ClientModel> getAllClients(Connection connection) throws DBException;
	
	List<ClientModel> getAllClientsLike(Connection connection, ClientModel clientModel) throws DBException;

	ClientModel updateClient(Connection connection, ClientModel model) throws DBException;

	ClientModel inserClient(Connection connection, ClientModel clientModel) throws DBException;

	ClientModel selectById(Connection connection, ClientModel model) throws DBException;
	
	ClientModel deleteById(Connection connection, ClientModel model) throws DBException;

	int deleteAll(Connection connection) throws DBException;
	
	static String getUpdateClientDynamicQuery(ClientModel clientModel) {
		String dynamicQuery = UPDATE_CLIENT_DATA;
		if (!AppUtils.isNullOrEmptyString(clientModel.getAddress()))
			dynamicQuery += " , " + AppUtils.CLIENT_TABLE_ADDRESS + " = ? ";
		if (!AppUtils.isNullOrEmptyString(clientModel.getContactNo()))
			dynamicQuery += " , " + AppUtils.CLIENT_TABLE_CONTACT_NUMBER + " = ? ";
		if (!AppUtils.isNullOrEmptyString(clientModel.getAccountNumber()))
			dynamicQuery += " , " + AppUtils.CLIENT_TABLE_ACCOUNT_NUMBER + " = ? ";
		if (!AppUtils.isNullOrEmptyString(clientModel.getDate()))
			dynamicQuery += " , " + AppUtils.CLIENT_TABLE_DATE + " = ? ";
		if (!AppUtils.isNullOrEmptyString(clientModel.getEmail()))
			dynamicQuery += " , " + AppUtils.CLIENT_TABLE_EMAIL + " = ? ";
		if (!AppUtils.isNullOrEmptyString(clientModel.getDistrict()))
			dynamicQuery += " , " + AppUtils.CLIENT_TABLE_DISTRICT + " = ? ";
		if (!AppUtils.isNullOrEmptyString(clientModel.getState()))
			dynamicQuery += " , " + AppUtils.CLIENT_TABLE_STATE + " = ? ";
		if (!AppUtils.isNullOrZeroInteger(clientModel.getAge()))
			dynamicQuery += " , " + AppUtils.CLIENT_TABLE_AGE + " = ? ";
		if (!AppUtils.isNullOrEmptyString(clientModel.getCountry()))
			dynamicQuery += " , " + AppUtils.CLIENT_TABLE_COUNTRY + " = ? ";
		if (!AppUtils.isNullOrEmptyString(clientModel.getReferralName()))
			dynamicQuery += " , " + AppUtils.CLIENT_TABLE_REF_NAME + " = ? ";
		if (!AppUtils.isNullOrEmptyString(clientModel.getReferralAddr()))
			dynamicQuery += " , " + AppUtils.CLIENT_TABLE_REF_ADDR + " = ? ";
		if (!AppUtils.isNullOrEmptyString(clientModel.getGender()))
			dynamicQuery += " , " + AppUtils.CLIENT_TABLE_GENDER + " = ? ";

		dynamicQuery += " WHERE " + AppUtils.CLIENT_TABLE_ID + " = ?";

		return dynamicQuery;
	}

}
