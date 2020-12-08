package Exceptions;

public class CampiNonConformiException extends Exception{
		public CampiNonConformiException() {
			super("Dati inseriti non validi"); 
		}
		
		
		public CampiNonConformiException(String msg) {
			super(msg);
		}
		
}
