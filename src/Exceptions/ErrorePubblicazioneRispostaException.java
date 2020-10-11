package Exceptions;

// da sostituire con ErrorePubblicazioneException? (per risposta e per domanda)

public class ErrorePubblicazioneRispostaException extends Exception{

	
	public ErrorePubblicazioneRispostaException() {
		super();
	}
	
	public ErrorePubblicazioneRispostaException(String message) {
		super(message);
	}
	
	private static final long serialVersionUID = -5466547296574670567L;
	
}
