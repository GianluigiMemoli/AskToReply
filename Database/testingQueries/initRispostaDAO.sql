use asktoreply;
INSERT into utenti (id, email, passwordHash, username, nome, cognome, ruoloID, isDisattivato) VALUES("idautoremock", "email@mock.com", "passwordhash", "autore", "mario", "rossi", 1, 0);
INSERT into partecipanti VALUES("idautoremock", 0, 0);
INSERT into utenti (id, email, passwordHash, username, nome, cognome, ruoloID, isDisattivato) VALUES("idrisponditoremock", "email@mock.com", "passwordhash", "autore", "mario", "rossi", 1, 0);
INSERT into partecipanti VALUES("idrisponditoremock", 0, 0);
INSERT INTO domande VALUES("iddomandamock", "titolo mock", "corpo di test", "idautoremock", "2021-05-24", 0, 0);
INSERT INTO domande VALUES("domandaDaRispondere", "titolo mock", "corpo di test", "idautoremock", "2021-05-24", 0, 0);

INSERT INTO risposte VALUES ("idRisp1", "iddomandamock", "corpo", null, "idrisponditoremock", "2021-05-25", 0);
INSERT INTO risposte VALUES ("idRisp2", "iddomandamock", "corpo", null, "idrisponditoremock", "2021-05-25", 0);
INSERT INTO risposte VALUES ("idRisp3", "iddomandamock", "corpo", null, "idrisponditoremock", "2021-05-25", 0);
INSERT INTO risposte VALUES ("idRisp4", "iddomandamock", "corpo", null, "idrisponditoremock", "2021-05-25", 0);

INSERT INTO votazioni VALUES("idautoremock", "idRisp1", 1);
INSERT INTO votazioni VALUES("idautoremock", "idRisp2", 1);

INSERT INTO votazioni VALUES("idautoremock", "idRisp3", -1);
INSERT INTO votazioni VALUES("idautoremock", "idRisp4", -1);
