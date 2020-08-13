CREATE DATABASE asktoreply;

USE asktoreply;

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

 CREATE TABLE Ruoli (
     id INTEGER,
     nome VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
 );

 CREATE TABLE Interessi (
     idUtente VARCHAR(256),
     idCategoria VARCHAR(256),
     PRIMARY KEY (idUtente, idCategoria),
     FOREIGN KEY idUtente REFERENCES Utenti(id),
     FOREIGN KEY idCategoria REFERENCES Categorie(id)
 );

 CREATE TABLE Partecipanti (
     idUtente VARCHAR(256),
     punteggio INTEGER DEFAULT 0,
     numeroSegnalazioni INTEGER DEFAULT 0,
     PRIMARY KEY(idUtente),
     FOREIGN KEY(idUtente) REFERENCES Utenti(id)
 );

CREATE TABLE categorie (
    id VARCHAR(256), 
    nome VARCHAR(max) NOT NULL, 
    PRIMARY KEY(id)
);

CREATE TABLE domande (
    id VARCHAR(256),
    titolo VARCHAR(256) NOT NULL, 
    corpo VARCHAR(max) NOT NULL, 
    idAllegato VARCHAR(256) DEFAULT NULL,
    idAutore VARCHAR(256) NOT NULL,
    dataPubblicazione DATETIME DEFAULT GETDATE(), 
    isArchiviata bit DEFAULT 0, 
    PRIMARY KEY(id), 
    --FOREIGN KEY (id_allegato) REFERENCES Allegati(id), 
    FOREIGN KEY (idAutore) REFERENCES Partecipanti(idUtente);
);

CREATE TABLE categorieDomande (
    idDomanda VARCHAR(256),
    idCategoria VARCHAR(256),
    PRIMARY KEY(idDomanda, idRisposta),
    FOREIGN KEY idDomanda REFERENCES domande(id),
    FOREIGN KEY idCategoria REFERENCES categorie(id)
);

CREATE TABLE risposte (
    id VARCHAR(256), 
    idDomanda VARCHAR(256) NOT NULL, 
    corpo VARCHAR(max) NOT NULL, 
    --allegati VARCHAR(256), 
    PRIMARY KEY(idRisposta)
);

CREATE TABLE votazioni (
    idUtente VARCHAR(256), 
    idRisposta VARCHAR(256), 
    valore smallint, -- +1  o -1 
    PRIMARY KEY(idUtente, idRisposta), 
    FOREIGN KEY(idUtente) REFERENCES Utenti(id), 
    FOREIGN KEY(idRisposta) REFERENCES Risposte(id)
); 


CREATE TABLE segnalazione (
    id  VARCHAR(256),     
    idMotivazione integer,
    dataDegnalazione DATETIME DEFAULT GETDATE();
    stato   smallint, -- possibile foreign key ?????????????????????
    commento VARCHAR(max) DEFAULT NULL, 
    PRIMARY KEY(id), 
    FOREIGN KEY(idMotivazione) Motivazioni(id)
);

CREATE TABLE segnalazioniRisposta (
    idDegnalazione  VARCHAR(256),     
    idRisposta  VARCHAR(256),     
    PRIMARY KEY(idSegnalazione, idRisposta),
    FOREIGN KEY(idSegnalazione) REFERENCES Segnalazioni(id),
    FOREIGN KEY(idRisposta) REFERENCES Risposte(id)      
);

CREATE TABLE segnalazioniDomanda (
    idSegnalazione  VARCHAR(256),     
    idDomanda  VARCHAR(256),     
    PRIMARY KEY(idDegnalazione, idDomanda),
    FOREIGN KEY(idDegnalazione) REFERENCES Segnalazioni(id),
    FOREIGN KEY(idDomanda) REFERENCES Domande(id)       
);

CREATE TABLE motivazioni (
    id integer, 
    nome VARCHAR(50) NOT NULL, 
    PRIMARY KEY(id)
);