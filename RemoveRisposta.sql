DELIMITER $$
CREATE PROCEDURE RemoveRisposta(_idRisposta VARCHAR(256))
BEGIN 
SELECT Risposta.idDomanda AS domanda
FROM Risposta
WHERE Risposta.idRisposta=_idRisposta;
CALL isArchiviata(domanda, @output);
SELECT @output;
IF @output = 0 THEN
	DELETE FROM Risposte WHERE id=_idRisposta;
END IF;
END;
DELIMITER;