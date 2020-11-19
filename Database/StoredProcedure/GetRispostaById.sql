use asktoreply; 

DELIMITER $$
CREATE PROCEDURE GetRispostaById(
    rispostaID varchar(256)
)
    BEGIN			
       SELECT * FROM Risposte WHERE Risposte.id = rispostaID; 

    END $$
