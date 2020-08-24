package Exceptions;

public class UsernamePresenteException extends Exception{
	public UsernamePresenteException() {
		super("Username già presente nel database");
	}
}
