use asktoreply;

drop procedure if exists HasVoted;

delimiter $$

create procedure hasVoted(
	idUtente varchar(256),
    idRisposta varchar(256)
)

begin
	select idUtente, idRisposta, valore from votazioni where idUtente = idUtente and idRisposta = idRisposta;
end $$

delimiter ;

