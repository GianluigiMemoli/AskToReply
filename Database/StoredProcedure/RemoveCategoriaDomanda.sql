use asktoreply;
DELIMITER $$
CREATE PROCEDURE RemoveCategoriaDomanda(_idDomanda VARCHAR(256), _idCategoria VARCHAR(256))
BEGIN 
	DELETE FROM CategorieDomande WHERE idDomanda=_idDomanda AND idCategoria=_idCategoria;
END $$
DELIMITER ;