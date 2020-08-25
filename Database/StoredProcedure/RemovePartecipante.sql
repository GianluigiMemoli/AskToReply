use asktoreply; 

DELIMITER $$
CREATE PROCEDURE RemovePartecipante(
    id VARCHAR(256)    
)
    BEGIN				
        DELETE FROM Partecipanti WHERE idUtente = id ;   
        DELETE FROM Utenti WHERE id = id  ;   
          
    END $$