use asktoreply; 

DELIMITER $$
CREATE PROCEDURE GetRuoloById
(
    idRuolo integer
)
    BEGIN			
       SELECT * FROM Ruoli WHERE Ruoli.id = idRuolo; 

    END $$
