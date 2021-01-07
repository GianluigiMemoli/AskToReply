use asktoreply;

delimiter $$


create procedure RimozioneVotazione(
	in idR varchar(256),
    in idU varchar(256)
)

begin
	delete from Votazioni where idR = Votazioni.idRisposta and idU = Votazioni.idUtente;
end $$

delimiter ;