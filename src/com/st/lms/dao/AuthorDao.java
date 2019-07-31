package com.st.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.st.lms.model.Author;

public class AuthorDao {

	private static final String url = "jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=UTC";	
	private static final String user = "root";
	private static final String password = "Lampard92!";
	
	Author author = new Author();
	public boolean retrieveAuthorsFromAuthorTable(int AuthorID) {
		author.setAuthorId(AuthorID);
		boolean idExist = false;
		try (Connection conn = DriverManager.getConnection(url, user, password)){
			
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("select * from tbl_author where authorId=" + author.getAuthorId());
			while (result.next()) {
				author.setAuthorId(AuthorID);
				if (result.getInt("authorId") == author.getAuthorId()) {
					idExist = true;
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return idExist;

	}

	public  void displayAuthorFromTable() {
		try (Connection conn = DriverManager.getConnection(url, user, password)){
			
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("select * from tbl_author");
			while (result.next()) {
				author.setAuthorId(result.getInt("authorId"));
				author.setAuthorName(result.getString("AuthorName"));
				System.out.print(author.getAuthorId());
				System.out.print(") ");
				System.out.print(author.getAuthorName());
				System.out.print("\n ");
			}

		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("Author doesn't exist");
		}
		finally {
			
		}
	}


	public void addAuthorToAuthorTable(int authorId, String authorName) {

		try (Connection conn = DriverManager.getConnection(url, user, password)){
			author.setAuthorId(authorId);
			author.setAuthorName(authorName);
			String sqlQuery = "insert into tbl_author values(?,?)";
			PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);
			preparedStmt.setInt(1, author.getAuthorId());
			preparedStmt.setString(2, author.getAuthorName());
			preparedStmt.execute();

		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println("Author cannot be added");
		}

	}

	public void deleteAuthorsFromAuthorTable(int deleteAuthorId) {
		
		try (Connection conn = DriverManager.getConnection(url, user, password)){
			
			author.setAuthorId(deleteAuthorId);
			String sqlQuery = "delete from tbl_author where authorId=?";
			PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);
			preparedStmt.setInt(1, author.getAuthorId());
			preparedStmt.execute();
		} catch (SQLException e) {
			System.out.println(" Book Id is not accessible ");
		}
	}

	public void updateAuthorsFromAuthorTable(int authID, String authorName) {
		author.setAuthorId(authID);
		author.setAuthorName(authorName);
		try (Connection conn = DriverManager.getConnection(url, user, password)){
			PreparedStatement preparedStmt = conn
					.prepareStatement("update tbl_author set authorId=?, authorName=? where authorId=?");
			preparedStmt.setInt(1, author.getAuthorId());
			preparedStmt.setString(2, author.getAuthorName());
			preparedStmt.setInt(3, author.getAuthorId());
			preparedStmt.execute();
			
			System.out.println("You have updated the author with id " + author.getAuthorId() + " with name " + author.getAuthorName());
			conn.close();

		} catch (Exception e) {
			System.out.println("Author Id Cannot be modified");
		}
		
	}

}