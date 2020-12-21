use asktoreply;

delimiter $$

drop procedure if exists CreateSegnalazioneRisposta;

create procedure CreateSegnalazioneRisposta(
    in idSegn varchar(256),
	in idRisp varchar(256)
)

begin

    insert into SegnalazioniRisposta(idSegnalazione, idRisposta)
    values (idSegn, idRisp);
    
end $$

delimiter ;