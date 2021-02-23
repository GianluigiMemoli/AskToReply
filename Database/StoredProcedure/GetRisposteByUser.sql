use asktoreply; 

DELIMITER $$
CREATE PROCEDURE GetRisposteByUser(
    userId varchar(256),
    currPage integer, 
    num integer
)
    BEGIN			
        SELECT * FROM Risposte WHERE Risposte.idAutore = userId ORDER BY dataPubblicazione DESC LIMIT currPage, num; 
    END $$
