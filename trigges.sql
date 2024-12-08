CREATE TABLE account_log (
    log_id SERIAL PRIMARY KEY,
    accountid BIGINT,
    operation TEXT,
    changed_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE OR REPLACE FUNCTION log_account_changes()
RETURNS TRIGGER AS $$
BEGIN
    IF (TG_OP = 'UPDATE') THEN
        INSERT INTO account_log (accountid, operation)
        VALUES (NEW.accountid, 'UPDATE');
    ELSIF (TG_OP = 'DELETE') THEN
        INSERT INTO account_log (accountid, operation)
        VALUES (OLD.accountid, 'DELETE');
END IF;
RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER account_change_logger
    AFTER UPDATE OR DELETE ON public.account
FOR EACH ROW
EXECUTE FUNCTION log_account_changes();
