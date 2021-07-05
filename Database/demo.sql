use asktoreply;
delete from categoriedomande;
delete from segnalazionidomanda;
delete from segnalazionirisposta;
delete from segnalazioni;
delete from statosegnalazione;
delete from votazioni;
delete from risposte;
delete from domande;
delete from interessi;
delete from partecipanti;
delete from utenti;
delete from ruoli;
delete from motivazioni;
delete from categorie;

insert into statosegnalazione values(1, "da gestire"),
									(2, "approvata"),
                                    (3, "declinata");
                                    
insert into motivazioni values(1,"Offensiva"),
							  (2,"Offtopic"),
                              (3,"Spam"),
                              (4,"Contenuti inappropriati"),
                              (5,"Link dannosi"),
                              (6,"Altro");

insert into ruoli values (1,"Partecipante"),
						 (2,"Moderatore"),
                         (3,"MasterModeratore");
# passwordHash è "password"
insert into utenti values ("USER1ID","master@moderatore.com","5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8",null,"mod","Mario","Rossi",3,0);


-- categorie

insert into categorie values
('id-categoria-demo-1', 'Informatica'),
('id-categoria-demo-2', 'Botanica'),
('id-categoria-demo-3', 'Cucina'),
('id-categoria-demo-4', 'Sport'),
('id-categoria-demo-5', 'Fisica'),
('id-categoria-demo-6', 'Matematica'),
('id-categoria-demo-7', 'Cinema'),
('id-categoria-demo-8', 'Storia'),
('id-categoria-demo-9', 'Economia');

-- Il campo 'password' di tutti gli utenti Ã¨ 'password'

--

insert into utenti values 
	(
		"id-moderatore-demo-1",
		"moderatore1@gmail.com",
		"5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8",
		null,
		"cdibenedetto",
		"Carmine",
		"Di Benedetto",
		2,
		false
	),
	(
		"id-moderatore-demo-2",
		"moderatore2@gmail.com",
		"5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8",
		null,
		"vbellogrado",
		"Vincenzo",
		"Bellogrado",
		2,
		false
	),
	(
		"id-moderatore-demo-3",
		"moderatore3@gmail.com",
		"5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8",
		null,
		"gmemoli",
		"Gianluigi",
		"Memoli",
		2,
		false
	);

--

insert into utenti values 
('id-utente-demo-1', 'partecipante1@gmail.com', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', null, 'sommervilleian', 'Ian', 'Sommerville', 1, 0);

insert into partecipanti values
('id-utente-demo-1', 0, 0);

insert into interessi (idCategoria, idUtente) values
('id-categoria-demo-1', 'id-utente-demo-1'),
('id-categoria-demo-5', 'id-utente-demo-1'),
('id-categoria-demo-6', 'id-utente-demo-1');

--

insert into utenti values 
(
	'id-utente-demo-2', 
	'partecipante2@gmail.com', 
	'5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 
	null, 
	'jblandy', 
    'Jim', 
	'Blandy', 
	1, 
	0
);

insert into partecipanti values
('id-utente-demo-2', 0, 0);

insert into interessi (idCategoria, idUtente) values
('id-categoria-demo-1', 'id-utente-demo-2'),
('id-categoria-demo-6', 'id-utente-demo-2'),
('id-categoria-demo-7', 'id-utente-demo-2');

--
insert into utenti values 
(
	'id-utente-demo-3', 
	'partecipante3@gmail.com', 
	'5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 
	null, 
	'jhonny', 
    'John', 
	'Doe', 
	1, 
	0
);

insert into partecipanti values
('id-utente-demo-3', 0, 0);

insert into interessi (idCategoria, idUtente) values
('id-categoria-demo-1', 'id-utente-demo-3'),
('id-categoria-demo-6', 'id-utente-demo-3'),
('id-categoria-demo-7', 'id-utente-demo-3');
--
INSERT INTO domande VALUES 
(
	"id-domanda-demo-1", 
	"E' possibile dimostrare che NON viviamo in una simulazione?", 
	"Vorrei sapere se Ã¨ possibile dimostrare che NON viviamo in una simulazione.", 
	"id-utente-demo-1", 
	"2021-05-24", 
	0, 
	0
);

INSERT INTO categoriedomande VALUES ("id-domanda-demo-1", "id-categoria-demo-5");
INSERT INTO categoriedomande VALUES ("id-domanda-demo-1", "id-categoria-demo-6");

INSERT INTO risposte VALUES 
(
	"id-risposta-demo-1", 
	"id-domanda-demo-1", 
	"No, non Ã¨ possibile.", 
	null,
	"id-utente-demo-2", 
	"2021-05-25", 
	0
);
--
INSERT INTO domande VALUES 
(
	"id-domanda-demo-2", 
	"Cosa significa la parola Tenet dall'omonimo film di Cristopher Nolan?", 
	"Che ruolo ha la parola nel film?", 
	"id-utente-demo-2", 
	"2021-06-24", 
	0, 
	0
);

INSERT INTO categoriedomande VALUES ("id-domanda-demo-2", "id-categoria-demo-7");


INSERT INTO risposte VALUES 
(
	"id-risposta-demo-3", 
	"id-domanda-demo-2", 
	"Viene è tratta da un'iscrizione latina, che recita SATOR, AREPO, TENET, OPERA, ROTAS che è palindroma. Il regista ha usato queste parole per dare i nomi a personaggi e rappresentare situazioni.", 
	null,
	"id-utente-demo-3", 
	"2021-05-25", 
	0
);

INSERT INTO risposte VALUES 
(
	"id-risposta-demo-4", 
	"id-domanda-demo-2", 
	"Non lo so :) ", 
	null,
	"id-utente-demo-1", 
	"2021-05-25", 
	0
);

insert into domande values
("DOM1ID", "Chi ha inventato la lampadina?","C'è chi dice che sia opera di Edison, altri citano Joseph Wilson Swan. Chi dei due?","id-utente-demo-1",CURDATE(),0,0),
("DOM2ID", "Cosa determina il prezzo di una criptovaluta?","Inoltre, perchè alcune al lancio valgono già tanto?","id-utente-demo-2",CURDATE(),0,0),
("DOM3ID", "Quanto tempo ci vuole per raggiungere Marte?","Con le tecnologie attuali quanto tempo è necessario per viaggiare dalla Terra al Pianeta rosso?","id-utente-demo-3",CURDATE(),0,0);
                           
insert into categoriedomande values("DOM1ID","id-categoria-demo-8"),								   
                                   ("DOM2ID","id-categoria-demo-9"),
								   ("DOM2ID","id-categoria-demo-1"),
								   ("DOM3ID","id-categoria-demo-5");

insert into segnalazioni VALUES("id-segnalazione-demo-1", 2, CURDATE(), 1, "Questa domanda è offtopic", "id-utente-demo-3");
insert into segnalazionidomanda values("id-segnalazione-demo-1", "DOM3ID");