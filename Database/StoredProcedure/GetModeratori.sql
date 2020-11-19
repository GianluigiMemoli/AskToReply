use asktoreply;
DELIMITER $$
CREATE PROCEDURE GetModeratori()
BEGIN
    SELECT id INTO @ruoloId FROM Ruoli WHERE Ruoli.nome = 'Moderatore';
    SELECT * FROM utenti WHERE ruoloId = @ruoloId;
END $$