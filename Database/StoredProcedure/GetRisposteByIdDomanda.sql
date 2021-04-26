use asktoreply; 

DELIMITER $$
CREATE PROCEDURE GetRisposteByIdDomanda(
    idDom varchar(256),
    num integer
)
    BEGIN			
        SELECT * FROM Risposte WHERE Risposte.idDomanda = idDom AND Risposte.isNascosta=0 ORDER BY dataPubblicazione DESC LIMIT 4 offset num; 
    END $$
