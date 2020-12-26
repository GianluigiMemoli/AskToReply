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