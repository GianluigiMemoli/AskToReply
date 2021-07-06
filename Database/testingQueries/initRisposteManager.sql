use asktoreply;

insert into utenti (
	id, 
	email, 
	passwordHash, 
	username, 
	nome, 
	cognome, 
	ruoloID, 
	isDisattivato
) 
values (
	"id-utente-testing-1", 
	"giuseppe.verdi@mock.com", 
	"passwordhash", 
	"gverde", 
	"Giuseppe", 
	"Verdi", 
	1, 
	0
);

insert into partecipanti 
values (
	"id-utente-testing-1", 
	0, 
	0
);

insert into utenti (
	id, 
	email, 
	passwordHash, 
	username, 
	nome, 
	cognome, 
	ruoloID, 
	isDisattivato
) 
values (
	"id-utente-testing-2", 
	"carlo.bianchi@mock.com", 
	"passwordhash", 
	"cbianchi", 
	"Carlo", 
	"Bianchi", 
	1, 
	0
);

insert into partecipanti 
values (
	"id-utente-testing-2", 
	0, 
	0
);

insert into utenti (
	id, 
	email, 
	passwordHash, 
	username, 
	nome, 
	cognome, 
	ruoloID, 
	isDisattivato
) 
values (
	"id-utente-testing-3", 
	"mario.rossi@mock.com", 
	"passwordhash", 
	"mrossi", 
	"Mario", 
	"Rossi", 
	1, 
	0
);

insert into partecipanti 
values (
	"id-utente-testing-3", 
	0, 
	0
);

insert into utenti (
	id, 
	email, 
	passwordHash, 
	username, 
	nome, 
	cognome, 
	ruoloID, 
	isDisattivato
) 
values (
	"id-utente-testing-4", 
	"luca.neri@mock.com", 
	"passwordhash", 
	"lneri", 
	"Luca", 
	"Neri", 
	1, 
	0
);

insert into partecipanti 
values (
	"id-utente-testing-4", 
	0, 
	0
);

insert into domande
values (
	"id-domanda-testing-1", 
	"Titolo domanda #1", 
	"Corpo domanda #1", 
	"id-utente-testing-1", 
	"2021-07-03", 
	0, 
	0
);

insert into risposte
values (
	"id-risposta-testing-1", 
	"id-domanda-testing-1", 
	"Corpo risposta 1", 
	null, 
	"id-utente-testing-2", 
	"2021-07-02", 
	0
);

insert into risposte
values (
	"id-risposta-testing-2", 
	"id-domanda-testing-1", 
	"Corpo risposta 2", 
	null, 
	"id-utente-testing-3", 
	"2021-07-02", 
	0
);

insert into votazioni (idUtente, idRisposta, valore)
values 
	(
		"id-utente-testing-4", 
		"id-risposta-testing-1",
		1
	), 
	(
		"id-utente-testing-4", 
		"id-risposta-testing-2",
		-1
	);