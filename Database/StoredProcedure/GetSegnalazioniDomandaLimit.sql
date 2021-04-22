use asktoreply;

drop procedure if exists GetSegnalazioniDomandaLimit;

delimiter &&
	
create procedure GetSegnalazioniDomandaLimit(
	in s integer,
	in e integer
)
begin
	
	select 
		*
	from
		segnalazioni s,
		segnalazionidomanda sd,
		domande d
	where
		s.stato = 1 and
		sd.idSegnalazione = s.id and
		sd.idDomanda = d.id
	order by 
		s.dataSegnalazione DESC
	limit s, e;
	
end &&

delimiter ;