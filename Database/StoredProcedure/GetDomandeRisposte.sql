use asktoreply;
DELIMITER $$
CREATE PROCEDURE GetDomandeRisposte(_idUtente VARCHAR(256))
BEGIN 
    SELECT * FROM domande JOIN risposte ON risposte.idDomanda = domande.id WHERE risposte.idAutore = _idUtente AND Risposte.isNascosta=0 AND domande.isNascosta = 0; 
END $$
DELIMITER ;