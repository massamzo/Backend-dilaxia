-- Enable Event Scheduler if not already enabled
USE playsphere;
SET GLOBAL event_scheduler = ON;

-- Create the event
DELIMITER //
CREATE EVENT delete_expired_otp
ON SCHEDULE EVERY 10 MINUTE
DO
BEGIN
    DELETE FROM temp_utenti WHERE expire_at <= NOW() - INTERVAL 10 MINUTE;
END;
//
DELIMITER ;