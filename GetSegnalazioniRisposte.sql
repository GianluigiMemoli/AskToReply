DELIMITER $$
CREATE PROCEDURE GetSegnalazioniRisposte()
BEGIN
	SELECT Segn.*, SegnRis.idRisposta
    FROM Segnalazioni AS Segn
    INNER JOIN segnalazioniRisposta AS SegnRis
		ON (Segn.id = SegnRis.idSegnalazione)
    WHERE Segn.stato=1
	ORDER BY Segn.dataSegnalazione ASC;
END;
DELIMITER;