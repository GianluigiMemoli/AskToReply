USE asktoreply; 
DELIMITER $$

CREATE PROCEDURE CreateUtente(
    email varchar(256),
    passwordHash varchar(256),
    username varchar(30), 
    cognome varchar(50), 
    nome varchar(50), 
    ruoloId integer, 
    userId varchar(256) OUT 
    )
    BEGIN 
        SET userId = UUID();         
        INSERT INTO Utenti (id, email, passwordHash, username, nome, cognome, ruoloId)
        VALUES (userId, email, passwordHash, username, nome, cognome, ruoloId);
        SELECT userId; 
    END $$
