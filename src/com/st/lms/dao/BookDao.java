package com.st.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookDao {

	private static final String url = "jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=UTC";	
	private static final String user = "root";
	private static final String password = "Lampard92!";
	
	
	public static void displayBooksFromTable() {
		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("select * from tbl_book");
			while (result.next()) {
				System.out.print(result.getString("bookId"));
				System.out.print(") ");
				System.out.print(result.getString("title"));
				System.out.print(" ");
				System.out.print("\n ");

			}
		} catch (Exception e) {
			// e.printStackTrace();
		}

	}
	
	public static boolean retrieveBooksFromABookTable(int newBook) {
		boolean idExist = false;
		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("select bookId from tbl_book where bookId=" + newBook);
			while (result.next()) {
				if (result.getInt("bookId") == newBook) {
					idExist = true;
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idExist;
	}
	
	public static void addBookToBookTable(int bookId, String title, int authId, int pubId) {
		Connection conn;
		try {
			conn = DriverManager.getConnection(url, user, password);

			String sqlQuery = "insert into tbl_book values(?,?,?,?)";
			PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);
			preparedStmt.setInt(1, bookId);
			preparedStmt.setString(2, title);
			preparedStmt.setInt(3, authId);
			preparedStmt.setInt(4, pubId);
			preparedStmt.execute();

			conn.close();

		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println(
					"Cannot add or update a child row. The Book ID should be new and The associated author and publisher have to exit first");

		}

	}
	
	public static void deleteBooksFromBookTable(int deleteBookId) {
		Connection conn;
		try {
			conn = DriverManager.getConnection(url, user, password);

			String sqlQuery = "delete from tbl_book where bookId=?";
			PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);
			preparedStmt.setInt(1, deleteBookId);
			preparedStmt.execute();
		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println(" deleteBookFromTable ");
		}
	}
	
	public static void updateBooksFromBookTable(int iD, String title, int authorId, int publisherId) {
		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			PreparedStatement preparedStmt = conn
					.prepareStatement("update tbl_book set title=?, authId=?, pubId=? where bookId=?");
			preparedStmt.setString(1, title);
			preparedStmt.setInt(2, authorId);
			preparedStmt.setInt(3, publisherId);
			preparedStmt.setInt(4, iD);
			preparedStmt.execute();

			System.out.println("You have updated the branch with id " + iD + " with title " + title + " Author Id "
					+ authorId + " Publisher ID " + publisherId);
			conn.close();

		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("Error in updateBooksFromBookTable function");
		}
	}
	
	
}
