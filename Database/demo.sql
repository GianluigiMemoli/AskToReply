-- categorie

insert into categorie values
('id-categoria-demo-1', 'Informatica'),
('id-categoria-demo-2', 'Botanica'),
('id-categoria-demo-3', 'Cucina'),
('id-categoria-demo-4', 'Sport'),
('id-categoria-demo-5', 'Fisica'),
('id-categoria-demo-6', 'Matematica'),
('id-categoria-demo-7', 'Cinema');

-- Il campo 'password' di tutti gli utenti è 'password'

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

insert into interessi values
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

insert into interessi values
('id-categoria-demo-1', 'id-utente-demo-2'),
('id-categoria-demo-6', 'id-utente-demo-2'),
('id-categoria-demo-7', 'id-utente-demo-2');

--

INSERT INTO domande VALUES 
(
	"id-domanda-demo-1", 
	"E' possibile dimostrare che NON viviamo in una simulazione?", 
	"Vorrei sapere se è possibile dimostrare che NON viviamo in una simulazione.", 
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
	"No, non è possibile.", 
	null,
	"id-utente-demo-2", 
	"2021-05-25", 
	0
);