package com.st.lms.controller;

import java.sql.Date;
import com.st.lms.dao.*;

public class BookLoansController {

	BookLoansDao BookLoans = new BookLoansDao();
	public boolean borrowerIsInBookLoansDatabase(int deleteBorrowerId) {
		return BookLoans.borrowerIsInBookLoansTable(deleteBorrowerId);
	}
	
	public void addToBookLoansDatabase(int branchId, int bookId, int cardNumber) {
		BookLoans.addToBookLoansTable(bookId, branchId, cardNumber);
	}
	
	public boolean bookIsLoanedFromBranchDatabase(int bookIdIn, int branchInput, int cardNumber) {

		return BookLoans.bookIsLoanedFromBookLoansTable(bookIdIn, branchInput, cardNumber);
	}
	
	public void deleteBorrowersFromBookLoansDatabase(int bookIdIn, int branchInput, int cardNumber) {

		BookLoans.deleteBorrowersFromBookLoansTable(bookIdIn, branchInput, cardNumber);
	}
	
	public void displayBorrowersFromDatabase() {
		BookLoans.displayBorrowersFromTable();
	}
	

	
	public void overrideDueDateInBookLoansDueDateDatabase(int cardNumber, int bookID, int branchIn,
			Date newDueDate) {
		BookLoans.overrideDueDateInBookLoansDueDateDatabase(cardNumber, bookID, branchIn, newDueDate);
	}

}
