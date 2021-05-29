use asktoreply;

#RESET DATABASE:

delete from interessi;
delete from partecipanti;
delete from utenti;
delete from ruoli;
delete from categorie;

#POPULATE DATABASE:

insert into ruoli values (1,"Partecipante"),
						 (2,"Moderatore"),
                         (3,"MasterModeratore");

insert into utenti values ("USER1ID","utente1@email.com","notHashedPass",null,"user1","Mario","Rossi",1,0),
						  ("USER2ID","utente2@email.com","notHashedPass",null,"user2","Luca","Bianchi",1,0),
                          ("USER3ID","utente3@email.com","notHashedPass",null,"user3","Antonella","Neri",1,0),
                          ("MOD1ID","mod1@email.com","notHashedPass",null,"mod1","Victor","White",2,0);
                          
insert into partecipanti values("USER1ID",0,0),
							   ("USER2ID",0,0),
                               ("USER3ID",0,0);

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
							("USER3ID","CAT5ID");