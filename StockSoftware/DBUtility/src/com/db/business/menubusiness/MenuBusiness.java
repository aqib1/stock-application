package com.db.business.menubusiness;

import java.sql.Connection;
import java.util.Map;

import com.db.Exception.DBException;
import com.db.helper.DBHelper;
import com.db.models.menus.MenuModel;
import com.db.services.menuservice.MenuService;
import com.db.services.menuservice.MenuServiceImpl;
import com.db.utility.AppUtils;

public class MenuBusiness {

	private MenuService menuService = new MenuServiceImpl();

	public Map<String, MenuModel> getAllMenus() throws DBException {
		Map<String, MenuModel> data = null;
		Connection c = DBHelper.getInstance().getConnection();
		data = menuService.getAllMenus(c);
		DBHelper.getInstance().clear();
		if(AppUtils.isNullOrEmptyMap(data))
			throw new IllegalArgumentException("menus recieved null from database!!");
		return data;
	}

	public int updateMenu(MenuModel model) throws DBException {

		if (AppUtils.isNull(model)) {
			throw new IllegalArgumentException("menu model cannot be null!!");
		}
		if (AppUtils.isNullOrEmptyString(model.getKey())) {
			throw new IllegalArgumentException("key cannot be null or empty!!");
		}
		if (AppUtils.isNullOrEmptyString(model.getTitle())) {
			throw new IllegalArgumentException("title cannot be null or empty!!");
		}
		int i;
		Connection c = DBHelper.getInstance().getConnection();
		i = menuService.updateMenu(c, model);
		DBHelper.getInstance().clear();
		return i;
	}

}
