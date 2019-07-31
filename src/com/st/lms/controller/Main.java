package com.st.lms.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Scanner;

import com.st.lms.dao.*;

public class Main {
	static AuthorController AuthorControl = new AuthorController();
	static BorrowerController BorrowerControl = new BorrowerController();
	static BranchController BranchControl = new BranchController();
	static BookController BookControl = new BookController();
	static BookLoansController BookLoansControl = new BookLoansController();
	static PublisherController Publishers = new PublisherController();
	static BookCopiesController BookCopiesControl = new BookCopiesController();
	@SuppressWarnings("resource")
	public static void main(String args[]) throws IOException, InterruptedException {
		while (true) {
			System.out.println("Welcome to GCIT Library Management System. Which category of user are you?");
			System.out.println("1) Librarian");
			System.out.println("2) Administrator");
			System.out.println("3) Borrower");

			Scanner in = new Scanner(System.in);
			 String category = in.nextLine();

			while (true) {
				if (category.equals("1")) {
					
					System.out.println("Select one of these libraries");
					System.out.println("Q) to previous");
					BranchControl.displayLibraryBranchesFromDatabase();
					
					String inputString = in.nextLine();
					
					if (inputString.equals("Q")) {
						break;
					} else if (!Library.isNumeric(inputString) || inputString == null) {
						System.out.println("Enter the right branch ID ");
						continue;
					}

					int branch = Integer.parseInt(inputString);
					if (!BranchControl.retrievebranchIDFromLibraryDatabase(branch)) {
						System.out.println("Enter the right branch ID ");
						continue;
					}
					while (true) {
						System.out.println("1) Update details of the library");
						System.out.println("2) Add copies of book in the branch ");
						System.out.println("Q) Quit to previous");
						
						String branchInput = in.nextLine();
						if (branchInput.equals("Q")) {
							break;
						} else if (!Library.isNumeric(branchInput) || inputString == null) {
							System.out.println("Enter one of the options ");
							continue;
						} else if (branchInput.equals("1")) {
							
							System.out.println("Enter a new branch name");
							String newBranchIN = in.nextLine();
							
							System.out.println("Enter a new branch Address");
							String newBranchADD = in.nextLine();
							
							BranchControl.updateLibraryInDatabase(branch, newBranchIN, newBranchADD);

						} else if (branchInput.equals("2")) {
							System.out.println("Enter a book ID");
							BookController.displayBooksFromDatabase();
							String book = in.nextLine();
							if (!Library.isNumeric(book)) {
								System.out.println("The book Id is a number a number");
								continue;
							}
							System.out.println("Enter a book quantity");
							String quantity = in.nextLine();
							if (!Library.isNumeric(quantity)) {
								System.out.println("The book Id is a number a number");
								continue;
							}
							int newBook = Integer.parseInt(book);
							int newQuantity = Integer.parseInt(quantity);
							if (!BookController.retrieveBooksFromABookDatabase(newBook)) {
								System.out.println("Enter a valid book ID. This book is not available");
								continue;
							}
							// Library.countCopiesofBookInBranchLibrary(newBook, branch);
							BookCopiesControl.feedBookToBranchDatabase(newBook, newQuantity, branch);
						} else {
							System.out.println("Choose one of the options");
							continue;
						}
					}
				}

				 else if (category.equals("2")) {

					System.out.println("Choose one of the followings table");
					System.out.println("1) Book");
					System.out.println("2) Author");
					System.out.println("3) publisher");
					System.out.println("4) Library Branch");
					System.out.println("5) Borrower");
					System.out.println("6) due date");
					System.out.println("Q) to previous");

					String selectTable = in.nextLine();

					if (selectTable.equals("Q")) {
						break;
					}
					if (!Library.isNumeric(selectTable)) {
						System.out.println("Enter one of the options");
					}

					int tableSelected = Integer.parseInt(selectTable);
					if (tableSelected == 1) {
						while (true) {
							System.out.println("Choose of of the following functions");
							System.out.println("1) Add");
							System.out.println("2) Update");
							System.out.println("3) Delete");
							System.out.println("Q) to previous");

							String selectFunction = in.nextLine();
							if (selectFunction.equals("1")) {
								BookController.displayBooksFromDatabase();
								System.out.println("Enter a new Book ID");
								String bookIdInput = in.nextLine();
								if (!Library.isNumeric(bookIdInput)) {
									System.out.println("Enter a String in the title field");
									continue;
								}
								int bookId = Integer.parseInt(bookIdInput);
								if (BookController.retrieveBooksFromABookDatabase(bookId)) {
									System.out.println("This Book ID already exists");
									continue;
								}
								System.out.println("Enter a new Book title");
								String titleInput = in.nextLine();
								if (!Library.stringInput(titleInput.toString())) {
									System.out.println("Enter a String in the title field");
									continue;
								}
								
								System.out.println("Enter a new author ID");
								AuthorControl.displayAuthorsFromDatabase();
								String authorIdInput = in.nextLine();
								if (!Library.isNumeric(authorIdInput)) {
									System.out.println("Enter a number in the ID field ");
									continue;
								}
								int authId = Integer.parseInt(authorIdInput);
								if (!AuthorControl.retrieveAuthorsFromAuthorDatabase(authId)) {
									System.out.println("The Author ID has to exist in the Author Table First");
									continue;
								}
								System.out.println("Enter a new Book publisher ID");
								Publishers.displayPublishersFromDatabase();
								String publisherIdInput = in.nextLine();
								if (!Library.isNumeric(publisherIdInput)) {
									System.out.println("Enter a number in the ID field ");
									continue;
								}
								int pubId = Integer.parseInt(publisherIdInput);
								if (!Publishers.retrievePublisherFromPublisherDatabase(pubId)) {
									System.out.println("The Publisher already exists. Choose a different ID");
									continue;
								}
								BookController.addBooksToBookDataBase(bookId, titleInput, authId, pubId);
								// break;
							} else if (selectFunction.equals("3")) {
								BookController.displayBooksFromDatabase();
								System.out.println("Are you ready to delete a book");
								String bookDeleteIdInput = in.nextLine();
								if (!BookController.retrieveBooksFromABookDatabase(
										Integer.parseInt(bookDeleteIdInput))) {
									System.out.println("This Book doesn't Exist");
									continue;
								}
								int deleteBookId = Integer.parseInt(bookDeleteIdInput);
								BookController.deleteBooksFromBookDatabase(deleteBookId);
							} else if (selectFunction.equals("2")) {
								BookController.displayBooksFromDatabase();
								System.out.println("Enter a new Book ID to Update");
								String bookUpdateIdInput = in.nextLine();
								if (!BookController.retrieveBooksFromABookDatabase(
										Integer.parseInt(bookUpdateIdInput))) {
									System.out.println("The Book ID has to exist to be updated");
									continue;
								}
								int iD = Integer.parseInt(bookUpdateIdInput);
								System.out.println("Enter a new title for the Book");
								String titleUpdateInput = in.nextLine();
								String title = titleUpdateInput;
								System.out.println("Enter a new Book author ID");
								String authorIdUpdateInput = in.nextLine();
								if (!AuthorControl.retrieveAuthorsFromAuthorDatabase(
										Integer.parseInt(authorIdUpdateInput))) {
									System.out.println("The Author ID has to exist in the Author Table First");
									continue;
								}
								int authorId = Integer.parseInt(authorIdUpdateInput);
								System.out.println("Enter a new Book publisher ID");
								String publisherUpdateIdInput = in.nextLine();
								if (!Publishers.retrievePublisherFromPublisherDatabase(
										Integer.parseInt(publisherUpdateIdInput))) {
									System.out.println("The Publisher ID has to exist in the Author Table First");
									continue;
								}
								int publisherId = Integer.parseInt(publisherUpdateIdInput);

								BookController.updateBooksFromBookDatabase(iD, title, authorId, publisherId);
							} else if (selectFunction.equals("Q")) {
								break;

							} else {
								System.out.println("Choose one of the options");
								continue;
							}

						}
					} else if (tableSelected == 2) {
						System.out.println("Choose of of the following functions");
						System.out.println("1) Add Author");
						System.out.println("2) Update Author");
						System.out.println("3) Delete Author");
						System.out.println("Q) To previous");
						String selectAuthorFunction = in.nextLine();

						if (selectAuthorFunction.equals("Q")) {
							break;
						}

						if (!Library.isNumeric(selectAuthorFunction)) {
							System.out.println("Enter a number in the ID field ");
							continue;
						}

						if (selectAuthorFunction.equals("1")) {
							AuthorControl.displayAuthorsFromDatabase();
							System.out.println("Add an author ID");
							String AuthorID = in.nextLine();
							if (!Library.isNumeric(AuthorID)) {
								System.out.println("Enter a number in the ID field ");
								continue;
							}
							int authorId = Integer.parseInt(AuthorID);
							if (AuthorControl.retrieveAuthorsFromAuthorDatabase(authorId)) {
								System.out.println("This Author ID already exists");
								continue;
							}
							System.out.println("Add an author Name");
							String authorName = in.nextLine();
							
							AuthorControl.addAuthorToAuthorDatabase(authorId, authorName);
						} else if (selectAuthorFunction.equals("2")) {

							AuthorControl.displayAuthorsFromDatabase();
							System.out.println("Add an author ID");
							String AuthorID = in.nextLine();
							if (!AuthorControl.retrieveAuthorsFromAuthorDatabase(
									Integer.parseInt(AuthorID))) {
								System.out.println("The Author ID has to exist in the Author Table First");
								continue;
							}
							int authorId = Integer.parseInt(AuthorID);
							System.out.println("Add an author Name");
							String newAuthorName = in.nextLine();
							AuthorControl.UpdateAuthorFromAuthorDatabase(authorId, newAuthorName);
						} else if (selectAuthorFunction.equals("3")) {
							AuthorControl.displayAuthorsFromDatabase();
							System.out.println("You are about to delete an author. Enter an ID");
							String authorDeleteIdInput = in.nextLine();
							if (!AuthorControl.retrieveAuthorsFromAuthorDatabase(
									Integer.parseInt(authorDeleteIdInput))) {
								System.out.println("The Author ID has to exist in the Author Table First");
								continue;
							}
							int deleteAuthorId = Integer.parseInt(authorDeleteIdInput);
							AuthorControl.deleteAuthorFromAuthorDatabase(deleteAuthorId);
						} else {
							System.out.println("Choose one of the options");
							continue;
						}

					} else if (tableSelected == 3) {
						System.out.println("Choose of of the following functions");
						System.out.println("1) Add Publisher");
						System.out.println("2) Update Publisher");
						System.out.println("3) Delete Publisher");
						String selectAuthorFunction = in.nextLine();
						if (selectAuthorFunction.equals("1")) {
							Publishers.displayPublishersFromDatabase();
							System.out.println("Add a new Publisher ID ");
							String PublisherID = in.nextLine();
							if (!Library.isNumeric(PublisherID)) {
								System.out.println("The publisher ID is a number");
							}
							if (BranchControl.retrievebranchIDFromLibraryDatabase(
									Integer.parseInt(PublisherID))) {
								System.out.println("This Publisher ID already exists");
							}
							int publisherId = Integer.parseInt(PublisherID);
							System.out.println("Add a new Publisher Name ");
							String newPublisherName = in.nextLine();
							System.out.println("Add a new Publisher Phone");
							String newPublisherPhone = in.nextLine();
							System.out.println("Add a new Publisher Address");
							String newPublisherAddress = in.nextLine();

							Publishers.addPublishersToPublisherDatabase(publisherId, newPublisherName, newPublisherAddress,
									newPublisherPhone);
						} else if (selectAuthorFunction.equals("2")) {
							Publishers.displayPublishersFromDatabase();
							System.out.println("Add a Publisher ID to Update");
							String PublisherID = in.nextLine();
							if (!Library.isNumeric(PublisherID)) {
								System.out.println("The publisher ID is a number");
								continue;
							}
							if (!BranchControl.retrievebranchIDFromLibraryDatabase(
									Integer.parseInt(PublisherID))) {
								System.out.println("Add a publisher ID that already exists");
								continue;
							}
							int publisherId = Integer.parseInt(PublisherID);
							System.out.println("Add a Publisher ID to Update");
							String newPublisherName = in.nextLine();
							System.out.println("Add a Publisher Phone to Update");
							String newPublisherPhone = in.nextLine();
							System.out.println("Add a Publisher Address to Update");
							String newPublisherAddress = in.nextLine();

							Publishers.updatePublishersToPublisherDatabase(publisherId, newPublisherName,
									newPublisherAddress, newPublisherPhone);
						} else if (selectAuthorFunction.equals("3")) {
							Publishers.displayPublishersFromDatabase();
							System.out.println("You are about to delete a Publisher. Enter an ID");
							String publisherDeleteIdInput = in.nextLine();
							if (!Library.isNumeric(publisherDeleteIdInput)) {
								System.out.println("The publisher ID is a number");
								continue;
							}
							if (!BranchControl.retrievebranchIDFromLibraryDatabase(
									Integer.parseInt(publisherDeleteIdInput))) {
								System.out.println("delete a publisher ID that already exists");
								continue;
							}
							int deletePublisherId = Integer.parseInt(publisherDeleteIdInput);
							Publishers.deletePublishersFromPublisherDatabase(deletePublisherId);
						} else {
							System.out.println("choose one of the provided options");
							break;
						}
					} else if (tableSelected == 4) {
						System.out.println("Choose of of the following functions");
						System.out.println("1) Add Branch");
						System.out.println("2) Update Branch");
						System.out.println("3) Delete Branch");
						String selectBranchFunction = in.nextLine();

						if (selectBranchFunction.equals("1")) {
							BranchControl.displayLibraryBranchesFromDatabase();
							System.out.println("Add a new Library Branch ID ");
							
							int newBranchID = Integer.parseInt(in.nextLine());
							System.out.println("Add a new Branch Name ");
							
							String newBranchName = in.nextLine();
							System.out.println("Add a new branch Address");
							String newBranchAddress = in.nextLine();

							BranchControl.addBranchToLibraryDatabase(newBranchID, newBranchName, newBranchAddress);
						} else if (selectBranchFunction.equals("2")) {
							BranchControl.displayLibraryBranchesFromDatabase();
							System.out.println("Update a Library Branch ID ");
							String branchID = in.nextLine();
							int newBranchID = Integer.parseInt(branchID);
							System.out.println("Update a Branch Name ");
							String newBranchName = in.nextLine();
							System.out.println("Update a branch Address");
							String newBranchAddress = in.nextLine();

							BranchControl.updateLibraryBranchDatabase(newBranchID, newBranchName, newBranchAddress);
						} else if (selectBranchFunction.equals("3")) {
							BranchControl.displayLibraryBranchesFromDatabase();
							System.out.println("You are about to delete a Branch. Enter an ID");
							String branchDeleteIdInput = in.nextLine();
							int deleteBranchId = Integer.parseInt(branchDeleteIdInput);
							BranchControl.deleteBranchFromLibraryDatabase(deleteBranchId);
						} else {
							System.out.println("choose one of the provided options");
							break;
						}
					} else if (tableSelected == 5) {

						while (true) {
							System.out.println("Choose of of the following functions");
							System.out.println("1) Add Borrower");
							System.out.println("2) Update Borrower");
							System.out.println("3) Delete Borrower");
							System.out.println("Q) to Previous");
							String selectBorrowerFunction = in.nextLine();
							
							if (selectBorrowerFunction.equals("Q")) {
								break;
							}
							if (selectBorrowerFunction.equals("1")) {
								System.out.println("Add a new Borrower card number ");
								String card = in.nextLine();
								int cardNumber = Integer.parseInt(card);
								
							if (BorrowerControl.borrowerIdExistInBorrowerDatabase(cardNumber)) {
									System.out.println("This card number already exist. Try Update");
									continue;
								}

								System.out.println("Add a new Borrower Name ");
								String borrowerName = in.nextLine();
								System.out.println("Add a new Borrower Address");
								String borrowerAddress = in.nextLine();
								System.out.println("Add a new Borrower Phone");
								String borrowerPhone = in.nextLine();
								BorrowerControl.addBorrowerInBorrowerDataBase(cardNumber, borrowerName, borrowerAddress,
										borrowerPhone);
							} else if (selectBorrowerFunction.equals("2")) {
								System.out.println("Update a Borrower card number ");
								String card = in.nextLine();
								int cardNumber = Integer.parseInt(card);
								if (!BorrowerControl.borrowerIdExistInBorrowerDatabase(cardNumber)) {
									System.out.println("Enter a valid ID Please");
									continue;
								}
								System.out.println("Update a Borrower Name ");
								String borrowerName = in.nextLine();
								System.out.println("Update a Borrower Address");
								String borrowerAddress = in.nextLine();
								System.out.println("Update a Borrower Phone");
								String borrowerPhone = in.nextLine();
								BorrowerControl.UpdateBorrowerInBorrowerDataBase(cardNumber, borrowerName, borrowerAddress,
										borrowerPhone);
							} else if (selectBorrowerFunction.equals("3")) {
								BookLoansControl.displayBorrowersFromDatabase();
								System.out.println("You are about to delete a Borrower. Enter an ID");
								String borrowerDeleteIdInput = in.nextLine();
								int deleteBorrowerId = Integer.parseInt(borrowerDeleteIdInput);
								if (BorrowerControl.borrowerIdExistInBorrowerDatabase(deleteBorrowerId)
										&& !BookLoansControl.borrowerIsInBookLoansDatabase(deleteBorrowerId)) {
									BorrowerControl.deleteBorrowersFromBorrowerDatabase(deleteBorrowerId);
								} else {
									System.out.println("Make sure Borrower doesn't owe a book before deleting");
									continue;
								}

							} else {
								System.out.println("Choose of of the options");
								continue;
							}
						}
					} else if (tableSelected == 6) {

						System.out.println("Enter a card number");
						String cardNumberInput = in.nextLine();
						if (!Library.isNumeric(cardNumberInput)) {
							System.out.println("Enter a number");
							continue;
						}
						int cardNumber = Integer.parseInt(cardNumberInput);
						System.out.println("Enter a Branch ID");
						String branchIDInput = in.nextLine();
						if (!Library.isNumeric(branchIDInput)) {
							System.out.println("Enter a number");
							continue;
						}
						int branchIn = Integer.parseInt(branchIDInput);
						System.out.println("Enter a book ID");
						String bookIDInput = in.nextLine();
						if (!Library.isNumeric(bookIDInput)) {
							System.out.println("Enter a number");
							continue;
						}
						int bookID = Integer.parseInt(bookIDInput);
						System.out.println("Enter a DueDate in yyyy-MM-dd format");
						Date newDueDate = Date.valueOf(in.nextLine());
						BookLoansControl.overrideDueDateInBookLoansDueDateDatabase(cardNumber, bookID, branchIn, newDueDate);
					}

				} else if (category.equals("3")) {
					
					System.out.println("Enter your card number");
					String cardNumberInput = in.nextLine();
					if (!Library.isNumeric(cardNumberInput)
							|| (!Library.stringInput(cardNumberInput))) {
						System.out.println("Enter a number");
						continue;
					}
					int cardNumber = Integer.parseInt(cardNumberInput);
					if (!BorrowerControl.retrieveCardNumberExistInDataBase(cardNumber)) {
						System.out.println("Invalid Library Card");
						continue;
					}

					System.out.println("Choose one of these options");
					System.out.println("1) Check out a book");
					System.out.println("2) return a book");
					System.out.println("3) return to previous");
					String option = in.nextLine();
					if (option.equals("1")) {
						System.out.println("Choose the branch you want to borrow from");
						BranchControl.displayLibraryBranchesFromDatabase();
						String branchIdInput = in.nextLine();
						if (!Library.isNumeric(branchIdInput)) {
							System.out.println("Enter a number");
							continue;
						}
						int branchInput = Integer.parseInt(branchIdInput);
						System.out.println("Choose the book ID you want to borrow");
						BookController.displayBooksFromDatabase();
						String bookIdInput = in.nextLine();
						
						if (!Library.isNumeric(bookIdInput)) {
							System.out.println("Enter a number");
							continue;
						}
						int bookIdIn = Integer.parseInt(bookIdInput);
						if (BookLoansControl.bookIsLoanedFromBranchDatabase(bookIdIn, branchInput, cardNumber)) {
							System.out.println("You have already borrowed this from this Library.");
							continue;
						}
						BookLoansControl.addToBookLoansDatabase(branchInput, bookIdIn, cardNumber);
						BookCopiesControl.reduceBookCopiesFromBranchDatabase(branchInput, bookIdIn);
					} else if (option.equals("2")) {

						System.out.println("Enter a branchID you would like to return");
						BranchControl.displayLibraryBranchesFromDatabase();
						String branchIdInput = in.nextLine();
						if (!Library.isNumeric(branchIdInput)) {
							System.out.println("Enter a number");
							continue;
						}
						int branchInput = Integer.parseInt(branchIdInput);
						System.out.println("Choose the book ID you want to borrow");
						BookController.displayBooksFromDatabase();
						String bookIdInput = in.nextLine();
						int bookIdIn = Integer.parseInt(bookIdInput);
						if (!Library.isNumeric(bookIdInput)) {
							System.out.println("Enter a number");
							continue;
						}
						if (!BookLoansControl.bookIsLoanedFromBranchDatabase(bookIdIn, branchInput, cardNumber)) {
							System.out.println("This book wasn't borrowed from the location");
							continue;
						}
						BookCopiesControl.feedBookToBranchDatabase(bookIdIn, 1, branchInput);
						BookLoansControl.deleteBorrowersFromBookLoansDatabase(bookIdIn, branchInput, cardNumber);
						break;
					} else if (option.equals("3")) {
						break;
					} else {
						System.out.println("choose one of the provided options");
						continue;
					}
					// }

				} else {
					System.out.println("choose one of the provided options");
					break;
				}

			}
		}
	}
}