package com.st.lms.controller;

import com.st.lms.dao.PublisherDao;

public class PublisherController {
	PublisherDao Publishers = new PublisherDao();
	
	public  boolean retrievePublisherFromPublisherDatabase(int publisher) {
		return Publishers.retrievePublisherFromPublisherTable(publisher);
	}
	
	public void addPublishersToPublisherDatabase(int publisherId, String newPublisherName,
			String publisherAddress, String publisherPhone) {
		Publishers.addPublisherToPublisherTable(publisherId, newPublisherName, publisherAddress, publisherPhone);
	}
	
	public void deletePublishersFromPublisherDatabase(int deletePublisherId) {
		Publishers.deletePublishersFromPublisherTable(deletePublisherId);
	}

	public void updatePublishersToPublisherDatabase(int publisherId, String newPublisherName,
			String newPublisherAddress, String newPublisherPhone) {
		Publishers.updatePublishersFromPublisherTable(publisherId, newPublisherName, newPublisherAddress,
				newPublisherPhone);
	}
	
	public void displayPublishersFromDatabase() {
		Publishers.displayPublishersFromTable();
	}
}
