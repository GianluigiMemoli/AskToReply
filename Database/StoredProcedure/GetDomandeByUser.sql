use asktoreply; 

DELIMITER $$
CREATE PROCEDURE GetDomandeByUser(
    userId varchar(256)
)
    BEGIN			
        SELECT * FROM Domande WHERE Domande.idAutore = userId; 
    END $$
