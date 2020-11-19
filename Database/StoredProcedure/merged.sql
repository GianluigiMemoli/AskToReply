use asktoreply;
DELIMITER $$
CREATE PROCEDURE AddCategoriaDomanda(_idDomanda VARCHAR(256), _idCategoria VARCHAR(256))
BEGIN 
	INSERT INTO CategorieDomande (idDomanda, idCategoria) VALUES (_idDomanda, _idCategoria);
END;
DELIMITER;
use asktoreply; 

DELIMITER $$
CREATE PROCEDURE AddInteresseUtente(
    email VARCHAR(256), 
    nomeCategoria VARCHAR(256)
)
    BEGIN			
		SELECT id INTO @userId FROM Utenti WHERE Utenti.email = email;        
		SELECT id INTO @categoriaId FROM Categorie WHERE Categorie.nome = nomeCategoria;                
        INSERT INTO Interessi VALUES(@userId, @categoriaId);     
    END $$
use asktoreply; 

DELIMITER $$
CREATE PROCEDURE ChangePassword(
    userId varchar(256), 
    newPasswordHash varchar(256)
)
    BEGIN			
        UPDATE Utenti SET passwordHash = newPasswordHash WHERE Utenti.id = userId ;
    END $$

use asktoreply; 

DELIMITER $$
CREATE PROCEDURE CreateCategoria(
    nome VARCHAR(256)   
)
    BEGIN					
        SET @id = UUID();
        INSERT INTO Categorie VALUES(@id, nome);     
    END $$
use asktoreply;

delimiter $$

create procedure CreateDomanda (
	in titolo varchar(256),
	in corpo varchar(256),
	in idAutore varchar(256),
    in dataPubblicazione datetime,
	out id varchar(256)
)

begin
	set id = UUID();

	insert into Domande (id, titolo, corpo, idAutore, dataPubblicazione)
    values (id, titolo, corpo, idAutore, dataPubblicazione);

	select id;
end $$

delimiter ;


use asktoreply;

delimiter $$

create procedure CreateRisposta( 
    idDomanda varchar(256), 
    corpo varchar(256),
    idAutore varchar(256),
    out id varchar(256)
)

begin
	set id = UUID();
    
    insert into Risposte (id, idDomanda, corpo, idAutore)
    values (id, idDomanda, corpo, idAutore);
    
    select id;
end $$

delimiter ;
use asktoreply

delimiter $$



create procedure CreateSegnalazione(
	in idMotivazione integer,
    in dataSegnalazione datetime,
    in stato integer,
    in commento varchar(256),
    out id varchar(256)
)

begin
	set id = UUID();
    
    insert into Segnalazioni(id, idMotivazione, dataSegnalazione, stato, commento)
    values (id, idMotivazione, dataSegnalazione, stato, commento);
    
    select @id;
end $$

delimiter ;
use asktoreply;

delimiter $$



create procedure CreateSegnalazioneDomanda(
	in idMotivazione integer,
    in dataSegnalazione datetime,
    in stato integer,
    in commento varchar(256),
    in idDomanda varchar(256)
)

begin
	
	call CreateSegnalazione(idMotivazione, dataSegnalazione, stato, commento, @idSegnalazione);
	
    insert into SegnalazioniDomanda(idSegnalazione, idDomanda)
    values (@idSegnalazione, idDomanda);
    
end $$

delimiter ;
use asktoreply;

delimiter $$



create procedure CreateSegnalazioneRisposta(
	in idMotivazione integer,
    in dataSegnalazione datetime,
    in stato integer,
    in commento varchar(256),
	in idRisposta varchar(256)
)

begin
	
	call CreateSegnalazione(idMotivazione, dataSegnalazione, stato, commento, @idSegnalazione);
	
    insert into SegnalazioniRisposta(idSegnalazione, idRisposta)
    values (@idSegnalazione, idRisposta);
    
end $$

delimiter ;
USE asktoreply; 
DELIMITER $$

CREATE PROCEDURE CreateUtente(
    email varchar(256),
    passwordHash varchar(256),
    username varchar(30), 
    cognome varchar(50), 
    nome varchar(50), 
    ruoloId integer, 
    OUT userId varchar(256) 
    )
    BEGIN 
        SET userId = UUID();         
        INSERT INTO Utenti (id, email, passwordHash, username, nome, cognome, ruoloId)
        VALUES (userId, UPPER(email), passwordHash, username, nome, cognome, ruoloId);
        SELECT userId; 
    END $$

