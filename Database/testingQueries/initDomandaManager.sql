use asktoreply;
INSERT into utenti (id, email, passwordHash, username, nome, cognome, ruoloID, isDisattivato) VALUES("idautoremock", "email@mock.com", "passwordhash", "autore", "mario", "rossi", 1, 0);
INSERT into utenti (id, email, passwordHash, username, nome, cognome, ruoloID, isDisattivato) VALUES("idautoretest", "email2@mock.com", "passwordhash", "autore2", "mario", "rossi", 1, 0);
INSERT into utenti (id, email, passwordHash, username, nome, cognome, ruoloID, isDisattivato) VALUES("idrisponditoremock", "genny@mock.com", "passwordhash", "risponditore", "gennaro", "verdi", 1, 0);
INSERT into partecipanti VALUES("idautoremock2", 0, 0);
INSERT into partecipanti VALUES("idautoremock", 0, 0);
INSERT into partecipanti VALUES("idrisponditoremock", 0, 0);
INSERT INTO domande VALUES("idmock", "titolo mock", "corpo di test", "idautoremock", "2021-05-24", 0, 0);
INSERT INTO categorie VALUES("id000", "pesca");
INSERT INTO categorie VALUES("id001", "tennis");
INSERT INTO domande VALUES("idmockpertinenti1", "titolo mock", "corpo di test", "idautoremock", "2021-05-24", 0, 0);
INSERT INTO domande VALUES("idmockpertinenti2", "titolo mock", "corpo di test", "idautoremock", "2021-05-24", 0, 0);
INSERT INTO domande VALUES("idmockpertinenti3", "titolo mock", "corpo di test", "idautoremock", "2021-05-24", 0, 0);
INSERT INTO categoriedomande VALUES ("idmockpertinenti1", "id001");
INSERT INTO categoriedomande VALUES ("idmockpertinenti1", "id000");
INSERT INTO categoriedomande VALUES ("idmockpertinenti2", "id000");
INSERT INTO categoriedomande VALUES ("idmockpertinenti2", "id001");
INSERT INTO categoriedomande VALUES ("idmockpertinenti3", "id000");
INSERT INTO categoriedomande VALUES ("idmockpertinenti3", "id001");
INSERT INTO risposte VALUES ("idRisp1", "idmock", "corpo", null, "idrisponditoremock", "2021-05-25", 0);
INSERT INTO risposte VALUES ("idRisp2", "idmockpertinenti1", "corpo", null, "idrisponditoremock", "2021-05-25", 0);
INSERT INTO risposte VALUES ("idRisp3", "idmockpertinenti2", "corpo", null, "idrisponditoremock", "2021-05-25", 0);
INSERT INTO risposte VALUES ("idRisp4", "idmockpertinenti3", "corpo", null, "idrisponditoremock", "2021-05-25", 0);