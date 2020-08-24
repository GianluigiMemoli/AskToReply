use asktoreply; 

DELIMITER $$
CREATE PROCEDURE GetUtenteByUsername(
    username varchar(256)
)
    BEGIN			
        SELECT * FROM Utenti WHERE Utenti.username = username; 
    END $$