use asktoreply;
DELIMITER $$
CREATE PROCEDURE DisattivaAccount(_idUtente VARCHAR(256))
BEGIN 
	UPDATE Utenti
	SET isDisattivato=TRUE
	WHERE id=_idUtente;
END;
DELIMITER;
use asktoreply;



delimiter $$

create procedure GetAllCategorie()
begin
	select id, nome from categorie;
end $$

delimiter ;
use asktoreply; 

DELIMITER $$
CREATE PROCEDURE GetAllInteressiByUserEmail(
    email varchar(256)
)
    BEGIN			
		SELECT id INTO @userId FROM Utenti WHERE Utenti.email = email;   
        SELECT id,nome FROM Categorie JOIN Interessi on Categorie.id = Interessi.idCategoria     ; 
    END $$

use asktoreply;



DELIMITER &&

create procedure GetAllMotivazioni()
begin
	select id, nome from motivazioni;
end &&

DELIMITER ;
use asktoreply; 

DELIMITER $$
CREATE PROCEDURE GetCategoriaByName(
    nome VARCHAR(256)   
)
    BEGIN					
        SELECT * from Categorie WHERE Categorie.nome = nome;     
    END $$
use asktoreply;

delimiter $$



create procedure GetCategorieDomandeByIdDomanda(
	in idDomanda varchar(256)
)
begin
	
	select id, nome
	from categorie
		left join categoriedomande 
		on categorie.id = categoriedomande.idCategoria 
        and categoriedomande.idDomanda = idDomanda;
    
end $$

delimiter ;
use asktoreply; 

DELIMITER $$
CREATE PROCEDURE GetDomandeByUser(
    userId varchar(256)
)
    BEGIN			
        SELECT * FROM Domande WHERE Domande.idAutore = userId; 
    END $$

use asktoreply;
DELIMITER $$
CREATE PROCEDURE GetDomandeDiInteresseByUser(_idUtente VARCHAR(256))
BEGIN
	SELECT *
    FROM Domande AS Dom
    INNER JOIN CategorieDomande AS CaDom
		ON(Dom.id=CaDom.idDomanda)
	INNER JOIN Interessi AS Inter
		ON(CaDom.idCategoria=Inter.idCategoria)
	WHERE Inter.idUtente=_idUtente
    ORDER BY Dom.dataPubblicazione DESC;
END;
DELIMITER;

use asktoreply; 

DELIMITER $$
CREATE PROCEDURE GetPartecipanteByEmail(
    email varchar(256)
)
    BEGIN			
        SELECT * FROM Partecipanti 
        LEFT JOIN Utenti  
        ON Partecipanti.idUtente = Utenti.id
        WHERE Utenti.email = UPPER(email); 

    END $$

use asktoreply; 

DELIMITER $$
CREATE PROCEDURE GetRisposteByUser(
    userId varchar(256)
)
    BEGIN			
        SELECT * FROM Riposte WHERE Risposte.idAutore = userId; 
    END $$

use asktoreply; 

DELIMITER $$
CREATE PROCEDURE GetRuoloById
(
    idRuolo integer
)
    BEGIN			
       SELECT * FROM Ruoli WHERE Ruoli.id = idRuolo; 

    END $$


use asktoreply;

DELIMITER $$
CREATE PROCEDURE GetSegnalazioneDomandaById(_idSegnalazione VARCHAR(256))
BEGIN
	SELECT Segn.*, SegnDom.idDomanda
    FROM Segnalazioni AS Segn
    INNER JOIN segnalazioniDomanda AS SegnDom
		ON (Segn.id = SegnDom.idSegnalazione)
    WHERE Segn.stato=0 AND Segn.id=_idSegnalazione
	ORDER BY Segn.dataSegnalazione ASC;
END;
DELIMITER;
DELIMITER $$
CREATE PROCEDURE GetSegnalazioneRispostaById(_idSegnalazione VARCHAR(256))
BEGIN
	SELECT Segn.*, SegnRis.idRisposta
    FROM Segnalazioni AS Segn
    INNER JOIN segnalazioniRisposta AS SegnRis
		ON (Segn.id = SegnRis.idSegnalazione)
    WHERE Segn.stato=1 AND Segn.id=_idSegnalazione
	ORDER BY Segn.dataSegnalazione ASC;
END;
DELIMITER;
use asktoreply;
DELIMITER $$
CREATE PROCEDURE GetSegnalazioniDomande()
BEGIN
	SELECT Segn.*, SegnDom.idDomanda
    FROM Segnalazioni AS Segn
    INNER JOIN segnalazioniDomanda AS SegnDom
		ON (Segn.id = SegnDom.idSegnalazione)
    WHERE Segn.stato=0
	ORDER BY Segn.dataSegnalazione ASC;
