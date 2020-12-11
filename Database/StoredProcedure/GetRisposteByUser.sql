use asktoreply; 

DELIMITER $$
CREATE PROCEDURE GetRisposteByUser(
    userId varchar(256)
)
    BEGIN			
        SELECT * FROM Risposte WHERE Risposte.idAutore = userId; 
    END $$
