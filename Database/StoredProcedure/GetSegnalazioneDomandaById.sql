use asktoreply;

DELIMITER $$
CREATE PROCEDURE GetSegnalazioneDomandaById(_idSegnalazione VARCHAR(256))
BEGIN
	SELECT Segn.*, SegnDom.idDomanda
    FROM Segnalazioni AS Segn
    INNER JOIN segnalazioniDomanda AS SegnDom
		ON (Segn.id = SegnDom.idSegnalazione)
    WHERE Segn.stato=0 AND Segn.id=_idSegnalazione
	ORDER BY Segn.dataSegnalazione ASC;
END $$
DELIMITER ;