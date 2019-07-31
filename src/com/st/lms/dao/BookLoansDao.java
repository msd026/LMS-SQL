package com.st.lms.dao;

import com.st.lms.model.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

public class BookLoansDao {
	
	private static final String url = "jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=UTC";	
	private static final String user = "root";
	private static final String password = "Lampard92!";
	
	BookLoans bookLoans = new BookLoans();
	public boolean bookIsLoanedFromBookLoansTable(int bookIdIn, int branchInput, int cardNumber) {
		bookLoans.setBookId(bookIdIn);
		bookLoans.setBranchId(branchInput);
		bookLoans.setCardId(cardNumber);
		
		boolean bookloanExist = false;
		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			PreparedStatement preparedStmt = conn
					.prepareStatement("select * from tbl_book_loans where branchId=? and cardNo=?");
			preparedStmt.setInt(1, bookLoans.getBranchId());
			preparedStmt.setInt(2, bookLoans.getCardId());
			preparedStmt.execute();

			ResultSet result = preparedStmt.executeQuery();

			while (result.next()) {
				if (result.getInt("bookId") == bookLoans.getBookId()) {
					bookloanExist = true;
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("enter an ID in the list provided");
		}
		return bookloanExist;
	}
	public void displayBorrowersFromTable() {
		try (Connection conn = DriverManager.getConnection(url, user, password)){
			
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("select * from tbl_book_loans");
			while (result.next()) {
				bookLoans.setCardId(result.getInt("cardNo"));
				bookLoans.setBookId(result.getInt("bookId"));
				bookLoans.setBranchId(result.getInt("branchId"));
				System.out.print(bookLoans.getCardId());
				System.out.print(") ");
				System.out.print(bookLoans.getBookId());
				System.out.print(" , ");
				System.out.print(bookLoans.getBranchId());
				System.out.print("\n ");

			}
			
		} catch (Exception e) {
			// e.printStackTrace();
			
		}

	} 
	
	public void addToBookLoansTable(int bookId, int branchId, int cardNumber) {
		bookLoans.setBookId(bookId);;
		bookLoans.setBranchId(branchId);
		bookLoans.setCardId(cardNumber);
		try (Connection conn = DriverManager.getConnection(url, user, password)){
			String sqlQuery = "insert into tbl_book_loans values(?,?,?,?,?)";
			
			Date borrowDate = Date.valueOf("2019-07-08");
			String input = "2019-07-15";
			Date endDate = Date.valueOf(input);

			PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);

			preparedStmt.setInt(1, bookLoans.getBookId());
			preparedStmt.setInt(2, bookLoans.getBranchId());
			preparedStmt.setInt(3, bookLoans.getCardId());
			preparedStmt.setObject(4, borrowDate);
			preparedStmt.setObject(5, endDate);
			preparedStmt.execute();
			
		} catch (SQLException e) {
			System.out.println("Book ID cannot be added in Database");
		}
	}
	
	public boolean borrowerIsInBookLoansTable(int deleteBorrowerId) {
		boolean idExist = false;
		bookLoans.setCardId(deleteBorrowerId);
		try (Connection conn = DriverManager.getConnection(url, user, password)){
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("select * from tbl_book_loans where cardNo=" + bookLoans.getCardId());
			while (result.next()) {
				if (result.getInt("cardNo") == bookLoans.getCardId()) {
					idExist = true;
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return idExist;
	}
	
	public void overrideDueDateInBookLoansDueDateDatabase(int cardNumber, int bookID, int branchIn,
			Date newDueDate) {
		bookLoans.setBookId(bookID);
		bookLoans.setBranchId(branchIn);
		bookLoans.setCardId(cardNumber);
		bookLoans.setDueDate(newDueDate);
		
		try (Connection conn = DriverManager.getConnection(url, user, password)){
			String sqlQuery = "update tbl_book_loans set dueDate=? where cardNo=? and bookId=? and branchId=?";
			PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);
			preparedStmt.setDate(1, bookLoans.getDueDate());
			preparedStmt.setInt(2, bookLoans.getCardId());
			preparedStmt.setInt(3, bookLoans.getBookId());
			preparedStmt.setInt(4, bookLoans.getBranchId());
			preparedStmt.execute();

		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println("Error in overrideDueDateInBookLoansDueDateDatabase function");
		}

	}
	
	public void deleteBorrowersFromBookLoansTable(int bookId, int branchInput, int cardNumber) {
		bookLoans.setBookId(bookId);
		bookLoans.setBranchId(branchInput);
		bookLoans.setCardId(cardNumber);
		try (Connection conn = DriverManager.getConnection(url, user, password)){
			
			PreparedStatement preparedStmt = conn
					.prepareStatement("delete from tbl_book_loans where branchId=? and cardNo=? and bookId=?");
			preparedStmt.setInt(1, bookLoans.getBranchId());
			preparedStmt.setInt(2, bookLoans.getCardId());
			preparedStmt.setInt(3, bookLoans.getBookId());
			preparedStmt.execute();

		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("enter an ID in the list provided");
		}

	}
	public  boolean retrieveBooksFromBookLoansTable(int bookIdIn, int bookIdIn2, int bookIdIn3) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
