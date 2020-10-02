use asktoreply;
DELIMITER $$
CREATE PROCEDURE RisolviSegnalazione(_idSegnalazione VARCHAR(256), _stato INTEGER)
BEGIN
	UPDATE Segnalazioni SET stato=_stato
    WHERE idSegnalazione=_idSegnalazione;
END;
DELIMITER;
