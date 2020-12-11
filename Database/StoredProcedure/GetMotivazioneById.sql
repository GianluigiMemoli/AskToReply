use asktoreply; 

DELIMITER $$
CREATE PROCEDURE GetMotivazioneById(
    motivazioneId varchar(256)
)
    BEGIN			
       SELECT nome FROM Motivazioni WHERE Motivazioni.id = motivazioneId; 

    END $$