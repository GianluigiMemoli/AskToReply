use asktoreply; 

DELIMITER $$
CREATE PROCEDURE ChangePassword(
    userId varchar(256), 
    newPasswordHash varchar(256)
)
    BEGIN			
        UPDATE Utenti SET passwordHash = newPasswordHash WHERE Utenti.id = userId ;
    END $$
