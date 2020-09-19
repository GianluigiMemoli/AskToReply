use asktoreply

delimiter $$

drop procedure if exists CreateSegnalazione;

create procedure CreateSegnalazione(
	in idMotivazione integer,
    in dataSegnalazione datetime,
    in stato integer,
    in commento varchar(256),
    out id varchar(256)
)

begin
	set id = UUID();
    
    insert into Segnalazioni(id, idMotivazione, dataSegnalazione, stato, commento)
    values (id, idMotivazione, dataSegnalazione, stato, commento);
    
    select @id;
end $$

delimiter ;