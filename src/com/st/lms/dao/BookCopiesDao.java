package com.st.lms.dao;
import com.st.lms.model.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookCopiesDao {

	private static final String url = "jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=UTC";	
	private static final String user = "root";
	private static final String password = "Lampard92!";
	
	static BookCopies bookCopy = new BookCopies();
	public static boolean retrieveBookFromBranchTable(int bookId, int branchId) {
		bookCopy.setBookId(bookId);
		bookCopy.setBranchId(branchId);
		boolean bookExistInBranch = false;
		
		try (Connection conn = DriverManager.getConnection(url, user, password)){
			
			PreparedStatement preparedStmt = conn
					.prepareStatement("select * from tbl_book_copies where branchId=? and bookId=?");
			preparedStmt.setInt(1, bookCopy.getBranchId());
			preparedStmt.setInt(2, bookCopy.getBookId());
			preparedStmt.execute();

			ResultSet result = preparedStmt.executeQuery();

			while (result.next()) {
				bookCopy.setBookId(result.getInt("bookId"));
				if (bookCopy.getBookId() == bookId) {
					bookExistInBranch = true;
				}
			}
			
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("enter an ID in the list provided");
		}
		return bookExistInBranch;
	}
	
	
	public static void addBookCopiesToLibrary(int bookId, int numberOfCopyToAddToLibrary, int branchId) {
		bookCopy.setBookId(bookId);
		bookCopy.setBranchId(branchId);
		bookCopy.setNoOfCopies(numberOfCopyToAddToLibrary);
		try (Connection conn = DriverManager.getConnection(url, user, password)){
			int totalBookCopiesAfterUpdate = 0;
			totalBookCopiesAfterUpdate = numberOfCopyToAddToLibrary + countCopiesofBookInBranch(bookCopy.getBookId(), bookCopy.getBranchId());
			if (!retrieveBookFromBranchTable(bookCopy.getBookId(), bookCopy.getBranchId())) {
				String sqlQuery = "insert into tbl_book_copies values(?,?,?)";
				PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);
				preparedStmt.setInt(1, bookCopy.getBookId());
				preparedStmt.setInt(2, bookCopy.getBranchId());
				preparedStmt.setInt(3, totalBookCopiesAfterUpdate);
				preparedStmt.executeUpdate();
				
			}
			String sqlQuery = "update tbl_book_copies set noOfCopies=? where bookId=? and branchId=?";
			PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);
			preparedStmt.setInt(1, totalBookCopiesAfterUpdate);
			preparedStmt.setInt(2, bookCopy.getBookId());
			preparedStmt.setInt(3, bookCopy.getBranchId());

			System.out.println("You now have " + totalBookCopiesAfterUpdate + " copies");
			preparedStmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Book Cannot be added");
		}
	}
	
	public static int countCopiesofBookInBranch(int bookId, int branchId) {
		bookCopy.setBookId(bookId);
		bookCopy.setBranchId(branchId);
		int returnValue = 0;
		try (Connection conn = DriverManager.getConnection(url, user, password)){
			
			String sqlQuery = "select * from tbl_book_copies where bookId=? and branchId=?";
			PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);
			preparedStmt.setInt(1, bookCopy.getBookId());
			preparedStmt.setInt(2, bookCopy.getBranchId());
			ResultSet result = preparedStmt.executeQuery();
			if (result.next()) {
				returnValue = result.getInt("noOfCopies");
			}
			
			conn.close();
		} catch (Exception e) {
			
			System.out.println("Choose another Branch");
		}
		return returnValue;

	}
	
	public static void reduceBookCopiesFromBranchTable(int branchId, int bookId) {
		bookCopy.setBookId(bookId);
		bookCopy.setBranchId(branchId);
		if (countCopiesofBookInBranch(bookCopy.getBookId(), bookCopy.getBranchId()) > 0) {
			try (Connection conn = DriverManager.getConnection(url, user, password)){
				
				int totalBookCopiesAfterUpdate = 0;
				totalBookCopiesAfterUpdate = countCopiesofBookInBranch(bookId, branchId) - 1;
				String sqlQuery = "update tbl_book_copies set noOfCopies=? where bookId=? and branchId=?";
				PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);
				preparedStmt.setInt(1, totalBookCopiesAfterUpdate);
				preparedStmt.setInt(2, bookCopy.getBookId());
				preparedStmt.setInt(3, bookCopy.getBranchId());

				System.out.println("You now have " + totalBookCopiesAfterUpdate + " copies");

				preparedStmt.executeUpdate();

			} catch (Exception e) {
				
				System.out.println("Error in reduceBookCopiesFromBranchTable function");
			}
		} else {
			System.out.println("There is no copy of this book in this branch");
		}
	}

}
