use asktoreply

delimiter $$


create procedure CreateSegnalazione(
	in idMotivazione integer,
    in dataSegnalazione datetime,
    in stato integer,
    in commento varchar(256),
	in idUtente varchar(256),
    out id varchar(256)
)

begin
	set id = UUID();
    
    insert into Segnalazioni(id, idMotivazione, dataSegnalazione, stato, commento, idUtente)
    values (id, idMotivazione, dataSegnalazione, stato, commento, idUtente);
    
    select id;
end $$

delimiter ;