use asktoreply; 

DELIMITER $$
CREATE PROCEDURE GetRisposteByUser(
    userId varchar(256),
    num integer
)
    BEGIN			
        SELECT * FROM Risposte WHERE Risposte.idAutore = userId ORDER BY dataPubblicazione DESC LIMIT 4 offset num; 
    END $$
