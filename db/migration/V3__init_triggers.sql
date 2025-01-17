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
    ELSIF (TG_OP = 'INSERT') THEN
        INSERT INTO account_log (accountid, operation)
        VALUES (NEW.accountid, 'INSERT');
END IF;
RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER account_change_logger
    AFTER INSERT OR UPDATE OR DELETE ON public.account
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


----Can be replaced by the "unique" property when set up an entity
-- CREATE OR REPLACE FUNCTION verify_account_email_used()
--     RETURNS TRIGGER AS $$
-- BEGIN
--     IF EXISTS (
--         SELECT 1
--         FROM account
--         WHERE (email = NEW.email AND accountid != OLD.accountid) OR (email = NEW.email AND OLD.accountid IS NULL)
--     ) THEN
--         RAISE EXCEPTION 'The email % is already in use by another account.', NEW.email;
--     END IF;
--     RETURN NEW;
-- END;
-- $$ LANGUAGE plpgsql;
--
-- CREATE OR REPLACE TRIGGER check_email_used
--     BEFORE INSERT OR UPDATE ON account
--     FOR EACH ROW
-- EXECUTE FUNCTION verify_account_email_used();

--- Blocked account  triggers

CREATE OR REPLACE FUNCTION set_ispermanent()
    RETURNS TRIGGER AS $$
BEGIN
    IF NEW.date_of_expire IS NOT NULL THEN
        NEW.is_permanent := FALSE;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER enforce_ispermanent
    BEFORE INSERT OR UPDATE ON blocked_account
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
    ) > 4 THEN RAISE EXCEPTION 'Cannot add more than 4 profiles to the account with ID: %', NEW.account_id;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER limit_to_4_profiles
    BEFORE INSERT OR UPDATE ON profile
    FOR EACH ROW
EXECUTE FUNCTION profiles_4_limit();


--
-- NOT VALID TRIGGER, DOESN'T CONTAIN HALF OF PROFILE NOT NULL FIELDS!!!!!
--
--- Auto create profile for account and preference for profile
-- CREATE OR REPLACE FUNCTION create_profile_for_account()
--     RETURNS TRIGGER AS $$
-- BEGIN
--     INSERT INTO profile (account_id)
--     VALUES (NEW.accountid);
--
--     RETURN NEW;
-- END;
-- $$ LANGUAGE plpgsql;
--
-- CREATE OR REPLACE TRIGGER after_account_insert
--     AFTER INSERT ON account
--     FOR EACH ROW
-- EXECUTE FUNCTION create_profile_for_account();



---
CREATE OR REPLACE FUNCTION create_preference_for_profile()
    RETURNS TRIGGER AS $$
DECLARE
    new_preference_id INT;
BEGIN
    INSERT INTO preference (profile_id)
    VALUES (NEW.profile_id)
    RETURNING preference.preference_id INTO new_preference_id;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER after_profile_insert
    AFTER INSERT ON profile
    FOR EACH ROW
EXECUTE FUNCTION create_preference_for_profile();


--- Blocked account triggers
--- This trigger can cause problems with testing uncoment in production

-- CREATE OR REPLACE FUNCTION auto_expire_blocked_accounts()
--     RETURNS TRIGGER AS $$
-- BEGIN
--     IF NEW.date_of_expire < CURRENT_DATE AND NOT NEW.is_permanent THEN
--         DELETE FROM blockedaccount WHERE blocked_accountid = NEW.blocked_accountid;
--     END IF;
--     RETURN NEW;
-- END;
-- $$ LANGUAGE plpgsql;
--
-- CREATE OR REPLACE TRIGGER auto_expire_trigger
--     AFTER INSERT OR UPDATE ON blockedaccount
--     FOR EACH ROW
-- EXECUTE FUNCTION auto_expire_blocked_accounts();


--- Watchlist triggers
CREATE OR REPLACE FUNCTION prevent_duplicate_watchlist()
    RETURNS TRIGGER AS $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM watchlist
        WHERE profile_id = NEW.profile_id
          AND (movie_id = NEW.movie_id OR series_id = NEW.series_id)
    ) THEN
        RAISE EXCEPTION 'Duplicate entry in watchlist for profile_id %', NEW.profile_id;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER prevent_duplicates_trigger
    BEFORE INSERT OR UPDATE ON watchlist
    FOR EACH ROW
EXECUTE FUNCTION prevent_duplicate_watchlist();


-- function to handle the creation of the watchlist
CREATE OR REPLACE FUNCTION create_watchlist_with_constraints()
    RETURNS TRIGGER AS $$
BEGIN

    IF (NEW.series_id IS NOT NULL AND NEW.movie_id IS NOT NULL) THEN
        RAISE EXCEPTION 'You cannot specify both series_id and movie_id in the watchlist';
    END IF;

    IF NEW.series_id IS NULL AND NEW.movie_id IS NULL THEN
        RAISE EXCEPTION 'At least one of series_id or movie_id must be specified';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER after_watchlist_insert
    BEFORE INSERT OR UPDATE ON watchlist
    FOR EACH ROW
EXECUTE FUNCTION create_watchlist_with_constraints();

-- function to handle media preference creation
CREATE OR REPLACE FUNCTION create_media_preference_with_constraints()
    RETURNS TRIGGER AS $$
BEGIN
    IF (NEW.series_id IS NOT NULL AND NEW.movie_id IS NOT NULL) THEN
        RAISE EXCEPTION 'You cannot specify both series_id and movie_id in media preference';
    END IF;

    IF NEW.series_id IS NULL AND NEW.movie_id IS NULL THEN
        RAISE EXCEPTION 'At least one of series_id or movie_id must be specified';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER after_media_preference_insert
    BEFORE INSERT OR UPDATE ON mediapreferences
    FOR EACH ROW
EXECUTE FUNCTION create_media_preference_with_constraints();
