package com.db.business.screencontentbusiness;

import java.util.Map;

import com.db.Exception.DBException;
import com.db.helper.DBHelper;
import com.db.models.screencontents.ScreenContentModel;
import com.db.services.screencontentservice.ScreenContentServiceImpl;
import com.db.services.screencontentservice.ScreenContentsService;
import com.db.utility.AppUtils;

public class ScreenContentBusiness {
	private ScreenContentsService screenContentService = new ScreenContentServiceImpl();

	public Map<String, ScreenContentModel> getScreenContentsByMenuId(Integer screen_id) throws DBException {
		if (AppUtils.isNullOrZeroInteger(screen_id))
			throw new IllegalArgumentException("menu_id cannot be null or zero");
		Map<String, ScreenContentModel> screenModels = screenContentService
				.getAllByScreenId(DBHelper.getInstance().getConnection(), screen_id);
		DBHelper.getInstance().clear();
		return screenModels;
	}

	public ScreenContentModel updateScreenContentModel(ScreenContentModel screenContentModel) throws DBException {
		if (AppUtils.isNull(screenContentModel)) {
			throw new IllegalArgumentException("screen content model cannot be null");
		}
		if (AppUtils.isNullOrEmptyString(screenContentModel.getKey())) {
			throw new IllegalArgumentException("key cannot be null or empty");
		}
		if (AppUtils.isNullOrEmptyString(screenContentModel.getTitle())) {
			throw new IllegalArgumentException("title cannot be null or empty");
		}
		screenContentModel = screenContentService.updateScreenContentModel(DBHelper.getInstance().getConnection(),
				screenContentModel);
		DBHelper.getInstance().clear();
		return screenContentModel;
	}
}
