use asktoreply; 

DELIMITER $$
CREATE PROCEDURE GetRuoloByName
(
    ruoloName VARCHAR(255)
)
    BEGIN			
       SELECT * FROM Ruoli WHERE Ruoli.nome = ruoloName; 

    END $$
