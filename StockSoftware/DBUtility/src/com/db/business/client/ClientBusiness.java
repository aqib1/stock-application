package com.db.business.client;

import java.util.List;

import com.db.Exception.DBException;
import com.db.helper.DBHelper;
import com.db.models.client.ClientModel;
import com.db.services.client.ClientService;
import com.db.services.client.ClientServiceImpl;
import com.db.utility.AppUtils;

public class ClientBusiness {
	private ClientService clientService = new ClientServiceImpl();

	public ClientModel getClientById(Integer id) throws DBException {
		if (AppUtils.isNullOrZeroInteger(id)) {
			throw new IllegalArgumentException("id cannot be null or zero");
		}
		ClientModel clientModel = clientService.getClientById(DBHelper.getInstance().getConnection(), id);
		DBHelper.getInstance().clear();
		return clientModel;
	}

	public List<ClientModel> getAllClients() throws DBException {
		List<ClientModel> clientModels = clientService.getAllClients(DBHelper.getInstance().getConnection());
		DBHelper.getInstance().clear();
		return clientModels;
	}

	public ClientModel updateClient(ClientModel model) throws DBException {
		if (AppUtils.isNull(model))
			throw new IllegalArgumentException("model object cannot be null");
		if (AppUtils.isNullOrEmptyString(model.getName()))
			throw new IllegalArgumentException("user name cannot be null or empty");
		if (AppUtils.isNullOrZeroInteger(model.getId()))
			throw new IllegalArgumentException("user id cannot be null or zero");

		ClientModel clientModel = clientService.updateClient(DBHelper.getInstance().getConnection(), model);
		DBHelper.getInstance().clear();
		return clientModel;
	}

	public ClientModel inserClient(ClientModel model) throws DBException {
		if (AppUtils.isNull(model))
			throw new IllegalArgumentException("model object cannot be null");
		if (AppUtils.isNullOrEmptyString(model.getName()))
			throw new IllegalArgumentException("user name cannot be null or empty");
		if (AppUtils.isNullOrEmptyString(model.getGender()))
			throw new IllegalArgumentException("gender cannot be null or empty");
		if (AppUtils.isNullOrEmptyString(model.getAccountNumber()))
			throw new IllegalArgumentException("account number cannot be null or empty");
		ClientModel insertId = clientService.inserClient(DBHelper.getInstance().getConnection(), model);
		DBHelper.getInstance().clear();
		return insertId;
	}

	public ClientModel selectById(ClientModel clientModel) throws DBException {
		if (AppUtils.isNull(clientModel))
			throw new IllegalArgumentException("model object cannot be null");
		if (AppUtils.isNullOrZeroInteger(clientModel.getId()))
			throw new IllegalArgumentException("client id cannot be zero or null");
		ClientModel model = clientService.selectById(DBHelper.getInstance().getConnection(), clientModel);
		DBHelper.getInstance().clear();
		return model;
	}

	public ClientModel deleteById(ClientModel model) throws DBException {
		if (AppUtils.isNull(model))
			throw new IllegalArgumentException("model object cannot be null");
		if (AppUtils.isNullOrZeroInteger(model.getId()))
			throw new IllegalArgumentException("client id cannot be zero or null");
		ClientModel clientModel = clientService.deleteById(DBHelper.getInstance().getConnection(), model);
		DBHelper.getInstance().clear();
		return clientModel;
	}

	public List<ClientModel> getAllClientsLike(ClientModel clientModel) throws DBException {
		if (AppUtils.isNull(clientModel))
			throw new IllegalArgumentException("model object cannot be null");
		List<ClientModel> data = clientService.getAllClientsLike(DBHelper.getInstance().getConnection(), clientModel);
		DBHelper.getInstance().clear();
		return data;
	}

	public int deleteAll() throws DBException {
		int r = clientService.deleteAll(DBHelper.getInstance().getConnection());
		DBHelper.getInstance().clear();
		return r;
	}
}
