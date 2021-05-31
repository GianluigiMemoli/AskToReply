use asktoreply;

drop procedure if exists getDomandaById;

delimiter $$

create procedure GetDomandaById(
	in idDomanda varchar(256)
)
begin

	select 
		id, 
        titolo,
        corpo,
        idAutore,
        dataPubblicazione,
        isArchiviata
	from domande
    where id = idDomanda and isNascosta = 0;
		
end $$;

delimiter ;