USE asktoreply;

DECLARE hasError integer; 
SET hasError = 0; 

DECLARE handler for sqlexception
SET hasError = 1

SET autocommit = 0; 
START TRANSACTION;  
	INSERT INTO Ruoli(nome)
    VALUES('Partecipante'); 

    INSERT INTO Ruoli(nome)
    VALUES('Moderatore'); 

    INSERT INTO Ruoli(nome)
    VALUES('MasterModeratore'); 
IF hasError = 1
    BEGIN
        ROLLBACK; 
    END
ELSE 
    BEGIN    
        COMMIT;
    END

SET autocommit = 1; 