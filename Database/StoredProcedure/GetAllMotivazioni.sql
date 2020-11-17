use asktoreply;

drop procedure if exists GetAllMotivazioni;

DELIMITER &&

create procedure GetAllMotivazioni()
begin
	select id, nome from motivazioni;
end &&

DELIMITER ;