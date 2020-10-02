use asktoreply; 

DELIMITER $$
CREATE PROCEDURE GetPartecipanteByEmail(
    email varchar(256)
)
    BEGIN			
        SELECT * FROM Partecipanti 
        LEFT JOIN Utenti  
        ON Partecipanti.idUtente = Utenti.id
        WHERE Utenti.email = UPPER(email); 

    END $$
