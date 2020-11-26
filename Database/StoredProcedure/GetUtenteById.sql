use asktoreply; 

DELIMITER $$
CREATE PROCEDURE GetUtenteById(
    id varchar(256)
)
    BEGIN			
        SELECT * FROM Utenti WHERE Utenti.id = id; 
    END $$
