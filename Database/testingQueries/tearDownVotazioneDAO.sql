use asktoreply;
delete from votazioni where idRisposta = "idRisp1" OR idRisposta = "idRisp2" OR idRisposta="idRisp3";
delete from risposte where id = "idRisp1" OR id = "idRisp2" OR id="idRisp3";
delete from domande where id = "iddomandamock";
delete from partecipanti where idUtente = "idrisponditoremock" or idUtente = "idrisponditoremock2" or idUtente="idautoremock";
delete from utenti where id = "idrisponditoremock" or id = "idrisponditoremock2" or id = "idautoremock";