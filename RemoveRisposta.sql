DELIMITER $$
CREATE PROCEDURE RemoveRisposta(_idRisposta VARCHAR(256))
BEGIN 
SELECT Risposte.idDomanda INTO @domanda
FROM Risposte
WHERE Risposte.id=_idRisposta;
CALL IsArchiviata(@domanda, @output);
SELECT @output;
IF @output = 0 THEN
	DELETE FROM Risposte WHERE id=_idRisposta;
END IF;
END;
DELIMITER;