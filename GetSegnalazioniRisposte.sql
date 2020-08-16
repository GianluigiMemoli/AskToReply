DELIMITER $$
CREATE PROCEDURE GetSegnalazioniRisposte()
BEGIN
	SELECT *
    FROM Segnalazioni AS Segn
    INNER JOIN segnalazioniRisposta AS SegnRis
		ON (Segn.id = SegnRis.idSegnalazione)
    WHERE Segn.stato=0
	ORDER BY Segn.dataSegnalazione ASC;
END;
DELIMITER;