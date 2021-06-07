use asktoreply;

delimiter $$

create procedure CreateRisposta( 
    idDomanda varchar(256), 
    corpo varchar(256),
    idAutore varchar(256),
    dataPubblicazione DATETIME,
    out id varchar(256)
)

begin
	set id = UUID();
    set dataPubblicazione = CURDATE();
    
    insert into Risposte (id, idDomanda, corpo, idAutore, dataPubblicazione, isNascosta)
    values (id, idDomanda, corpo, idAutore, dataPubblicazione, 0);
    
    select id;
end $$

delimiter ;