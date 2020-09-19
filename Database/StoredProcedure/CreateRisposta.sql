use asktoreply;

delimiter $$

create procedure CreateRisposta( 
    idDomanda varchar(256), 
    corpo varchar(256),
    idAutore varchar(256),
    out id varchar(256)
)

begin
	set id = UUID();
    
    insert into Risposte (id, idDomanda, corpo, idAutore)
    values (id, idDomanda, corpo, idAutore);
    
    select id;
end $$

delimiter ;