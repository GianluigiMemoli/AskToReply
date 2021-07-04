use asktoreply;


insert into utenti values ("USER11ID","utente1@email.com","notHashedPass",null,"user1","Mario","Rossi",1,0),
						  ("USER2ID","utente2@email.com","notHashedPass",null,"user2","Luca","Bianchi",1,0),
                          ("USER3ID","utente3@email.com","notHashedPass",null,"user3","Antonella","Neri",1,0),
                          ("MOD1ID","mod1@email.com","notHashedPass",null,"mod1","Victor","White",2,0);
                          
insert into partecipanti values("USER11ID",0,0),
							   ("USER2ID",0,0),
                               ("USER3ID",0,0);

                              
insert into categorie values("CAT1ID", "Economia"),
							("CAT2ID", "Informatica"),
                            ("CAT3ID", "Tecnologia"),
                            ("CAT4ID", "Storia"),
                            ("CAT5ID", "Fisica"),
                            ("CAT6ID", "Astronomia");

insert into interessi values("USER11ID","CAT1ID"),
							("USER11ID","CAT2ID"),
                            ("USER11ID","CAT3ID"),
                            ("USER11ID","CAT4ID"),
							("USER11ID","CAT5ID"),
                            ("USER11ID","CAT6ID"),
                            ("USER2ID","CAT1ID"),
							("USER2ID","CAT2ID"),
                            ("USER2ID","CAT3ID"),
                            ("USER3ID","CAT3ID"),
                            ("USER3ID","CAT4ID"),
							("USER3ID","CAT5ID");
                            
insert into domande values ("DOM1ID", "Chi ha inventato la lampadina?","C'è chi dice che sia opera di Edison, altri citano Joseph Wilson Swan. Chi dei due?","USER11ID",CURDATE(),0,0),
						   ("DOM2ID", "Cosa determina il prezzo di una criptovaluta?","Inoltre, perchè alcune al lancio valgono già tanto?","USER2ID",CURDATE(),0,0),
						   ("DOM3ID", "Quanto tempo ci vuole per raggiungere Marte?","Con le tecnologie attuali quanto tempo è necessario per viaggiare dalla Terra al Pianeta rosso?","USER3ID",CURDATE(),0,0),
						   ("DOM4ID", "Quanto tempo ci vuole per raggiungere Marte?","Con le tecnologie attuali quanto tempo è necessario per viaggiare dalla Terra al Pianeta rosso?","USER3ID",CURDATE(),0,0);
                           
insert into categoriedomande values("DOM1ID","CAT3ID"),
								   ("DOM1ID","CAT4ID"),
                                   ("DOM2ID","CAT1ID"),
								   ("DOM2ID","CAT2ID"),
                                   ("DOM2ID","CAT3ID"),
								   ("DOM3ID","CAT1ID"),
								   ("DOM4ID","CAT1ID");
								   

insert into segnalazioni values("SE1ID",1,CURDATE(),1, "commento test","USER11ID"),
							   ("SE2ID",5,CURDATE(),1, "commento test","USER11ID"),
							   ("SE3ID",1,CURDATE(),1, "commento test","USER11ID");

insert into segnalazionidomanda values("SE1ID","DOM3ID"),
									  ("SE2ID","DOM2ID"),
                                      ("SE3ID","DOM3ID");
                                      