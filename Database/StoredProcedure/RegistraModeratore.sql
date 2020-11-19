use asktoreply; 

DELIMITER $$
CREATE PROCEDURE RegistraModeratore(
    email varchar(256),
    passwordHash varchar(256),
    username varchar(30), 
    cognome varchar(50), 
    nome varchar(50)
)
    BEGIN			
        select @userId; 
		SELECT id INTO @ruoloId FROM Ruoli WHERE Ruoli.nome = 'Moderatore';        
		call asktoreply.CreateUtente(email, passwordHash, username, cognome, nome,  @ruoloId, @userId);              
    END $$