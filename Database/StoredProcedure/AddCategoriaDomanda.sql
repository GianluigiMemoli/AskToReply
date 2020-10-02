use asktoreply;
DELIMITER $$
CREATE PROCEDURE AddCategoriaDomanda(_idDomanda VARCHAR(256), _idCategoria VARCHAR(256))
BEGIN 
	INSERT INTO CategorieDomande (idDomanda, idCategoria) VALUES (_idDomanda, _idCategoria);
END;
DELIMITER;