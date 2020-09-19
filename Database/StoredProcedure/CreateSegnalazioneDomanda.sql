use asktoreply;

delimiter $$

drop procedure if exists CreateSegnalazioneDomanda;

create procedure CreateSegnalazioneDomanda(
	in idSegnalazione varchar(256),
    in idDomanda varchar(256)
)

begin
	
    insert into SegnalazioniDomanda(idSegnalazione, idDomanda)
    values (idSegnalazione, idDomanda);
    
end $$

delimiter ;