-- init

insert into categorie values 
('id-categoria-testing-1', 'Botanica'),
('id-categoria-testing-2', 'Sport'),
('id-categoria-testing-3', 'Informatica'),
('id-categoria-testing-4', 'Cucina'),
('id-categoria-testing-5', 'Libri');

insert into utenti values ('id-utente-testing-1', 'sommerville.ian@gmail.com', '1234567890', null, 'sommerville.ian', 'Ian', 'Sommerville', 1, 0);

insert into partecipanti values ('id-utente-testing-1', 0, 0);

insert into interessi values
('id-utente-testing-1', 'id-categoria-testing-1'),
('id-utente-testing-1', 'id-categoria-testing-3');




