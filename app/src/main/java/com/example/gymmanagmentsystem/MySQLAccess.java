package com.example.gymmanagmentsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class MySQLAccess {
	private static Connection dbConnection = null; //reset every time, fine being static
    private static Statement statement = null; //reset every time, fine being static
    private static ResultSet resultSet = null; //reset every time, fine being static


	//TODO change these to be properly structured queries that arent sql injectible
    public static int executeSQLQueryManipulation(String queryToExecute) {
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	
    	try {
			dbConnection = DriverManager.getConnection("jdbc:mysql://localhost/propertymanagementsystemschema?" 
			+ "user=root&password=Gmcia330@");
			
			statement = dbConnection.createStatement();
			
			statement.executeUpdate(queryToExecute);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			try {
				dbConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}
		}
    	
    	return 1;
    }


	//TODO change these to be properly structured queries that arent sql injectible
    public static ResultSet executeSQLQueryNonManipulation(String queryToExecute) {
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	
    	try {
			dbConnection = DriverManager.getConnection("jdbc:mysql://localhost/propertymanagementsystemschema?" 
			+ "user=root&password=Gmcia330@");
			
			statement = dbConnection.createStatement();
			
			resultSet = statement.executeQuery(queryToExecute);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null; //do this or else it will return the result set from the previous query if it fails
		}finally {
			try {
				dbConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return null;//do this or else it will return the result set from the previous query if it fails
			}
		}
    	
    	return resultSet;
    }
}
