use asktoreply; 

DELIMITER $$
CREATE PROCEDURE AddInteresseUtente(
    email VARCHAR(256), 
    nomeCategoria VARCHAR(256)
)
    BEGIN			
		SELECT id INTO @ruoloId FROM Ruoli WHERE Ruoli.nome = 'Partecipante';        
		call asktoreply.CreateUtente(email, passwordHash, username, cognome, nome,  @ruoloId, @userId);      
        select @userId; 
        INSERT INTO Partecipanti VALUES(@userId, 0, 0);     
    END $$