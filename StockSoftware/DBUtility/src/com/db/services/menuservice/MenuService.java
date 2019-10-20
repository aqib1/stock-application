package com.db.services.menuservice;

import java.sql.Connection;
import java.util.Map;

import com.db.Exception.DBException;
import com.db.models.menus.MenuModel;
import com.db.utility.AppUtils;

public interface MenuService {
	
	String SELECT_ALL = "SELECT * FROM "+AppUtils.MENUS_TABLE;
	String UPDATE_MENU = "UPDATE "+AppUtils.MENUS_TABLE +" SET "+AppUtils.MENUS_COLUMN_TITLE+"= ? WHERE "+AppUtils.MENUS_COLUMN_KEY +" = ?";

	Map<String,MenuModel> getAllMenus(Connection connection) throws DBException;
	
	int updateMenu(Connection connection, MenuModel model) throws DBException;
}
