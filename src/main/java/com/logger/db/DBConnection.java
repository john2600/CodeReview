package com.logger.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection {
	public Connection connection;
	private static DBConnection dbSingleton;
	private Statement statement;
	static Properties properties;

	static {
		properties = new Properties();
		properties.put("jdbc.driver", "com.mysql.jdbc.Driver");
		properties.put("jdbc.url", "jdbc:mysql://localhost:3306/db");
		properties.put("jdbc.username", "root");
		properties.put("jdbc.password", "password");
		try {
			Class.forName(properties.getProperty("jdbc.driver"));
		} catch (ClassNotFoundException e) {
		}
	}

	public DBConnection() {
		try {
			this.connection = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties);
		} catch (SQLException e) {
		}
	}

	public void executeQuery(String request) {
		try {
			insert(request);
		} catch (SQLException e) {
		}
	}

	public static synchronized DBConnection getDbCon() throws SQLException {
		if (dbSingleton == null) {
			dbSingleton = new DBConnection();
		}
		return dbSingleton;

	}

	private int insert(String query) throws SQLException {
		statement = dbSingleton.connection.createStatement();
		final int result = statement.executeUpdate(query);
		return result;
	}

}
