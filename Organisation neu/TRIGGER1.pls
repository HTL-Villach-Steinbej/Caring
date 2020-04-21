create or replace TRIGGER TRIGGER1 
BEFORE UPDATE ON LEIHT_AUS 
FOR EACH ROW 
BEGIN
        Update fahrzeug Set ausgeliehen='nein' WHERE fid= :NEW.la_fid;
END;