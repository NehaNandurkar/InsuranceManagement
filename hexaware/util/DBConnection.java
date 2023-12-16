package com.hexaware.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection{
  private static final String URL="jdbc:mysql://localhost:3306/insurance";
  private static final String USER="root";
  private static final String PASSWORD="123456789";
  
  public static Connection getConnection() {
	  try {
		return DriverManager.getConnection(URL,USER,PASSWORD);
	} catch (SQLException e) {
		throw new RuntimeException("Error occured while connecting ",e);
	}
  }
}