package com.st.lms.model;

public class Book {
	private int iD;
	private String title;
	private int authId;
	private int pubId;
	
	public Book() {};
	
	public Book(int iD, String title, int authId, int pubId) {
		super();
		this.iD = iD;
		this.title = title;
		this.authId = authId;
		this.pubId = pubId;
	}

	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getAuthId() {
		return authId;
	}

	public void setAuthId(int authId) {
		this.authId = authId;
	}

	public int getPubId() {
		return pubId;
	}

	public void setPubId(int pubId) {
		this.pubId = pubId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + authId;
		result = prime * result + iD;
		result = prime * result + pubId;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (authId != other.authId)
			return false;
		if (iD != other.iD)
			return false;
		if (pubId != other.pubId)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
	
}
