package com.db.business.footerbusiness;

import com.db.Exception.DBException;
import com.db.helper.DBHelper;
import com.db.models.app.contents.FooterModel;
import com.db.services.footerservice.FooterService;
import com.db.services.footerservice.FooterServiceImpl;
import com.db.utility.AppUtils;

public class FooterBusiness {
	
	private FooterService footerService = new FooterServiceImpl();

	public FooterModel update(FooterModel model) throws DBException {
		if(AppUtils.isNull(model))
			throw new IllegalArgumentException("null model object not allowed");
		if(AppUtils.isNullOrEmptyString(model.getContent()))
			throw new IllegalArgumentException("content footer empty or null not allowed");
		if(AppUtils.isNullOrZeroInteger(model.getId()))
			throw new IllegalArgumentException("id of null or zero is not allowed");
		
		model = footerService.update(DBHelper.getInstance().getConnection(), model);
		DBHelper.getInstance().clear();
		if(AppUtils.isNull(model))
			throw new IllegalArgumentException("footer model recieved null from database!!");
		return model;
	}
	
	public FooterModel getFooter() throws DBException {
		FooterModel model = footerService.getFooter(DBHelper.getInstance().getConnection());
		DBHelper.getInstance().clear();
		if(AppUtils.isNull(model))
			throw new IllegalArgumentException("footer model recieved null from database!!");
		return model;
	}
}
