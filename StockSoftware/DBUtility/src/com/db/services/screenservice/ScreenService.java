package com.db.services.screenservice;

import java.sql.Connection;
import java.util.Map;

import com.db.Exception.DBException;
import com.db.models.screens.ScreenModel;
import com.db.utility.AppUtils;

public interface ScreenService {

	String GET_ALL_SCREEN_BY_MENU_ID = "SELECT * FROM " + AppUtils.SCREEN_TABLE_NAME + " WHERE "
			+ AppUtils.SCREEN_TABLE_MENU_ID + " = ? ";
	
	String UPDATE_SCREEN_TITLE = "UPDATE "+AppUtils.SCREEN_TABLE_NAME+" SET "+AppUtils.SCREEN_TABLE_TITLE+" = ? WHERE "+AppUtils.SCREEN_TABLE_KEY+" = ?";

	Map<String, ScreenModel> getAllScreensByMenuId(Connection connection, Integer menu_id) throws DBException;

	ScreenModel updateScreenModel(Connection connection, ScreenModel screenModel) throws DBException;
}
