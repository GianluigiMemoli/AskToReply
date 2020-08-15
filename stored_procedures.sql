/*
    ... = parametri omessi per rapidità
*/


/* [ GIGIO ] */

/*
    
    CreateUtente(...) 
    
    RemoveUtente(idUtente)
    
    Per la registrazione/l'eliminazione di un utente normale o di un moderatore.
*/


/*
    GetUtenteByEmail(email)
    
    Per l'accesso.
*/

/*
    ChangePassword(nuovaPassword)

    Per il recupero password.
*/

/*
    GetTendenze()

    Per le tendenze.
*/

/*
    UpdateUtente(...)

    Per la modifica del profilo.
*/

/*
    GetDomandeByUserId(idUtente)
    GetRiposteByUserId(idUtente)

    Per lo storico domande/risposte.
*/




/* [ DIBENEDETTO ] */

/*
    CreateDomanda(...)
    CreateRisposta(...)

    Per la pubblicazione di una domanda/risposta.
*/

/*
    CreateSegnalazioneDomanda(idDomanda, idMotivazione, commento)
    CreateSegnalazioneRisposta(idRisposta, idMotivazione, commmento)

    Per la segnalazione di una domanda/risposta.
*/

/*
    VotazioneRisposta(idRisposta, valore)
    RemoveVotazioneRisposta(idRisposta)
*/



/*[ TUTTI ] */

/*
    RicercaDomande(...)

    Per la ricerca con e/o senza filtri.
*/



/* [ BELLOGRADO ] */


/*
	• RisolviSegnalazione(idSegnalazione, stato)
    invece di 	->	RisolviSegnalazioneDomanda(idSegnalazione, stato)
					RisolviSegnalazioneRisposta(idSegnalazione, stato)
*/

/*
#   • GetSegnalazioniDomande()
#	• GetSegnalazioniRisposte()
    invece di	->	GetSegnalazioni()
*/

/*
	• GetDomandeDiInteresseByUser(idUtente)
    invece di 	->	GetDomandeByCategoria(idUtente)
*/


/*
    • RemoveDomanda(idDomanda)
#   • RemoveRisposta(idRisposta)
*/

/*
    • DisattivaAccount(idUtente)
*/

/*
	• AddCategoriaDomanda(idDomanda, idCategoria)
    • RemoveCategoriaDomanda(idDomanda, idCategoria)
    invece di	->	UpdateCategorieDomanda(idDomanda, categorie)
*/