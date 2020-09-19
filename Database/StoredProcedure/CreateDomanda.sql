use asktoreply;

delimiter $$

create procedure CreateDomanda (
	in titolo varchar(256),
	in corpo varchar(256),
	in idAutore varchar(256),
    in dataPubblicazione datetime,
	out id varchar(256)
)

begin
	set id = UUID();

	insert into Domande (id, titolo, corpo, idAutore, dataPubblicazione)
    values (id, titolo, corpo, idAutore, dataPubblicazione);

	select id;
end $$

delimiter ;

