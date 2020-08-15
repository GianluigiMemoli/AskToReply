DELIMITER $$
CREATE PROCEDURE IsArchiviata(_idDomanda VARCHAR(256), OUT _isArchiviata BIT)
BEGIN 
    SELECT isArchiviata
    FROM Domande
	WHERE id = _idDomanda;
	SET _isArchiviata = isArchiviata;
END;
DELIMITER;