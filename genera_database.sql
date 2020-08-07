CREATE DATABASE asktoreply;

USE asktoreply;

CREATE TABLE utenti (
    id VARCHAR(256),
    email VARCHAR(320) NOT NULL,
    password_hash VARCHAR(64) NOT NULL,
    nuova_password VARCHAR(64),
    username VARCHAR(30) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    ruolo_id INTEGER,
    PRIMARY KEY (id),
    FOREIGN KEY (ruolo_id) REFERENCES ruoli(id)
 );

 CREATE TABLE ruoli (
     id INTEGER,
     nome VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
 );

 CREATE TABLE partecipanti (
     id_utente VARCHAR(256),
     punteggio INTEGER DEFAULT 0,
     numero_segnalazioni INTEGER DEFAULT 0,
     PRIMARY KEY(id_utente),
     FOREIGN KEY(id_utente) REFERENCES utenti(id)
 );

CREATE TABLE Categoria(
    id VARCHAR(256), 
    nome VARCHAR(max) NOT NULL, 
    PRIMARY KEY(id)
);

CREATE TABLE Domande(
    id VARCHAR(256),
    titolo VARCHAR(256) NOT NULL, 
    corpo VARCHAR(max) NOT NULL, 
    id_allegato VARCHAR(256) DEFAULT NULL, 
    id_categoria VARCHAR(256) NOT NULL, 
    id_autore VARCHAR(256) NOT NULL,
    dataPubblicazione DATETIME DEFAULT GETDATE(), 
    isArchiviata bit DEFAULT 0, 
    PRIMARY KEY(id), 
    --FOREIGN KEY (id_allegato) REFERENCES Allegati(id), 
    FOREIGN KEY (id_categoria) REFERENCES Categoria(id), 
    FOREIGN KEY (id_autore) REFERENCES partecipanti(id_utente);
);


CREATE TABLE Risposte(
    id VARCHAR(256), 
    id_domanda VARCHAR(256) NOT NULL, 
    corpo VARCHAR(max) NOT NULL, 
    --allegati VARCHAR(256), 
    PRIMARY KEY(id_risposta)
);


CREATE TABLE Votazioni(
    id_utente VARCHAR(256), 
    id_risposta VARCHAR(256), 
    valore smallint, -- +1  o -1 
    PRIMARY KEY(id_utente, id_risposta), 
    FOREIGN KEY(id_utente) REFERENCES utenti(id), 
    FOREIGN KEY(id_risposta) REFERENCES Risposte(id)
); 


CREATE TABLE Segnalazione(
    id  VARCHAR(256),     
    id_motivazione integer, 
    stato   smallint, -- possibile foreign key ?????????????????????
    commento VARCHAR(max) DEFAULT NULL, 
    PRIMARY KEY(id), 
    FOREIGN KEY(id_motivazione) Motivazioni(id)
);


CREATE TABLE SegnalazioniRisposta(
    id_segnalazione  VARCHAR(256),     
    id_risposta  VARCHAR(256),     
    PRIMARY KEY(id_segnalazione, id_risposta)         
);


CREATE TABLE SegnalazioniDomanda(
    id_segnalazione  VARCHAR(256),     
    id_domanda  VARCHAR(256),     
    PRIMARY KEY(id_segnalazione, id_domanda)         
);


CREATE TABLE Motivazioni(
    id integer, 
    nome VARCHAR(50) NOT NULL, 
    PRIMARY KEY(id)
);


