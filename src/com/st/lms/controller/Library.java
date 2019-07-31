package com.st.lms.controller;
public class Library {

	public static boolean isNumeric(String string) {
		try {
			Double.parseDouble(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean stringInput(String string) {
		if (string == null) {
			return false;
		} else {
			return true;
		}
	}

	

	

	

	





	

	





	

	

	

	

	



	









	

	



	

	



	
}