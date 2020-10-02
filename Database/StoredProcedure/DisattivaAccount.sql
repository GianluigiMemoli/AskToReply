use asktoreply;
DELIMITER $$
CREATE PROCEDURE DisattivaAccount(_idUtente VARCHAR(256))
BEGIN 
	UPDATE Utenti
	SET isDisattivato=TRUE
	WHERE id=_idUtente;
END;
DELIMITER;