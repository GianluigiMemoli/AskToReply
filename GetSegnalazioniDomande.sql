DELIMITER $$
CREATE PROCEDURE GetSegnalazioniDomande()
BEGIN
	SELECT Segn.*, SegnDom.idDomanda
    FROM Segnalazioni AS Segn
    INNER JOIN segnalazioniDomanda AS SegnDom
		ON (Segn.id = SegnDom.idSegnalazione)
    WHERE Segn.stato=0
	ORDER BY Segn.dataSegnalazione ASC;
END;
DELIMITER;