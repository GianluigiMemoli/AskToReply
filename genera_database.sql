DROP DATABASE IF EXISTS asktoreply;

CREATE DATABASE asktoreply;

USE asktoreply;

CREATE TABLE Categorie (
    id VARCHAR(256), 
    nome VARCHAR(256) NOT NULL, 
    PRIMARY KEY(id)
);

 CREATE TABLE Ruoli (
     id INTEGER,
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
    ruoloId INTEGER,
    isDisattivato BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (id),
    FOREIGN KEY (ruoloId) REFERENCES Ruoli(id)
 );


 CREATE TABLE Partecipanti (
     idUtente VARCHAR(256),
     punteggio INTEGER DEFAULT 0,clear
     numeroSegnalazioni INTEGER DEFAULT 0,
     PRIMARY KEY(idUtente),
     FOREIGN KEY(idUtente) REFERENCES Utenti(id)
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
    idAllegato VARCHAR(256) DEFAULT NULL,
    idAutore VARCHAR(256) NOT NULL,
    dataPubblicazione DATETIME NOT NULL, 
    isArchiviata bit DEFAULT 0, 
    PRIMARY KEY(id), 
    /*FOREIGN KEY (idAllegato) REFERENCES Allegati(id),*/
    FOREIGN KEY (idAutore) REFERENCES Partecipanti(idUtente)
);

CREATE TABLE CategorieDomande (
    idDomanda VARCHAR(256),
    idCategoria VARCHAR(256),
    PRIMARY KEY(idDomanda, idCategoria),
    FOREIGN KEY (idDomanda) REFERENCES Domande(id),
    FOREIGN KEY (idCategoria) REFERENCES Categorie(id)
);

CREATE TABLE Risposte (
    id VARCHAR(256), 
    idDomanda VARCHAR(256) NOT NULL, 
    corpo VARCHAR(256) NOT NULL, 
    allegati VARCHAR(256), 
    PRIMARY KEY(id)
);

CREATE TABLE Votazioni (
    idUtente VARCHAR(256), 
    idRisposta VARCHAR(256), 
    valore smallint, -- +1  o -1 
    PRIMARY KEY(idUtente, idRisposta), 
    FOREIGN KEY(idUtente) REFERENCES Utenti(id), 
    FOREIGN KEY(idRisposta) REFERENCES Risposte(id)
); 


CREATE TABLE Motivazioni (
    id integer, 
    nome VARCHAR(50) NOT NULL, 
    PRIMARY KEY(id)
);

CREATE TABLE Segnalazioni (
    id  VARCHAR(256),     
    idMotivazione integer,
    dataDegnalazione DATETIME NOT NULL,
    stato   smallint, -- possibile foreign key ?????????????????????
    commento VARCHAR(256) DEFAULT NULL, 
    PRIMARY KEY(id), 
    FOREIGN KEY(idMotivazione) REFERENCES Motivazioni(id)
);

CREATE TABLE SegnalazioniRisposta (
    idSegnalazione  VARCHAR(256),     
    idRisposta  VARCHAR(256),     
    PRIMARY KEY(idSegnalazione, idRisposta),
    FOREIGN KEY(idSegnalazione) REFERENCES Segnalazioni(id),
    FOREIGN KEY(idRisposta) REFERENCES Risposte(id)      
);

CREATE TABLE SegnalazioniDomanda (
    idSegnalazione  VARCHAR(256),     
    idDomanda  VARCHAR(256),     
    PRIMARY KEY(idSegnalazione, idDomanda),
    FOREIGN KEY(idSegnalazione) REFERENCES Segnalazioni(id),
    FOREIGN KEY(idDomanda) REFERENCES Domande(id)       
);

