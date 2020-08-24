use asktoreply; 

DELIMITER $$
CREATE PROCEDURE GetAllInteressiByUserEmail(
    email varchar(256)
)
    BEGIN			
		SELECT id INTO @userId FROM Utenti WHERE Utenti.email = email;   
        SELECT id,nome FROM Categorie JOIN Interessi on Categorie.id = Interessi.idCategoria     ; 
    END $$
