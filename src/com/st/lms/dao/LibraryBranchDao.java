package com.st.lms.dao;
import com.st.lms.model.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class LibraryBranchDao {

	private static final String url = "jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=UTC";	
	private static final String user = "root";
	private static final String password = "Lampard92!";
	

	static LibraryBranch library = new LibraryBranch();
	
	public boolean retrievebranchIDFromLibraryTable(int iD) {
		boolean idExist = false;
		try (Connection conn = DriverManager.getConnection(url, user, password)){
		
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("select branchId from tbl_library_branch where branchId=" + iD);
			while (result.next()) {
				library.setBranchID(result.getInt("branchId"));
				if (result.getInt("branchId") == iD) {
					idExist = true;
				}
			}
	
		} catch (Exception e) {
			System.out.println("Branch doesn't exist");
		}
		return idExist;
	}
	
	public void selectBranchFromTable() {
	try (Connection conn = DriverManager.getConnection(url, user, password)){
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery("select * from tbl_library_branch ");
		while (result.next()) {
			library.setBranchID(result.getInt("branchId"));
			library.setBranchName(result.getString("branchName"));
			library.setBranchAddress(result.getString("branchAddress"));
			
			System.out.print(library.getBranchID());
			System.out.print(") ");
			System.out.print(library.getBranchName());
			System.out.print(" ");
			System.out.print(library.getBranchAddress());
			System.out.print("\n ");
		}

	} catch (Exception e) {
		System.out.println("Branch doesn't exist");
	}
}
	
	public void displayLibraryBranchesFromTable() {
		try (Connection conn = DriverManager.getConnection(url, user, password)){
	
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("select * from tbl_library_branch");
			while (result.next()) {
				library.setBranchID(result.getInt("branchId"));
				library.setBranchName(result.getString("branchName"));
				library.setBranchAddress(result.getString("branchAddress"));
				
				System.out.print(library.getBranchID());
				System.out.print(") ");
				System.out.print(library.getBranchName());
				System.out.print(" ");
				System.out.print(library.getBranchAddress());
				System.out.print("\n ");
			}

		} catch (Exception e) {
			System.out.println("Publisher doesn't exist");
		}

	}
	
	
	public void updateLibraryInformation(int iD, String newBranchName, String newBranchAddress) {
		
		try (Connection conn = DriverManager.getConnection(url, user, password)){
			LibraryBranch library = new LibraryBranch(iD,newBranchName, newBranchAddress);
			PreparedStatement preparedStmt = conn
					.prepareStatement("update tbl_library_branch set branchName=?, branchAddress=? where branchId=?");
			preparedStmt.setString(1, newBranchName);
			preparedStmt.setString(2, newBranchAddress);
			preparedStmt.setInt(3, iD);
			preparedStmt.execute();

			System.out.println(" You have updated the branch with id " + library.getBranchID() + " with Branch Name " + library.getBranchName()
					+ " and address " + library.getBranchAddress());

		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("Error in updateLibraryInformation function");
		}
		displayLibraryBranchesFromTable();
	}
	
	
	public void addBranchToLibraryDatabase(int newBranchID, String newBranchName, String newBranchAddress) {
		
		
		try (Connection conn = DriverManager.getConnection(url, user, password);){
			LibraryBranch library = new LibraryBranch(newBranchID,newBranchName, newBranchAddress);
			String sqlQuery = "insert into tbl_library_branch values(?,?,?)";
			PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);
			preparedStmt.setInt(1, library.getBranchID());
			preparedStmt.setString(2, library.getBranchName());
			preparedStmt.setString(3, library.getBranchAddress());
			preparedStmt.execute();
		} catch (SQLException e) {
			System.out.println("Branch cannot be added");
		}
	}
	
	public void DeleteBranchFromLibraryTable(int deleteBranchId) {
		library.setBranchID(deleteBranchId);
		try (Connection conn = DriverManager.getConnection(url, user, password)){
			
			String sqlQuery = "delete from tbl_library_branch where branchId=?";
			PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);
			preparedStmt.setInt(1, library.getBranchID());
			preparedStmt.execute();
		} catch (SQLException e) {
			System.out.println("Branch doesn't exist");;
		}
	}
	
	
	
}
