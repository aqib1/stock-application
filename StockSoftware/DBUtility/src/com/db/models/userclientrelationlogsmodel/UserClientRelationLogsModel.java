package com.db.models.userclientrelationlogsmodel;

import com.db.models.authentication.AuthModel;
import com.db.models.client.ClientModel;

public class UserClientRelationLogsModel {

	private int id;
	private AuthModel authModel;
	private ClientModel clientModel;
	private String status;
	private String log_time;

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

	public ClientModel getClientModel() {
		return clientModel;
	}

	public void setClientModel(ClientModel clientModel) {
		this.clientModel = clientModel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLog_time() {
		return log_time;
	}

	public void setLog_time(String log_time) {
		this.log_time = log_time;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserClientRelationLogs [id=");
		builder.append(id);
		builder.append(", authModel=");
		builder.append(authModel);
		builder.append(", clientModel=");
		builder.append(clientModel);
		builder.append(", status=");
		builder.append(status);
		builder.append(", log_time=");
		builder.append(log_time);
		builder.append("]");
		return builder.toString();
	}

}
