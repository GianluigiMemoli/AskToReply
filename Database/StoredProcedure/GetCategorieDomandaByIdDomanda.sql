use asktoreply;

delimiter $$

drop procedure if exists GetCategorieDomandeByIdDomanda;

create procedure GetCategorieDomandeByIdDomanda(
	in idDomanda varchar(256)
)
begin
	
	select id, nome
	from categorie
		left join categoriedomande 
		on categorie.id = categoriedomande.idCategoria 
        and categoriedomande.idDomanda = idDomanda;
    
end $$

delimiter ;