package com.st.lms.controller;

import com.st.lms.dao.BookDao;

public class BookController {

	public static boolean retrieveBooksFromABookDatabase(int newBook) {
		return BookDao.retrieveBooksFromABookTable(newBook);
	}
	
	public static void addBooksToBookDataBase(int bookId, String title, int authId, int pubId) {
		BookDao.addBookToBookTable(bookId, title, authId, pubId);
	}
	
	public static void deleteBooksFromBookDatabase(int deleteBookId) {
		BookDao.deleteBooksFromBookTable(deleteBookId);
	}
	
	public static void updateBooksFromBookDatabase(int iD, String title, int authorId, int publisherId) {
		BookDao.updateBooksFromBookTable(iD, title, authorId, publisherId);
	}
	
	public static void displayBooksFromDatabase() {
		BookDao.displayBooksFromTable();
	}
}
