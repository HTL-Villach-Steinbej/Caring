create or replace TRIGGER TRIGGER2 
BEFORE INSERT ON LEIHT_AUS 
FOR EACH ROW 
DECLARE
    isausgeliehen fahrzeug.ausgeliehen%TYPE := NULL;
BEGIN
  select ausgeliehen into isausgeliehen from fahrzeug Where fid = :NEW.la_fid;
  
        IF isausgeliehen like 'ja' THEN
        RAISE_APPLICATION_ERROR(-20000, 'Auto ist bereits ausgeliehen');
        End if;
        IF isausgeliehen like 'nein' THEN
        Update fahrzeug Set ausgeliehen='ja' WHERE fid= :NEW.la_fid;
    END IF;
END;