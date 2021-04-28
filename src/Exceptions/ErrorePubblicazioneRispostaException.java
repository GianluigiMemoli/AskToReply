package Exceptions;

// da sostituire con ErrorePubblicazioneException? (per risposta e per domanda)

public class ErrorePubblicazioneRispostaException extends Exception{

	private static final long serialVersionUID = 1L;

	public ErrorePubblicazioneRispostaException() {
		super("Errore pubblicazione risposta.");
	}
	
	public ErrorePubblicazioneRispostaException(String message) {
		super(message);
	}	
}
