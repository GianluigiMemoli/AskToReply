use asktoreply;

drop procedure if exists GetNumeroDomandebyAutore;

delimiter &&

create procedure GetNumeroDomandeByAutore(
	in idAutore varchar(256)
)
begin
	select count(*) as numero_domande
	from domande d
	where d.idAutore = idAutore and
	d.isNascosta = 0;
end &&

delimiter ;