use asktoreply; 

DELIMITER $$

CREATE PROCEDURE RemovePartecipante(
    id VARCHAR(256)    
)
BEGIN				
	DELETE FROM Interessi WHERE idUtente = id;
	DELETE FROM Partecipanti WHERE idUtente = id; 
	DELETE FROM Utenti u WHERE u.id = id;   
END $$