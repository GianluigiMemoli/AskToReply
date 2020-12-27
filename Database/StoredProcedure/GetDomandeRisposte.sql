use asktoreply;
DELIMITER $$
CREATE PROCEDURE GetDomandeRisposte(_idUtente VARCHAR(256))
BEGIN 
    SELECT * FROM domande JOIN risposte ON risposte.idDomanda = domande.id WHERE risposte.idAutore = _idUtente; 
END $$
DELIMITER ;