package com.flipkart.utils;

import java.sql.*;

public class DBUtils {
	static final String DB_URL = "jdbc:mysql://localhost/CRS-SCHEMA";
	static final String USER = "root";
	static final String PASS = "Fk!@#%213498";

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;

	}

}