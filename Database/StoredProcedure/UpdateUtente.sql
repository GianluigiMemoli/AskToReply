USE asktoreply; 
DELIMITER $$

CREATE PROCEDURE UpdateUtente(
    email varchar(256),    
    username varchar(30), 
    cognome varchar(50), 
    nome varchar(50),     
    userId varchar(256),
    password varchar(256)
    )
    BEGIN 
        
        UPDATE Utenti SET email = UPPER(email), username = username, nome = nome, cognome = cognome, password = password
        WHERE id = userId;
        
    END $$
