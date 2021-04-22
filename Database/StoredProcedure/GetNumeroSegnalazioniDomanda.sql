use asktoreply;

drop procedure if exists GetNumeroSegnalazioniDomanda;

delimiter &&

create procedure GetNumeroSegnalazioniDomanda()
begin
	select count(*) as numero_segnalazioni_domanda
	from segnalazioni s, segnalazionidomanda sd
	WHERE s.stato = 0 AND
    sd.idSegnalazione = s.id;
end &&

delimiter ;