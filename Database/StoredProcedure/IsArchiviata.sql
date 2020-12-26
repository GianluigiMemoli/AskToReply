use asktoreply;
DELIMITER $$
CREATE PROCEDURE IsArchiviata(_idDomanda VARCHAR(256), OUT _isArchiviata BIT)
BEGIN 
    SELECT isArchiviata INTO @isA
    FROM Domande
	WHERE id = _idDomanda;
	SET _isArchiviata = @isA;
END $$
DELIMITER ;