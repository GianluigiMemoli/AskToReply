use asktoreply;

#RESET DATABASE:

delete from categoriedomande;
delete from segnalazionidomanda;
delete from segnalazionirisposta;
delete from segnalazioni;
delete from statosegnalazione;
delete from votazioni;
delete from risposte;
delete from domande;
delete from interessi;
delete from partecipanti;
delete from utenti;
delete from ruoli;
delete from motivazioni;
delete from categorie;

#POPULATE DATABASE:

insert into statosegnalazione values(1, "da gestire"),
									(2, "approvata"),
                                    (3, "declinata");
                                    
insert into motivazioni values(2,"Offtopic"),
							  (1,"Offensiva"),
                              (3,"Spam"),
                              (4,"Contenuti inappropriati"),
                              (5,"Link dannosi"),
                              (6,"Altro");

insert into ruoli values (1,"Partecipante"),
						 (2,"Moderatore"),
                         (3,"MasterModeratore");

insert into utenti values ("USER1ID","utente1@email.com","notHashedPass",null,"user1","Mario","Rossi",1,0),
						  ("USER2ID","utente2@email.com","notHashedPass",null,"user2","Luca","Bianchi",1,0),
                          ("USER3ID","utente3@email.com","notHashedPass",null,"user3","Antonella","Neri",1,0),
                          ("USER4ID","laura@marcocci.com","notHashedPass",null,"user4","Laura","Marcocci",1,0),
                          ("MOD1ID","mod1@email.com","notHashedPass",null,"mod1","Victor","White",2,0),
						  ("MOD2ID","mod2@email.com","notHashedPass",null,"mod2","Vincent","Manetti",2,0),
                          ("USER5ID", "giorgia@santi.it", "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8", null, "user5", "Giorgia", "Santi",1,0);
                          

insert into partecipanti values("USER1ID",0,0),
							   ("USER2ID",0,0),
                               ("USER3ID",0,0),
							   ("USER4ID",0,0),
                               ("USER5ID",0,0);

insert into categorie values("CAT1ID", "Economia"),
							("CAT2ID", "Informatica"),
                            ("CAT3ID", "Tecnologia"),
                            ("CAT4ID", "Storia"),
                            ("CAT5ID", "Fisica"),
                            ("CAT6ID", "Astronomia");

insert into interessi values("USER1ID","CAT1ID"),
							("USER1ID","CAT2ID"),
                            ("USER1ID","CAT3ID"),
                            ("USER1ID","CAT4ID"),
							("USER1ID","CAT5ID"),
                            ("USER1ID","CAT6ID"),
                            ("USER2ID","CAT1ID"),
							("USER2ID","CAT2ID"),
                            ("USER2ID","CAT3ID"),
                            ("USER3ID","CAT3ID"),
                            ("USER3ID","CAT4ID"),
							("USER3ID","CAT5ID"),
                            ("USER4ID","CAT1ID"),
                            ("USER5ID","CAT1ID");
                            
insert into domande values ("DOM1ID", "Chi ha inventato la lampadina?","C'è chi dice che sia opera di Edison, altri citano Joseph Wilson Swan. Chi dei due?","USER1ID",CURDATE(),0,0),
						   ("DOM2ID", "Cosa determina il prezzo di una criptovaluta?","Inoltre, perchè alcune al lancio valgono già tanto?","USER2ID",CURDATE(),0,0),
						   ("DOM3ID", "Quanto tempo ci vuole per raggiungere Marte?","Con le tecnologie attuali quanto tempo è necessario per viaggiare dalla Terra al Pianeta rosso?","USER3ID",CURDATE(),0,0);
                           
insert into categoriedomande values("DOM1ID","CAT3ID"),
								   ("DOM1ID","CAT4ID"),
                                   ("DOM2ID","CAT1ID"),
								   ("DOM2ID","CAT2ID"),
                                   ("DOM2ID","CAT3ID"),
								   ("DOM3ID","CAT1ID");

insert into segnalazioni values("SE1ID",1,CURDATE(),1, "commento test","USER1ID"),
							   ("SE2ID",5,CURDATE(),1, "commento test","USER1ID"),
							   ("SE3ID",1,CURDATE(),1, "commento test","USER1ID");

insert into segnalazionidomanda values("SE1ID","DOM3ID"),
									  ("SE2ID","DOM2ID"),
                                      ("SE3ID","DOM3ID");