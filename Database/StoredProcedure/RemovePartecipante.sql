use asktoreply; 

DELIMITER $$

CREATE PROCEDURE RemovePartecipante(
    _id VARCHAR(256)
)

BEGIN
    DELETE FROM Partecipanti WHERE idUtente = _id ;
    DELETE FROM Interessi WHERE idUtente = _id; 
    DELETE FROM Utenti WHERE id = _id;
END $$