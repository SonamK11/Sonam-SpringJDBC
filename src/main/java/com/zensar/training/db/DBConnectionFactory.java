package com.zensar.training.db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class DBConnectionFactory {
	
	public static Connection createConnection()throws Exception{
		Connection connection=null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/TrainingDB";
		String username="root";
		String password="ashishrao0526";
		
		connection =DriverManager.getConnection(url,username,password);
		return connection;
	}

}
