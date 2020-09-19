use asktoreply;

delimiter $$

drop procedure if exists CreateSegnalazioneRisposta;

create procedure CreateSegnalazioneRisposta(
	in idRisposta varchar(256),
    in idDomanda varchar(256)
)

begin
	
    insert into SegnalazioniRisposta(idSegnalazione, idRisposta)
    values (idSegnalazione, idRisposta);
    
end $$

delimiter ;