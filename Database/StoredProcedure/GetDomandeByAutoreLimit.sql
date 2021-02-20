use asktoreply;

drop procedure if exists GetDomandaByAutoreLimit;

delimiter &&

create procedure GetDomandeByAutoreLimit(
	in idAutore varchar(256),
	in s integer,
	in e integer
)
begin
	select *
	from domande d
	where d.idAutore = idAutore
	order by d.dataPubblicazione desc
	limit s, e;
end && 

delimiter ;