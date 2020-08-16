DELIMITER $$
CREATE PROCEDURE RemoveRisposta(_idRisposta VARCHAR(256))
BEGIN 
SELECT Risposte.idDomanda AS domanda
FROM Risposte
WHERE Risposte.id=_idRisposta;
CALL isArchiviata(domanda, @output);
SELECT @output;
IF @output = 0 THEN
	DELETE FROM Risposte WHERE id=_idRisposta;
END IF;
END;
DELIMITER;