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
    IF NOT EXISTS (SELECT 1 FROM public.liveinfo WHERE liveinfo.liveinfo_id = p_liveinfoid) THEN
        RAISE EXCEPTION 'Live Info with id % does not exist', p_liveinfoid;
    END IF;

    IF p_watchTime IS NULL THEN
        RAISE EXCEPTION 'Watch time cannot be null';
    END IF;

    UPDATE liveinfo
    SET watched_time = p_watchTime
    WHERE liveinfo_id = p_liveinfoid;

END
$$;

-- Profile Controller

-- patch

CREATE OR REPLACE PROCEDURE update_profile(profile_data JSONB)
    LANGUAGE plpgsql
AS $$
BEGIN
    IF NOT profile_data ? 'profile_id' THEN
        RAISE EXCEPTION 'profile_id is required';
    END IF;
    UPDATE profile
    SET
        age             = COALESCE((profile_data->>'age')::INTEGER, age),
        film            = COALESCE(profile_data->>'film', film),
        language        = COALESCE(profile_data->>'language', language),
        minimum_age     = COALESCE((profile_data->>'minimum_age')::INTEGER, minimum_age),
        profile_child   = COALESCE((profile_data->>'profile_child')::BOOLEAN, profile_child),
        profile_image   = COALESCE(profile_data->>'profile_image', profile_image),
        profile_name    = COALESCE(profile_data->>'profile_name', profile_name),
        series          = COALESCE(profile_data->>'series', series),
        account_id      = COALESCE((profile_data->>'account_id')::INTEGER, account_id),
        preference_id   = COALESCE((profile_data->>'preference_id')::INTEGER, preference_id)
    WHERE profile_id = (profile_data->>'profile_id')::INTEGER;
END;
$$;