END;
DELIMITER;
use asktoreply;
DELIMITER $$
CREATE PROCEDURE GetSegnalazioniRisposte()
BEGIN
	SELECT Segn.*, SegnRis.idRisposta
    FROM Segnalazioni AS Segn
    INNER JOIN segnalazioniRisposta AS SegnRis
		ON (Segn.id = SegnRis.idSegnalazione)
    WHERE Segn.stato=1
	ORDER BY Segn.dataSegnalazione ASC;
END;
DELIMITER;
use asktoreply; 

DELIMITER $$
CREATE PROCEDURE GetUtenteByEmail(
    email varchar(256)
)
    BEGIN			
        SELECT * FROM Utenti WHERE Utenti.email = UPPER(email); 
    END $$

use asktoreply; 

DELIMITER $$
CREATE PROCEDURE GetUtenteByUsername(
    username varchar(256)
)
    BEGIN			
        SELECT * FROM Utenti WHERE Utenti.username = username; 
    END $$

use asktoreply;
DELIMITER $$
CREATE PROCEDURE IsArchiviata(_idDomanda VARCHAR(256), OUT _isArchiviata BIT)
BEGIN 
    SELECT isArchiviata INTO @isA
    FROM Domande
	WHERE id = _idDomanda;
	SET _isArchiviata = @isA;
END;
DELIMITER;
use asktoreply; 

DELIMITER $$
CREATE PROCEDURE Registrazione(
    email varchar(256),
    passwordHash varchar(256),
    username varchar(30), 
    cognome varchar(50), 
    nome varchar(50)
)
    BEGIN			
		SELECT id INTO @ruoloId FROM Ruoli WHERE Ruoli.nome = 'Partecipante';        
		call asktoreply.CreateUtente(email, passwordHash, username, cognome, nome,  @ruoloId, @userId);      
        select @userId; 
        INSERT INTO Partecipanti VALUES(@userId, 0, 0);     
    END $$
use asktoreply;
DELIMITER $$
CREATE PROCEDURE RemoveCategoriaDomanda(_idDomanda VARCHAR(256), _idCategoria VARCHAR(256))
BEGIN 
	DELETE FROM CategorieDomande WHERE idDomanda=_idDomanda AND idCategoria=_idCategoria;
END;
DELIMITER;
use asktoreply;
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
use asktoreply; 

DELIMITER $$
CREATE PROCEDURE RemoveInteresseUtente(
    email VARCHAR(256), 
    nomeCategoria VARCHAR(256)
)
    BEGIN			
		SELECT id INTO @userId FROM Utenti WHERE Utenti.email = email;        
		SELECT id INTO @categoriaId FROM Categorie WHERE Categorie.nome = nomeCategoria;                
        DELETE FROM Interessi WHERE idUtente = @userId AND idCategoria = @categoriaId ;     
    END $$
use asktoreply; 

DELIMITER $$
CREATE PROCEDURE RemovePartecipante(
    id VARCHAR(256)    
)
    BEGIN				
        DELETE FROM Partecipanti WHERE idUtente = id ;   
        DELETE FROM Interessi WHERE idUtente = id; 
        DELETE FROM Utenti WHERE id = id  ;   
          
    END $$
use asktoreply;
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
use asktoreply;

delimiter $$



create procedure RimozioneVotazione(
	in idRisposta varchar(256),
    in idUtente varchar(256)
)

begin
	delete from Votazioni where idRisposta = idRisposta and idUtente = idUtente;
end $$

delimiter ;
use asktoreply;
DELIMITER $$
CREATE PROCEDURE RisolviSegnalazione(_idSegnalazione VARCHAR(256), _stato INTEGER)
BEGIN
	UPDATE Segnalazioni SET stato=_stato
    WHERE idSegnalazione=_idSegnalazione;
END;
DELIMITER;

USE asktoreply; 
DELIMITER $$

CREATE PROCEDURE UpdateUtente(
    email varchar(256),    
    username varchar(30), 
    cognome varchar(50), 
    nome varchar(50),     
    userId varchar(256)
    )
    BEGIN 
        
        UPDATE Utenti SET email = UPPER(email), username = username, nome = nome, cognome = cognome
        WHERE id = userId;
        
    END $$

use asktoreply;

delimiter $$



create procedure VotazioneRisposta(
	in idUtente varchar(256),
    in idRisposta varchar(256),
    in valore smallint
)

begin
	insert into votazioni(idUtente, idRisposta, valore) values (idUtente, idRisposta, valore);
end $$

delimiter ;
