DELIMITER $$
CREATE PROCEDURE GetSegnalazioneRispostaById(_idSegnalazione VARCHAR(256))
BEGIN
	SELECT Segn.*, SegnRis.idRisposta
    FROM Segnalazioni AS Segn
    INNER JOIN segnalazioniRisposta AS SegnRis
		ON (Segn.id = SegnRis.idSegnalazione)
    WHERE Segn.stato=1 AND Segn.id=_idSegnalazione
	ORDER BY Segn.dataSegnalazione ASC;
END;
DELIMITER;