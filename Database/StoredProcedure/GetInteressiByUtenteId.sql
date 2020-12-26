use asktoreply;
DELIMITER $$
CREATE PROCEDURE GetInteressiByUtente(
    id varchar(256)
)
BEGIN
    SELECT idCategoria, Categorie.nome FROM asktoreply.interessi as interesse
    JOIN Categorie ON Categorie.id = interesse.idCategoria
    WHERE interesse.idUtente = id;
END $$