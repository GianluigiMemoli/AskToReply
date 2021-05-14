use asktoreply;

DELIMITER $$

CREATE PROCEDURE RemoveDomanda(_idDomanda VARCHAR(256))

BEGIN

	UPDATE domande SET isNascosta = 1 WHERE id = _idDomanda;

END $$

DELIMITER;