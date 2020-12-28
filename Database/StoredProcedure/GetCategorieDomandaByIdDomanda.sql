use asktoreply;

delimiter $$


create procedure GetCategorieDomandeByIdDomanda(
	in idDomanda varchar(256)
)
begin
	
	select id, nome
	from categorie
		join categoriedomande 
		on categorie.id = categoriedomande.idCategoria 
        and categoriedomande.idDomanda = idDomanda;
    
end $$

delimiter ;