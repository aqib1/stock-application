package com.db.business.screens;

import java.util.Map;

import com.db.Exception.DBException;
import com.db.helper.DBHelper;
import com.db.models.screens.ScreenModel;
import com.db.services.screenservice.ScreenService;
import com.db.services.screenservice.ScreenServiceImpl;
import com.db.utility.AppUtils;

public class ScreensBusiness {

	private ScreenService screenService = new ScreenServiceImpl();

	public Map<String, ScreenModel> getScreensByMenuId(Integer menu_id) throws DBException {
		if (AppUtils.isNullOrZeroInteger(menu_id))
			throw new IllegalArgumentException("menu_id cannot be null or zero");
		Map<String, ScreenModel> screenModels = screenService
				.getAllScreensByMenuId(DBHelper.getInstance().getConnection(), menu_id);
		DBHelper.getInstance().clear();
		return screenModels;
	}

	public ScreenModel updateScreenModel(ScreenModel screenModel) throws DBException {
		if (AppUtils.isNull(screenModel)) {
			throw new IllegalArgumentException("screen model cannot be null");
		}
		if (AppUtils.isNullOrEmptyString(screenModel.getKey())) {
			throw new IllegalArgumentException("key cannot be null or empty");
		}
		if (AppUtils.isNullOrEmptyString(screenModel.getTitle())) {
			throw new IllegalArgumentException("title cannot be null or empty");
		}
		screenModel = screenService.updateScreenModel(DBHelper.getInstance().getConnection(), screenModel);
		DBHelper.getInstance().clear();
		return screenModel;
	}
}
