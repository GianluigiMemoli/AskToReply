use asktoreply;

delimiter $$

drop procedure if exists CreateSegnalazioneRisposta;

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