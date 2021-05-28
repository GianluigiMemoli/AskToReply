
DELETE FROM votazioni WHERE idUtente = "idautoremock";
DELETE FROM risposte where idDomanda = "iddomandamock";
DELETE FROM risposte where corpo = "corpo";
DELETE FROM domande WHERE id = "iddomandamock";
DELETE FROM domande WHERE idAutore = "idautoremock";
DELETE FROM partecipanti WHERE idUtente = "idrisponditoremock" OR idUtente = "idautoremock";
DELETE FROM utenti WHERE id = "idrisponditoremock" OR id = "idautoremock";