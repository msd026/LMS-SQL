package com.st.lms.controller;

import com.st.lms.dao.LibraryBranchDao;

public class BranchController {

	LibraryBranchDao libraryBranch = new LibraryBranchDao();
	public boolean retrievebranchIDFromLibraryDatabase(int digit) {
		return libraryBranch.retrievebranchIDFromLibraryTable(digit);
	}
	
	public void selectBranchFromDataBase() {
		libraryBranch.selectBranchFromTable();
	}
	
	public  void addBranchToLibraryDatabase(int newBranchID, String newBranchName, String newBranchAddress) {
		libraryBranch.addBranchToLibraryDatabase(newBranchID, newBranchName, newBranchAddress);
	}
	
	public void deleteBranchFromLibraryDatabase(int deleteBranchId) {
		libraryBranch.DeleteBranchFromLibraryTable(deleteBranchId);
	}
	
	public void displayLibraryBranchesFromDatabase() {
		libraryBranch.displayLibraryBranchesFromTable();
	}
	
	public void updateLibraryBranchDatabase(int newBranchID, String newBranchName, String newBranchAddress) {
		libraryBranch.updateLibraryInformation(newBranchID, newBranchName, newBranchAddress);
	}
	
	public void updateLibraryInDatabase(int iD, String newBranchName, String NewBranchAddress) {
		libraryBranch.updateLibraryInformation(iD, newBranchName, NewBranchAddress);
	}
}
