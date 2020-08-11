/*
    ... = parametri omessi per rapidità
*/

/*
    CreazioneUtente(...)
    RimozioneUtente(idUtente)

    Per la registrazione/l'eliminazione di un utente normale o di un moderatore.

*/

/*
    GetUtenteByEmail(email)
    
    Per l'accesso.
*/

/*
    SetNuovaPassword(nuovaPassword)

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
    GetDomande(idUtente)
    GetRiposte(idUtente)

    Per lo storico domande/risposte.
*/

/*
    PubblicazioneDomanda(...)
    PubblicazioneRisposta(...)

    Per la pubblicazione di una domanda/risposta.
*/

/*
    SegnalazioneDomanda(idDomanda, idMotivazione, commento)
    SegnalazioneRisposta(idRisposta, idMotivazione, commmento)

    Per la segnalazione di una domanda/risposta.
*/

/*
    RicercaDomande(...)

    Per la ricerca con e/o senza filtri.
*/

/*
    RimozioneDomanda(idDomanda)
    RimozioneRisposta(idRisposta)
*/

/*
    VotazioneRisposta(idRisposta, valore)
    RimozioneVotazioneRisposta(idRisposta)
*/

/*
    RisolviSegnalazioneDomanda(idSegnalazione, stato)
    RisolviSegnalazioneRisposta(idSegnalazione, stato)

    stato = risolta, declinata, in attesa.
*/

/*
    GetSegnalazioni()
*/

/*
    GetDomandePertinenti(idUtente)

    Per le domande con categorie presenti negli interessi dell'utente.
*/

/*
    DisattivaAccount(idUtente)
*/

/*
    UpdateCategorieDomanda(idDomanda, cetegorie)

    Aggiorna le categorie di una domanda. Serve per la risoluzione di una segnalazione di domanda offtopic.
*/

