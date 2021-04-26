use asktoreply;
DELIMITER $$
CREATE PROCEDURE GetSegnalazioniRisposte()
BEGIN
    SELECT Segn.*, SegnRis.idRisposta, Risp.*
    FROM Segnalazioni AS Segn
    INNER JOIN segnalazioniRisposta AS SegnRis 
	ON (Segn.id = SegnRis.idSegnalazione)
	INNER JOIN Risposte AS Risp 
	ON (SegnRis.idRisposta = Risp.id)
    WHERE Segn.stato=1 and Risp.isNascosta=0
	ORDER BY Segn.dataSegnalazione ASC;
END $$
DELIMITER ;