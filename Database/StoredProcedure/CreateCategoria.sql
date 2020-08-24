use asktoreply; 

DELIMITER $$
CREATE PROCEDURE CreateCategoria(
    nome VARCHAR(256)   
)
    BEGIN					
        SET @id = UUID();
        INSERT INTO Categorie VALUES(@id, nome);     
    END $$