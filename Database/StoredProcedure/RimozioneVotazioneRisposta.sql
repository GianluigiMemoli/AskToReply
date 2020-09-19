use asktoreply;

delimiter $$

drop procedure if exists RimozioneVotazione;

create procedure RimozioneVotazione(
	in idRisposta varchar(256),
    in idUtente varchar(256)
)

begin
	delete from Votazioni where idRisposta = idRisposta and idUtente = idUtente;
end $$

delimiter ;