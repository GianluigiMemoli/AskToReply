use asktoreply; 

DELIMITER $$
CREATE PROCEDURE GetUtenteByEmail(
    email varchar(256)
)
    BEGIN			
        SELECT * FROM Utenti WHERE Utenti.email = email; 
    END $$
