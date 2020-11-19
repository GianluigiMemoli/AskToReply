use asktoreply;
DELIMITER $$
CREATE PROCEDURE DeleteModeratore(
    moderatoreId varchar(256)
)
BEGIN   
    SELECT id INTO @ruoloId FROM Ruoli WHERE Ruoli.nome = "Moderatore";
    UPDATE FROM Utente WHERE id = moderatoreId AND ruoloId = @ruoloId SET isDisattivato = 1;
END