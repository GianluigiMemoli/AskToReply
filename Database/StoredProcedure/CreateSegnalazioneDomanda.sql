use asktoreply;

delimiter $$

drop procedure if exists CreateSegnalazioneDomanda;

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