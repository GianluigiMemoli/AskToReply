DELIMITER $$
CREATE PROCEDURE RemoveDomanda(_idDomanda VARCHAR(256))
BEGIN 
CALL IsArchiviata(_idDomanda, @output);
SELECT @output;
IF @output = 0 THEN
	DELETE FROM Domande WHERE id=_idDomanda;
END IF;
END;
DELIMITER;