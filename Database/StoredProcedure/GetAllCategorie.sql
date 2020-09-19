use asktoreply;

drop procedure if exists GetAllCategorie;

delimiter $$

create procedure GetAllCategorie()
begin
	select id, nome from categorie;
end $$

delimiter ;