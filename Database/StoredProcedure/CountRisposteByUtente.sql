use asktoreply; 

DELIMITER $$
CREATE PROCEDURE GetNumeroRisposteByUtente(
    userId varchar(256)
)
    BEGIN			
        SELECT Count(*) as numeroRisposte FROM Risposte WHERE Risposte.idAutore = userId; 
    END $$

