package com.lym.util;

import java.sql.*;

public class DbUtil {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	private String url = "jdbc:mysql://127.0.0.1:3306/db_script";
	private String user = "root";
	private String password = "123456";
	private Connection mConnection;

	public Connection getConnect() throws Exception {
		if (null == mConnection || mConnection.isClosed()) {
			mConnection = DriverManager.getConnection(url, user, password);
		}
		return mConnection;
	}

	public synchronized ResultSet executeQuery(String sql) throws Exception {
		Statement stmt = mConnection.createStatement();
		return stmt.executeQuery(sql);
	}

	public synchronized void execute(String sql) throws Exception {
		Statement stmt = mConnection.createStatement();
		stmt.execute(sql);
	}

	public void close() throws Exception {
		mConnection.close();
	}
	
}
