use asktoreply; 

DELIMITER $$
CREATE PROCEDURE RemoveInteresseUtente(
    email VARCHAR(256), 
    nomeCategoria VARCHAR(256)
)
    BEGIN			
		SELECT id INTO @userId FROM Utenti WHERE Utenti.email = email;        
		SELECT id INTO @categoriaId FROM Categorie WHERE Categorie.nome = nomeCategoria;                
        DELETE FROM Interessi WHERE idUtente = @userId AND idCategoria = @categoriaId ;     
    END $$