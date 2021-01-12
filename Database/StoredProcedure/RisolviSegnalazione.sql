use asktoreply;
DELIMITER $$
CREATE PROCEDURE RisolviSegnalazione(idSegnalazione VARCHAR(256), _stato INTEGER)
BEGIN
	UPDATE Segnalazioni SET stato=_stato
    WHERE id=idSegnalazione;
END $$
DELIMITER ;