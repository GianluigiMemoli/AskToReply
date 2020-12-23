DROP DATABASE IF EXISTS asktoreply;

CREATE DATABASE asktoreply;

USE asktoreply;

CREATE TABLE Categorie (
    id VARCHAR(256), 
    nome VARCHAR(256) NOT NULL, 
    PRIMARY KEY(id)
);

 CREATE TABLE Ruoli (
     id INTEGER AUTO_INCREMENT,
     nome VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
 );

CREATE TABLE Utenti (
    id VARCHAR(256),
    email VARCHAR(320) NOT NULL,
    passwordHash VARCHAR(64) NOT NULL,
    nuovaPassword VARCHAR(64),
    username VARCHAR(30) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    ruoloId INTEGER NOT NULL,
    isDisattivato BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (id),
    FOREIGN KEY (ruoloId) REFERENCES Ruoli(id)
 );

 CREATE TABLE Partecipanti (
     idUtente VARCHAR(256),
     punteggio INTEGER DEFAULT 0,
     numeroSegnalazioni INTEGER DEFAULT 0,
     PRIMARY KEY(idUtente),
     FOREIGN KEY(idUtente) REFERENCES Utenti(id) ON DELETE CASCADE
 );

 CREATE TABLE Interessi (
     idUtente VARCHAR(256),
     idCategoria VARCHAR(256),
     PRIMARY KEY (idUtente, idCategoria),
     FOREIGN KEY (idUtente) REFERENCES Utenti(id),
     FOREIGN KEY (idCategoria) REFERENCES Categorie(id)
 );

CREATE TABLE Domande (
    id VARCHAR(256),
    titolo VARCHAR(256) NOT NULL, 
    corpo VARCHAR(256) NOT NULL,
    idAutore VARCHAR(256) NOT NULL,
    dataPubblicazione DATETIME NOT NULL, 
    isArchiviata bit DEFAULT 0 NOT NULL, 
    PRIMARY KEY (id),
    FOREIGN KEY (idAutore) REFERENCES Partecipanti(idUtente),
    FULLTEXT (titolo, corpo)
);

CREATE TABLE CategorieDomande (
    idDomanda VARCHAR(256),
    idCategoria VARCHAR(256),
    PRIMARY KEY(idDomanda, idCategoria),
    FOREIGN KEY (idDomanda) REFERENCES Domande(id) ON DELETE CASCADE,
    FOREIGN KEY (idCategoria) REFERENCES Categorie(id)
);

CREATE TABLE Risposte (
    id VARCHAR(256), 
    idDomanda VARCHAR(256) NOT NULL, 
    corpo VARCHAR(256) NOT NULL, 
    allegati VARCHAR(256), 
    idAutore VARCHAR(256),
	dataPubblicazione DATETIME NOT NULL, 
	FOREIGN KEY (idDomanda) REFERENCES Domande(id),
    FOREIGN KEY (idAutore) REFERENCES Utenti(id),
    PRIMARY KEY(id)
);

CREATE TABLE Votazioni (
    idUtente VARCHAR(256), 
    idRisposta VARCHAR(256), 
    valore smallint, # +1  o -1 
    PRIMARY KEY(idUtente, idRisposta), 
    FOREIGN KEY(idUtente) REFERENCES Utenti(id), 
    FOREIGN KEY(idRisposta) REFERENCES Risposte(id) ON DELETE CASCADE
); 

CREATE TABLE Motivazioni (
    id integer AUTO_INCREMENT, 
    nome VARCHAR(50) NOT NULL, 
    PRIMARY KEY(id)
);

CREATE TABLE StatoSegnalazione(
    id integer AUTO_INCREMENT, # 1 = da gestire, 2 = approvata, 3 = declinata
    nome VARCHAR(50) NOT NULL, 
    PRIMARY KEY(id)
);

CREATE TABLE Segnalazioni (
    id  VARCHAR(256),     
    idMotivazione integer NOT NULL,
    dataSegnalazione DATETIME NOT NULL,
    stato integer NOT NULL,
    commento VARCHAR(256) DEFAULT NULL, 
    PRIMARY KEY(id), 
    FOREIGN KEY(idMotivazione) REFERENCES Motivazioni(id),
    FOREIGN KEY(stato) REFERENCES StatoSegnalazione(id)
);

CREATE TABLE SegnalazioniRisposta (
    idSegnalazione  VARCHAR(256),     
    idRisposta  VARCHAR(256),     
    PRIMARY KEY(idSegnalazione, idRisposta),
    FOREIGN KEY(idSegnalazione) REFERENCES Segnalazioni(id),
    FOREIGN KEY(idRisposta) REFERENCES Risposte(id) ON DELETE CASCADE
);

CREATE TABLE SegnalazioniDomanda (
    idSegnalazione  VARCHAR(256),     
    idDomanda  VARCHAR(256),     
    PRIMARY KEY(idSegnalazione, idDomanda),
    FOREIGN KEY(idSegnalazione) REFERENCES Segnalazioni(id),
    FOREIGN KEY(idDomanda) REFERENCES Domande(id) ON DELETE CASCADE
);