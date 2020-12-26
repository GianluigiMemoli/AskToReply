use asktoreply;
DELIMITER $$
CREATE PROCEDURE DisattivaAccount(_idUtente VARCHAR(256))
BEGIN 
	UPDATE Utenti
	SET isDisattivato=1
	WHERE id=_idUtente;
END;
DELIMITER;