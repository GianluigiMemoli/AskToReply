use asktoreply; 

DELIMITER $$
CREATE PROCEDURE Registrazione(
    email varchar(256),
    passwordHash varchar(256),
    username varchar(30), 
    cognome varchar(50), 
    nome varchar(50)
)
    BEGIN			
		SELECT id INTO @ruoloId FROM Ruoli WHERE Ruoli.nome = 'Partecipante';        
		call asktoreply.CreateUtente(email, passwordHash, username, cognome, nome,  @ruoloId, @userId);      
        select @userId; 
        INSERT INTO Partecipanti VALUES(@userId, 0, 0);     
    END $$