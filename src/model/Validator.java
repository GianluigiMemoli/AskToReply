package model;

import java.util.regex.Pattern;

public class Validator {
	final static int MIN_PASSWORD_LENGTH = 6;
	final static int MAX_PASSWORD_LENGTH = 32;
	
	final static int MIN_USERNAME_LENGTH = 3;
	final static int MAX_USERNAME_LENGTH = 10;
	
	final static int MIN_NOME_LENGTH = 2;
	
	final static int MIN_COGNOME_LENGTH = 2;
	
	private static boolean hasOnlyLettersApostropheAndSpaces(String text) {						
		return Pattern.matches("([A-Za-z']+\\s*)+", text);
	}
	
	private static boolean isAnEmail(String text) {
		return Pattern.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", text);		
	}

	 
	private static boolean isPasswordValid(String password) {
				
		return 	password.length() <= MAX_PASSWORD_LENGTH  
				&&
				password.length() >= MIN_PASSWORD_LENGTH;
	}
	
	
 
	private static boolean isUsernameValid(String username) {
		int usernameLength = username.length(); 
		if((usernameLength < MIN_USERNAME_LENGTH && usernameLength > MAX_USERNAME_LENGTH))
			return false;
		
		
		return Pattern.matches("^[a-zA-Z]+[a-zA-Z0-9]*", username);
	}
	
	public static boolean validateRegistationFields(
			String nome, 
			String cognome, 
			String email, 
			String username, 
			String password
			){
		
		if (nome.length() < MIN_NOME_LENGTH || cognome.length() < MIN_COGNOME_LENGTH)
			return false;
		
		return hasOnlyLettersApostropheAndSpaces(nome) &&
			   hasOnlyLettersApostropheAndSpaces(cognome) && 
			   isAnEmail(email) && 
			   isUsernameValid(username) && 
			   isPasswordValid(password)
			   ;
		
	}
	
	public static boolean validateUpdateProfileFields(String nome, String cognome, String username, String email) {
		return 
			   hasOnlyLettersApostropheAndSpaces(nome) &&
			   hasOnlyLettersApostropheAndSpaces(cognome) && 
			   isAnEmail(email) && 
			   isUsernameValid(username); 				   
	}
	
}
