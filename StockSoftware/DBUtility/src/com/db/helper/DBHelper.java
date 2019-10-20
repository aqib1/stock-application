package com.db.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

import com.db.utility.AppUtils;

public class DBHelper {
	private static DBHelper dbHelper = null;
	private static String DB_PATH = "";
	private Connection connection = null;

	private DBHelper() {
		try {
			Class.forName("org.sqlite.JDBC");
			setDBPath();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void initConnection() {
		try {
			connection = DriverManager.getConnection(DB_PATH);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		initConnection();
		return connection;
	}

	public void clear() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void setDBPath() {
		String os_name = System.getProperty("os.name");
		if (os_name.toLowerCase().contains(AppUtils.WINDOWS_OS)) {
			DB_PATH = AppUtils.WINDOWS_OS_DB_PATH;
		} else if (os_name.toLowerCase().contains(AppUtils.MAC_OS)) {
			DB_PATH = System.getProperty("user.home") + AppUtils.MAC_OS_DB_PATH;
		}
		DB_PATH = AppUtils.SQLITE_DRIVER_URL + DB_PATH;
	}

	public static DBHelper getInstance() {
		if (Objects.isNull(dbHelper)) {
			synchronized (DBHelper.class) {
				if (Objects.isNull(dbHelper)) {
					dbHelper = new DBHelper();
				}
			}
		}
		return dbHelper;
	}
}
