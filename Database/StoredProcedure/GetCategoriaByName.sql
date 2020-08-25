use asktoreply; 

DELIMITER $$
CREATE PROCEDURE GetCategoriaByName(
    nome VARCHAR(256)   
)
    BEGIN					
        SELECT * from Categorie WHERE Categorie.nome = nome;     
    END $$