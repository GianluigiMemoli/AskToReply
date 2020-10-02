use asktoreply; 

DELIMITER $$
CREATE PROCEDURE AddInteresseUtente(
    email VARCHAR(256), 
    nomeCategoria VARCHAR(256)
)
    BEGIN			
		SELECT id INTO @userId FROM Utenti WHERE Utenti.email = email;        
		SELECT id INTO @categoriaId FROM Categorie WHERE Categorie.nome = nomeCategoria;                
        INSERT INTO Interessi VALUES(@userId, @categoriaId);     
    END $$