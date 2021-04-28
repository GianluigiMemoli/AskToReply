package Exceptions;

public class ErroreSegnalazioneRispostaException extends Exception{

	public ErroreSegnalazioneRispostaException() {
		super("Errore segnalazione risposta.");
	}
	
	public ErroreSegnalazioneRispostaException(String message) {
		super(message);
	}	
}
