package com.db.business.userclientrelationlogsbusiness;

import java.sql.Connection;
import java.util.List;

import com.db.Exception.DBException;
import com.db.helper.DBHelper;
import com.db.models.authentication.AuthModel;
import com.db.models.client.ClientModel;
import com.db.models.userclientrelationlogsmodel.UserClientRelationLogsModel;
import com.db.services.authenticationservice.AuthService;
import com.db.services.authenticationservice.AuthServiceImpl;
import com.db.services.client.ClientService;
import com.db.services.client.ClientServiceImpl;
import com.db.services.userclientrelationservice.UserClientRelationService;
import com.db.services.userclientrelationservice.UserClientRelationServiceImpl;
import com.db.utility.AppUtils;

public class UserClientRelationBusiness {

	private UserClientRelationService userClientRelationService = new UserClientRelationServiceImpl();
	private AuthService authService = new AuthServiceImpl();
	private ClientService clientService = new ClientServiceImpl();

	public List<UserClientRelationLogsModel> getAllLogs(int page) throws DBException {
		if (AppUtils.isNullOrZeroInteger(page))
			throw new IllegalArgumentException("page number cannot be null or zero!!");
		Connection connection = DBHelper.getInstance().getConnection();
		List<UserClientRelationLogsModel> logs = null;
		try {
			logs = userClientRelationService.getAllLogs(connection, page);
			if (AppUtils.isNullOrEmptyList(logs))
				return null;

			for (UserClientRelationLogsModel log : logs) {
				AuthModel auth = authService.selectById(connection, log.getAuthModel());
				if (AppUtils.isNull(auth)) {
					throw new IllegalArgumentException("user does not exists against logs");
				}
				log.setAuthModel(auth);
				ClientModel clientModel = clientService.selectById(connection, log.getClientModel());
				if (AppUtils.isNull(clientModel) && (log.getStatus().equals(AppUtils.DELETE_STATUS)
						|| log.getStatus().equals(AppUtils.DELETE_ALL_STATUS))) {
					log.setClientModel(new ClientModel());
				} else if (!AppUtils.isNull(clientModel))
					log.setClientModel(clientModel);
				else
					throw new IllegalArgumentException("client error placed while fetching logs");
			}
		} finally {
			DBHelper.getInstance().clear();
		}

		return logs;
	}

	public UserClientRelationLogsModel insertLogs(UserClientRelationLogsModel logs) throws DBException {
		if (AppUtils.isNull(logs))
			throw new IllegalArgumentException("user-client-relation logs cannot be null!!");
		if (AppUtils.isNullOrZeroInteger(logs.getAuthModel().getId()))
			throw new IllegalArgumentException("user id cannot be null or zero");
		if (AppUtils.isNullOrZeroInteger(logs.getClientModel().getId()))
			throw new IllegalArgumentException("client id cannot be null or zero");
		if (AppUtils.isNullOrEmptyString(logs.getStatus()))
			throw new IllegalArgumentException("status cannot be null or zero");
		UserClientRelationLogsModel model = userClientRelationService.insertLogs(DBHelper.getInstance().getConnection(),
				logs);
		DBHelper.getInstance().clear();
		return model;
	}

	public int deleteAll() throws DBException {
		int result = userClientRelationService.deleteAllLogs(DBHelper.getInstance().getConnection());
		DBHelper.getInstance().clear();
		return result;
	}

}
