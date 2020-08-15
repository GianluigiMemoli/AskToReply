DELIMITER $$
CREATE PROCEDURE AddCategoriaDomanda(_idDomanda VARCHAR(256), idCategoria VARCHAR(256))
BEGIN 
	INSERT INTO CategorieDomande (idDomanda, idCategoria) VALUES (_idDomanda, _idCategoria);
END;
DELIMITER;