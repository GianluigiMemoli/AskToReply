use asktoreply;

#RESET DATABASE:

insert into statosegnalazione values(1, "da gestire"),
									(2, "approvata"),
                                    (3, "declinata");
                                    
insert into motivazioni values(1,"Offensiva"),
							  (2,"Offtopic"),
                              (3,"Spam"),
                              (4,"Contenuti inappropriati"),
                              (5,"Link dannosi"),
                              (6,"Altro");

insert into ruoli values (1,"Partecipante"),
						 (2,"Moderatore"),
                         (3,"MasterModeratore");
# passwordHash è "password"
insert into utenti values ("USER0ID","master@moderatore.com","5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8",null,"mod","Mario","Rossi",3,0);
