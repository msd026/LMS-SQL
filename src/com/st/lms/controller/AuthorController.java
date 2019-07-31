package com.st.lms.controller;

import com.st.lms.dao.AuthorDao;

public class AuthorController {
	
	AuthorDao authordao = new AuthorDao();
	
	public boolean retrieveAuthorsFromAuthorDatabase(int author) {
		return this.authordao.retrieveAuthorsFromAuthorTable(author);
	}
	
	public void addAuthorToAuthorDatabase(int authorId, String authorName) {
		authordao.addAuthorToAuthorTable(authorId, authorName);
	}
	
	public  void deleteAuthorFromAuthorDatabase(int deleteAuthorId) {
		authordao.deleteAuthorsFromAuthorTable(deleteAuthorId);
	}
	
	public void UpdateAuthorFromAuthorDatabase(int authID, String authorName) {
		authordao.updateAuthorsFromAuthorTable(authID, authorName);
	}
	public void displayAuthorsFromDatabase() {
		authordao.displayAuthorFromTable();
	}
}
