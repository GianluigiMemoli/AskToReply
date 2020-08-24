package Exceptions;

public class EmailPresenteException extends Exception{
	public EmailPresenteException() {
		super("Email già presente nel database"); 
	}
}
