package Exceptions;

public class UsernamePresenteException extends Exception{
	public UsernamePresenteException() {
		super("Username gi� presente nel database");
	}
}
