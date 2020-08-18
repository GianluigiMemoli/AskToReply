use asktoreply; 

DELIMITER $$
CREATE PROCEDURE GetRisposteByUser(
    userId varchar(256)
)
    BEGIN			
        SELECT * FROM Riposte WHERE Risposte.idAutore = userId; 
    END $$
