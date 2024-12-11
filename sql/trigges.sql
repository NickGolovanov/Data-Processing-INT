--- Account interaction trigger
CREATE TABLE IF NOT EXISTS account_log (
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

CREATE OR REPLACE TRIGGER account_change_logger
    AFTER UPDATE OR DELETE ON public.account
FOR EACH ROW
EXECUTE FUNCTION log_account_changes();


--- Account email verification triggers
--- Not a valid email
--- Email used

CREATE OR REPLACE FUNCTION verify_account_email_format()
    RETURNS TRIGGER AS $$
BEGIN
    IF NEW.email !~ '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$' THEN
        RAISE EXCEPTION 'Invalid email format: %', NEW.email;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER check_email_format
    BEFORE INSERT OR UPDATE ON account
    FOR EACH ROW
EXECUTE FUNCTION verify_account_email_format();

CREATE OR REPLACE FUNCTION verify_account_email_used()
    RETURNS TRIGGER AS $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM account
        WHERE email = NEW.email
          AND accountid != NEW.accountid
    ) THEN RAISE EXCEPTION 'The email % is already in use by another account.', NEW.email;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER check_email_used
    BEFORE INSERT OR UPDATE ON account
    FOR EACH ROW
EXECUTE FUNCTION verify_account_email_used();

--- Blocked account  triggers

CREATE OR REPLACE FUNCTION set_ispermanent()
    RETURNS TRIGGER AS $$
BEGIN
    IF NEW.dateofexpire IS NOT NULL THEN
        NEW.ispermanent := FALSE;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER enforce_ispermanent
    BEFORE INSERT OR UPDATE ON blockedaccount
    FOR EACH ROW
EXECUTE FUNCTION set_ispermanent();


--- Profile triggers
CREATE OR REPLACE FUNCTION profiles_4_limit()
    RETURNS TRIGGER AS $$
BEGIN
    IF  (
        SELECT count(*)
        FROM profile
        WHERE  profile.account_id = NEW.account_id
    ) >= 4 THEN RAISE EXCEPTION 'Cannot add more than 4 profiles to the account with ID: %', NEW.account_id;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER limit_t0_4_profiles
    BEFORE INSERT OR UPDATE ON profile
    FOR EACH ROW
EXECUTE FUNCTION profiles_4_limit();

