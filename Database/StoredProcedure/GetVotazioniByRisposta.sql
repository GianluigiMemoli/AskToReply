use asktoreply; 

DELIMITER $$
CREATE PROCEDURE GetVotazioniByRisposta(
    idRisp varchar(256)
)
    BEGIN			
        SELECT * FROM votazioni WHERE votazioni.idRisposta = idRisp; 
    END $$
