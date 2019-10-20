package com.stock.controller.contenteditor;

import com.db.models.app.contents.FooterModel;
import com.db.models.app.contents.HeaderModel;

public interface IContentObserver {

	void observeForFotter(FooterModel footerModel);

	void observeForHeader(HeaderModel headerModel);
}
