package com.st.lms.controller;

import com.st.lms.dao.BookCopiesDao;

public class BookCopiesController {

	public boolean retrieveBooksFromABranchDatabase(int bookId, int BranchId) {
		return BookCopiesDao.retrieveBookFromBranchTable(bookId, BranchId);
	}
	
	public void countCopiesofBookInBranchLibrary(int bookId, int branchId) {
		BookCopiesDao.countCopiesofBookInBranch(bookId, branchId);
	}
	
	public void feedBookToBranchDatabase(int bookId, int numberOfCopyToAddToLibrary, int branchId) {
		BookCopiesDao.addBookCopiesToLibrary(bookId, numberOfCopyToAddToLibrary, branchId);
	}
	
	public void reduceBookCopiesFromBranchDatabase(int branchId, int bookId) {
		BookCopiesDao.reduceBookCopiesFromBranchTable(branchId, bookId);
	}

	
	
}
