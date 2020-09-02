package model;

import java.sql.CallableStatement;
import java.sql.SQLException;

// DA COMPLETARE

public class SegnalazioneRispostaDAO {

	public static void addSegnalazioneRisposta(SegnalazioneRispostaBean segnalazione) {
		//Utilizzo della S.P. [CreateSegnalazioneRisposta(idRisposta, idMotivazione, commento)] { Di Benedetto }
		
		/* Bisogna aggiungere a SegnalazioneRispostaBean i dati contenuti in 'Segnalazioni' (nel DataBase) ...credo */
		// in base a quanto detto sopra, il codice sottostante è incompleto.
		
		DBManager dbManager = DBManager.getInstance();
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("CreateSegnalazioneRisposta", 2);
			callProcedure.setString(1, segnalazione.getIdRisposta());
			callProcedure.setString(2, segnalazione.getIdSegnalazione());
			callProcedure.executeUpdate();
		}catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void getSegnalazioneRisposta() {
		//Utilizzo della S.P. [GetSegnalazioneRispostaById] { aggiungere S.P. }
		
		
	}
	
}