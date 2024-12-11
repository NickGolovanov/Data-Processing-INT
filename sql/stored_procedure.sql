-- Account

--- Reset password Stored Procedures
CREATE OR REPLACE PROCEDURE reset_password(
    p_email VARCHAR(255),
    p_new_password VARCHAR(255)
)
    LANGUAGE plpgsql
AS $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM public.account WHERE email = p_email) THEN
        RAISE EXCEPTION 'Account with email % does not exist', p_email;
    END IF;

    UPDATE public.account
    SET password = p_new_password
    WHERE email = p_email;

    RAISE NOTICE 'Password successfully reset for email %', p_email;
END;
$$;

--- Login

--- Register

-- Live Info
CREATE OR REPLACE PROCEDURE update_watch_time(
    p_liveinfoid BIGINT,
    p_watchTime VARCHAR(255)
)
LANGUAGE plpgsql
AS $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM public.liveinfo WHERE liveinfoid = p_liveinfoid) THEN
        RAISE EXCEPTION 'Live Info with id % does not exist', p_liveinfoid;
    END IF;

    IF p_watchTime IS NULL THEN
        RAISE EXCEPTION 'Watch time cannot be null';
    END IF;

    UPDATE liveinfo
    SET watchedtime = p_watchTime
    WHERE liveinfoid = p_liveinfoid;

END
$$