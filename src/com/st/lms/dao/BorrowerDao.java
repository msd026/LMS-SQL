package com.st.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.st.lms.model.Borrower;

public class BorrowerDao {

	private static final String url = "jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=UTC";	
	private static final String user = "root";
	private static final String password = "Lampard92!";
	
	Borrower borrower= new Borrower();
	
	public boolean retrieveCardNumberExistInTable(int cardNumber) {
		borrower.setCardNo(cardNumber);
		boolean cardExit = false;
		try (Connection conn = DriverManager.getConnection(url, user, password)){
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("select * from tbl_borrower");
			while (result.next()) {
				borrower.setCardNo(result.getInt("cardNo"));
				if ( borrower.getCardNo()== cardNumber) {
					cardExit = true;
				}
			}
			conn.close();

		} catch (Exception e) {
			// e.printStackTrace();
		}
		return cardExit;
	}
	
	public boolean borrowerIdExistInBorrowerTable(int deleteBorrowerId) {
		boolean idExist = false;
		borrower.setCardNo(deleteBorrowerId);
		try (Connection conn = DriverManager.getConnection(url, user, password)){
			Statement statement = conn.createStatement();
			ResultSet result = statement
					.executeQuery("select cardNo from tbl_borrower where cardNo=" + deleteBorrowerId);
			while (result.next()) {
				
				if (result.getInt("cardNo") == borrower.getCardNo()) {
					idExist = true;
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return idExist;
	}
	
	public void addBorrowerInBorrowerTable(int cardNumber, String borrowerName, String borrowerAddress,
			String borrowerPhone) {
		
		try (Connection conn = DriverManager.getConnection(url, user, password)){
			
			String sqlQuery = "insert into tbl_borrower values(?,?,?,?)";
			PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);

			preparedStmt.setInt(1, cardNumber);
			preparedStmt.setString(2, borrowerName);
			preparedStmt.setString(3, borrowerAddress);
			preparedStmt.setString(4, borrowerPhone);
			preparedStmt.execute();
		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println("Error in addBorrowerToBorrowerTable function");

		}
	}
	
	public void UpdateBorrowerInBorrowerTable(int cardNumber, String borrowerName, String borrowerAddress,
			String borrowerPhone) {
		
		try (Connection conn = DriverManager.getConnection(url, user, password)){
			
			String sqlQuery = "update tbl_borrower set name=?, address=?,phone=? where cardNo=?";
			PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);
			preparedStmt.setString(1, borrowerName);
			preparedStmt.setString(2, borrowerAddress);
			preparedStmt.setString(3, borrowerPhone);
			preparedStmt.setInt(4, cardNumber);
			preparedStmt.execute();

		} catch (SQLException e) {
			System.out.println("Update Borrower Exception");
		}

	}
	
	public void deleteBorrowersFromBorrowerTable(int deleteBorrowerId) {
		borrower.setCardNo(deleteBorrowerId);
		try (Connection conn = DriverManager.getConnection(url, user, password)){
			String sqlQuery = "delete from tbl_borrower where cardNo=?";
			PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);
			preparedStmt.setInt(1, borrower.getCardNo());
			preparedStmt.execute();
		} catch (SQLException e) {
			System.out.println(" Delete Browser Exception ");
		}
	}
	

}
