package com.db.services.sysparamservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.db.Exception.DBException;
import com.db.models.sysparams.SysParamModel;
import com.db.utility.AppUtils;

public class SysParamServiceImpl implements SysParamService {

	@Override
	public SysParamModel getSysParamModel(Connection connection, String param) throws DBException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		SysParamModel model = null;
		int index = 0;
		try {
			statement = connection.prepareStatement(SELECT_SYS_PARAM);
			statement.setString(++index, param);
			resultSet = statement.executeQuery();
			if (resultSet.next() == false) {
				return null;
			} else {
				model = new SysParamModel();
				model.setId(resultSet.getInt(AppUtils.SYSPARAMS_TABLE_ID));
				model.setParam(resultSet.getString(AppUtils.SYSPARAMS_TABLE_PARAM));
				model.setIsAllow(resultSet.getString(AppUtils.SYSPARAMA_TABLE_ISACTIVE));
			}
		} catch (SQLException e) {
			throw new DBException(e.getMessage(), e);
		} finally {
			try {
				statement.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return model;
	}

}
