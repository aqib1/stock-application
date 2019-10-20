package com.db.services.screencontentservice;

import java.sql.Connection;
import java.util.Map;

import com.db.Exception.DBException;
import com.db.models.screencontents.ScreenContentModel;
import com.db.utility.AppUtils;

public interface ScreenContentsService {
	String SELECT_ALL_BY_SCREEN_ID = "SELECT * FROM "+AppUtils.SCREEN_CONTENTS_TABLE+" WHERE "+AppUtils.SCREEN_CONTENTS_TABLE_SCREEN_ID+" = ?";
	
	String UPDATE_SCREEN_CONTENTS_TITLE_BY_KEY = "UPDATE "+AppUtils.SCREEN_CONTENTS_TABLE+" SET "+AppUtils.SCREEN_CONTENTS_TABLE_TITLE+" = ? WHERE "+AppUtils.SCREEN_CONTENTS_TABLE_KEY+" = ?";

	Map<String,ScreenContentModel> getAllByScreenId(Connection connection, Integer screen_id) throws DBException;

	ScreenContentModel updateScreenContentModel(Connection connection, ScreenContentModel screenContentModel) throws DBException;

}
