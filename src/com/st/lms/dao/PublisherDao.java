package com.st.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.st.lms.model.Publisher;

public class PublisherDao {

	private static final String url = "jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=UTC";	
	private static final String user = "root";
	private static final String password = "Lampard92!";
	
//	Publisher Publishers = new Publisher();
	
	public boolean retrievePublisherFromPublisherTable(int PublisheID) {
		boolean idExist = false;
		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("select * from tbl_publisher where publisherId=" + PublisheID);
			while (result.next()) {
				if (result.getInt("publisherId") == PublisheID) {
					idExist = true;
				}
			}
			conn.close();
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return idExist;

	}
	
	public void displayPublishersFromTable() {
		// TODO Auto-generated method stub
		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("select * from tbl_publisher");
			while (result.next()) {
				System.out.print(result.getString("publisherId"));
				System.out.print(") ");
				System.out.print(result.getString("publisherName"));
				System.out.print("\n ");
			}

		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("Publisher doesn't exist");
		}

	}
	
	public void addPublisherToPublisherTable(int publisherId, String publisherName, String publisherAddress,
			String publisherPhone) {
		// TODO Auto-generated method stub
		Connection conn;
		try {
			conn = DriverManager.getConnection(url, user, password);

			String sqlQuery = "insert into tbl_publisher values(?,?,?,?)";

			PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);

			preparedStmt.setInt(1, publisherId);
			preparedStmt.setString(2, publisherName);
			preparedStmt.setString(3, publisherAddress);
			preparedStmt.setString(4, publisherPhone);
			preparedStmt.execute();
			// listBooks();
			conn.close();
		} catch (SQLException e) {
			/// e.printStackTrace();
			System.out.println("Error in addPublisherToPublisherTable function");

		}

	}
	
	
	public void deletePublishersFromPublisherTable(int deleteAuthorId) {
		// TODO Auto-generated method stub

		Connection conn;
		try {
			conn = DriverManager.getConnection(url, user, password);

			String sqlQuery = "delete from tbl_publisher where publisherId=?";
			PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);
			preparedStmt.setInt(1, deleteAuthorId);
			preparedStmt.execute();
		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println(" deletePublishersFromPublisherTable ");
		}
	}
	
	public void updatePublishersFromPublisherTable(int publisherId, String newPublisherName,
			String newPublisherAddress, String newPublisherPhone) {
		Connection conn;
		try {
			conn = DriverManager.getConnection(url, user, password);

			String sqlQuery = "update tbl_publisher set publisherId=?,publisherName=?, publisherAddress=?, publisherPhone=?";
			PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);
			preparedStmt.setInt(1, publisherId);
			preparedStmt.setString(2, newPublisherName);
			preparedStmt.setString(3, newPublisherAddress);
			preparedStmt.setString(4, newPublisherPhone);
			preparedStmt.execute();

			conn.close();
		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println("Error in updatePublishersFromPublisherTable function");
		}

	}
	
	

}
