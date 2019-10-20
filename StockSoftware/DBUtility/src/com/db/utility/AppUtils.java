package com.db.utility;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AppUtils {
	private AppUtils() {

	}

	public static final String[] PROD_STAT_TABLE_HEADERS = { "#", "Product Name", "Product quantity", "Bar code",
			"Price", "Tax", "Company name", "Supplier name", "Supplier contact", "Supplier account", "Payment method",
			"Paid balance", "Net balance", "Changed by", "Added date", "Contact number", "Email", "Address", "Status" };

	public static final String[] CLIENT_STAT_TABLE_HEADERS = { "#", "Client Name", "Client contact", "Client email",
			"Client age", "Client gender", "Client account", "Added date", "Client country", "Client address",
			"Client reference", "Changed by", "User number", "Status" };

	public static final String[] SALE_STAT_TABLE_HEADERS = { "#", "Product name", "Product quantity", "Product price",
			"Product tax", "Barcode", "Saller name", "Sold price", "Sold quantity", "Client name", "Client account",
			"Reference name", "Sold by", "Stat date", "Profit", "Total bill" };

	public static final String[] SALE_WEEKLY_PROFIT_STATS = { "#", "Product name", "Product quantity", "Client name",
			"Client account", "Sold by", "Profit", "Sold date" };

	public static final String WINDOWS_OS = "window";
	public static final String MAC_OS = "mac";
	public static final String WINDOWS_OS_DB_PATH = "C://db/stockdb.db";
	public static final String MAC_OS_DB_PATH = "/db/stockdb.db";
	public static final String SQLITE_DRIVER_URL = "jdbc:sqlite:";

	public static final String YES = "Y";
	public static final String NO = "N";
	public static final String MALE = "M";
	public static final String FEMALE = "F";

	/********************* menu table - columns *************************/
	public static final String MENUS_TABLE = "Menus";
	public static final String MENUS_COLUMN_ID = "menu_id";
	public static final String MENUS_COLUMN_TITLE = "menu_title";
	public static final String MENUS_COLUMN_KEY = "menu_key";

	/************************* application table - columns *****************/
	public static final String APPLICATION_TABLE = "Application";
	public static final String APPLICATION_COLUMN_ID = "app_id";
	public static final String APPLICATION_COLUMN_APP_NAME = "app_name";
	public static final String APPLICATION_COLUMN_FOOTER_ID = "footer_id";
	public static final String APPLICATION_COLUMN_HEADER_ID = "header_id";
	public static final String APPLICATION_COLUMN_WINDOW_TITLE = "windowTitle";

	/************************* Footer table - columns ********************/
	public static final String FOOTER_TABLE_NAME = "Footer";
	public static final String FOOTER_TABLE_ID = "id";
	public static final String FOOTER_TABLE_BACKGROUND = "background";
	public static final String FOOTER_TABLE_ISACTIVE = "isActive";
	public static final String FOOTER_TABLE_ISBORDER = "isBorder";
	public static final String FOOTER_TABLE_CONTENT = "content";
	public static final String FOOTER_TABLE_ISROUND = "isRound";
	public static final String FOOTER_TABLE_CLOCK = "clock";
	public static final String FOOTER_TABLE_CONTENT_TEXT_SIZE = "contentTextSize";
	public static final String FOOTER_TABLE_CONTENT_TEXT_COLOR = "contentTextColor";
	public static final String FOOTER_TABLE_CONTENT_TEXT_FONT = "contentTextFont";
	public static final String FOOTER_TABLE_CONTENT_TEXT_STYLE = "contentTextStyle";

	/********************** Header table - columns **************************/
	public static final String HEADER_TABLE_NAME = "Header";
	public static final String HEADER_TABLE_ID = "id";
	public static final String HEADER_TABLE_BACKGROUND = "background";
	public static final String HEADER_TABLE_ISACTIVE = "isActive";
	public static final String HEADER_TABLE_ISBORDER = "isBorder";
	public static final String HEADER_TABLE_ISROUND = "isRound";
	public static final String HEADER_TABLE_TITLE = "title";
	public static final String HEADER_TABLE_TITLE_FONT = "titlefont";
	public static final String HEADER_TABLE_TITLE_SIZE = "titleSize";
	public static final String HEADER_TABLE_TITLE_COLOR = "titleColor";
	public static final String HEADER_TABLE_TITLE_STYLE = "titleStyle";

	/********************** Login table - columns ****************************/
	public static final String LOGIN_TABLE_NAME = "Auth";
	public static final String LOGIN_TABLE_ID = "id";
	public static final String LOGIN_TABLE_USERNAME = "username";
	public static final String LOGIN_TABLE_PASSWORD = "password";
	public static final String LOGIN_TABLE_EMAIL = "email";
	public static final String LOGIN_TABLE_ADDRESS = "address";
	public static final String LOGIN_TABLE_CONTACTNUMBER = "contactNumber";
	public static final String LOGIN_TABLE_AGE = "age";
	public static final String LOGIN_TABLE_GENDER = "gender";
	public static final String LOGIN_TABLE_CREATION_DATE = "creation_date";

	/************************** SysParams table - columns *********************/
	public static final String SYSPARAMS_TABLE_NAME = "SysParams";
	public static final String SYSPARAMS_TABLE_ID = "id";
	public static final String SYSPARAMS_TABLE_PARAM = "param";
	public static final String SYSPARAMA_TABLE_ISACTIVE = "isAllow";

	/************************* Screen table - columns **********************/
	public static final String SCREEN_TABLE_NAME = "Screen";
	public static final String SCREEN_TABLE_ID = "id";
	public static final String SCREEN_TABLE_TITLE = "title";
	public static final String SCREEN_TABLE_KEY = "key";
	public static final String SCREEN_TABLE_MENU_ID = "menu_id";

	/********************* ScreenContents table - columns *******************/
	public static final String SCREEN_CONTENTS_TABLE = "ScreenContents";
	public static final String SCREEN_CONTENTS_TABLE_ID = "id";
	public static final String SCREEN_CONTENTS_TABLE_TITLE = "title";
	public static final String SCREEN_CONTENTS_TABLE_KEY = "key";
	public static final String SCREEN_CONTENTS_TABLE_SCREEN_ID = "screen_id";

	/********************** Client table - columns *************************/
	public static final String CLIENT_TABLE_NAME = "Clients";
	public static final String CLIENT_TABLE_ID = "id";
	public static final String CLIENT_TABLE_USERNAME = "name";
	public static final String CLIENT_TABLE_CONTACT_NUMBER = "contact_no";
	public static final String CLIENT_TABLE_EMAIL = "email";
	public static final String CLIENT_TABLE_AGE = "age";
	public static final String CLIENT_TABLE_GENDER = "gender";
	public static final String CLIENT_TABLE_ACCOUNT_NUMBER = "account_no";
	public static final String CLIENT_TABLE_DATE = "date";
	public static final String CLIENT_TABLE_COUNTRY = "country";
	public static final String CLIENT_TABLE_DISTRICT = "district";
	public static final String CLIENT_TABLE_STATE = "state";
	public static final String CLIENT_TABLE_ADDRESS = "address";
	public static final String CLIENT_TABLE_REF_NAME = "ref_name";
	public static final String CLIENT_TABLE_REF_ADDR = "ref_addr";

	/********************* Product table - columns ************************/
	public static final String PRODUCT_TABLE_NAME = "products";
	public static final String PRODUCT_TABLE_ID = "id";
	public static final String PRODUCT_TABLE_PROD_NAME = "prod_name";
	public static final String PRODUCT_TABLE_PROD_QUANT = "prod_quant";
	public static final String PRODUCT_TABLE_BAR_CODE = "bar_code";
	public static final String PRODUCT_TABLE_PRICE = "price";
	public static final String PRODUCT_TABLE_TAX = "tax";
	public static final String PRODUCT_TABLE_COMP_NAME = "com_name";
	public static final String PRODUCT_TABLE_SUPL_NAME = "sup_name";
	public static final String PRODUCT_TABLE_SUP_CONTACT = "sup_contact";
	public static final String PRODUCT_TABLE_SUP_ACCT = "sup_acct";
	public static final String PRODUCT_TABLE_PAYM_MTHD = "pym_mthd";
	public static final String PRODUCT_TABLE_PAID_BLNC = "paid_blnc";
	public static final String PRODUCT_TABLE_NET_BLNC = "net_blnc";

	/*********************** USER Table - columns **************************/
	public static final String USER_TABLE_NAME = "USER";
	public static final String USER_TABLE_ID = "id";
	public static final String USER_TABLE_USERNAME = "name";
	public static final String USER_TABLE_PASSWORD = "password";
	public static final String USER_TABLE_CREATION_DATE = "creation_date";

	/***********************
	 * USER_PROD_RELATION_LOGS table - columns
	 *****************/
	public static final String USER_PROD_RELATION_TABLE_NAME = "USER_PROD_RELATION_LOGS";
	public static final String USER_PROD_RELATION_TABLE_ID = "id";
	public static final String USER_PROD_RELATION_TABLE_PROD_ID = "prod_id";
	public static final String USER_PROD_RELATION_LOGS_USER_ID = "user_id";
	public static final String USER_PROD_RELATION_LOGS_STATUS = "status";
	public static final String USER_PROD_RELATION_LOGS_LOG_TIME = "log_time";
	public static final String CREATE_STATUS = "C";
	public static final String UPDATE_STATUS = "U";
	public static final String DELETE_STATUS = "D";
	public static final String DELETE_ALL_STATUS = "DA";

	/********************************
	 * USER_CLIENT_RELATION_LOGS
	 *******************************/
	public static final String USER_CLIENT_RELATION_LOGS_TABLE = "USER_CLIENT_RELATION_LOGS";
	public static final String USER_CLIENT_RELATION_LOGS_ID = "id";
	public static final String USER_CLIENT_RELATION_LOGS_CLIENT_ID = "client_id";
	public static final String USER_CLIENT_RELATION_LOGS_USER_ID = "user_id";
	public static final String USER_CLIENT_RELATION_LOGS_STATUS = "status";
	public static final String USER_CLIENT_RELATION_LOGS_LOG_TIME = "log_time";

	/************************** SALE - Table ********************************/
	public static final String SALE_TABLE_NAME = "Sale";
	public static final String SALE_TABLE_ID = "id";
	public static final String SALE_TABLE_PRODUCT_ID = "prod_id";
	public static final String SALE_TABLE_CLIENT_ID = "client_id";
	public static final String SALE_TABLE_USER_ID = "user_id";
	public static final String SALE_TABLE_SUPP_NAME = "sup_name";
	public static final String SALE_TABLE_SOLD_PRICE = "sold_price";
	public static final String SALE_TABLE_SOLD_QUANTITY = "sold_quant";
	public static final String SALE_TABLE_DATE = "date";
	public static final String SALE_TABLE_PERCENT_PROFIT = "percent_profit";
	public static final String SALE_TABLE_TOTAL_BILL = "total_bill";

	/*********************** Utility methods *************************/
	public static boolean isNull(Object obj) {
		return obj == null;
	}

	public static boolean isNullOrEmptyString(String val) {
		return Objects.isNull(val) || val.isEmpty();
	}

	public static boolean isNullInteger(Integer i) {
		return Objects.isNull(i);
	}

	public static boolean isNullOrZeroInteger(Integer i) {
		return isNullInteger(i) || i == 0;
	}

	public static boolean isNullOrEmptyList(List<?> li) {
		return isNull(li) || li.isEmpty();
	}

	public static boolean isNullOrEmptyMap(Map<?, ?> map) {
		return isNull(map) || map.isEmpty();
	}

	public static double calculateProfit(double sale, double cost) {
		double profit = sale - cost;
		double percent = (profit / cost) * 100;
		return percent;
	}

	
	public static List<String> weekDates(){
		List<String> dates = new ArrayList<>();
		LocalDate localDate = LocalDate.now();
		for(int x=0 ;x<=7; x++) {
			dates.add(localDate.plusDays(x).toString());
		}
		return dates;
	}
	
	public static List<String> monthDates(){
		List<String> dates = new ArrayList<>();
		LocalDate localDate = LocalDate.now();
		for(int x=0 ;x<=30; x++) {
			dates.add(localDate.plusDays(x).toString());
		}
		return dates;
	}

	public static String getProdLogStatus(String status) {
		switch (status) {
		case CREATE_STATUS:
			return "Created";
		case UPDATE_STATUS:
			return "Updated";
		case DELETE_STATUS:
			return "Deleted";
		case DELETE_ALL_STATUS:
			return "Delete All";
		default:
			return "";
		}
	}
}
