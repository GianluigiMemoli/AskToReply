use asktoreply; 

DELIMITER $$
CREATE PROCEDURE 
(
    idRuolo integer
)
    BEGIN			
       SELECT * FROM Ruoli WHERE Ruoli.id = idRuolo; 

    END $$
