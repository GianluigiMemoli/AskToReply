use asktoreply;
DELIMITER $$
CREATE PROCEDURE GetNumeroRisposteByDomanda(_idDomanda VARCHAR(256))
BEGIN 
	SELECT COUNT(*) FROM risposte WHERE idDomanda = _idDomanda;
END $$
DELIMITER ;