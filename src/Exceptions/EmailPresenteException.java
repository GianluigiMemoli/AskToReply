package Exceptions;

public class EmailPresenteException extends Exception{
	public EmailPresenteException() {
		super("Email gi� presente nel database"); 
	}
}
