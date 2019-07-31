package com.st.lms.controller;

import com.st.lms.dao.BorrowerDao;

public class BorrowerController {
	BorrowerDao borrower = new BorrowerDao();
	public boolean borrowerIdExistInBorrowerDatabase(int deleteBorrowerId) {
		return borrower.borrowerIdExistInBorrowerTable(deleteBorrowerId);
	}
	
	public boolean retrieveCardNumberExistInDataBase(int cardNumber) {
		return borrower.retrieveCardNumberExistInTable(cardNumber);
	}
	
	public void addBorrowerInBorrowerDataBase(int cardNumber, String borrowerName, String borrowerAddress,
			String borrowerPhone) {
		borrower.addBorrowerInBorrowerTable(cardNumber, borrowerName, borrowerAddress, borrowerPhone);
	}
	
	public void deleteBorrowersFromBorrowerDatabase(int cardNumber) {
		borrower.deleteBorrowersFromBorrowerTable(cardNumber);
	}
	
	public void UpdateBorrowerInBorrowerDataBase(int cardNumber, String borrowerName, String borrowerAddress,
			String borrowerPhone) {
		borrower.UpdateBorrowerInBorrowerTable(cardNumber, borrowerName, borrowerAddress, borrowerPhone);

	}
}
