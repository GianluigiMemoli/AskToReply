use asktoreply; 

DELIMITER $$
CREATE PROCEDURE GetCorpoRispostaById(
    rispostaID varchar(256)
)
    BEGIN			
       SELECT corpo FROM Risposte WHERE Risposte.id = rispostaID; 

    END $$