package com.db.Exception;

public class DBException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1813986000648620945L;

	public DBException(String message) {
		super(message);
	}

	public DBException(String message, Throwable arg1) {
		super(message, arg1);
	}

}
