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


CREATE OR REPLACE PROCEDURE update_subtitle(
    p_subtitle_id BIGINT,
    p_movie_id BIGINT,
    p_subtitle_langauge VARCHAR(255),
    p_subtitle_location VARCHAR(255)
)
    LANGUAGE plpgsql
AS $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM public.subtitle WHERE subtitle_id = p_subtitle_id) THEN
        RAISE EXCEPTION 'Subtitle with id % does not exist', p_subtitle_id;
    END IF;

    IF NOT EXISTS (SELECT 1 FROM public.movie WHERE movie_id = p_movie_id) THEN
        RAISE EXCEPTION 'Movie with id % does not exist', p_movie_id;
    END IF;

    UPDATE public.subtitle
    SET
        movie_id = p_movie_id,
        language = p_subtitle_langauge,
        subtitle_location = p_subtitle_location
    WHERE subtitle_id = p_subtitle_id;

    RAISE NOTICE 'Subtitle successfully updated for id %', p_subtitle_id;
END;
$$;

CREATE OR REPLACE PROCEDURE add_info_movie(
    p_movie_id BIGINT,
    p_info_description varchar(255),
    p_info_type varchar(255),
    OUT p_info_id BIGINT
)
    LANGUAGE plpgsql
AS $$
DECLARE
    v_info_id BIGINT;
BEGIN
    IF NOT EXISTS (SELECT 1 FROM public.movie WHERE movie_id = p_movie_id) THEN
        RAISE EXCEPTION 'Movie with id % does not exist', p_movie_id;
    END IF;

    INSERT INTO public.info (description, type)
    VALUES (p_info_description, p_info_type)
    RETURNING info_id INTO v_info_id;

    INSERT INTO public.infomovie (info_id, movie_id)
    VALUES (v_info_id, p_movie_id)
    RETURNING info_id INTO p_info_id;

    RAISE NOTICE 'InfoMovie successfully added for movie id % with info id %', p_movie_id, v_info_id;
END;
$$;


CREATE OR REPLACE PROCEDURE add_subtitle(
    p_movie_id BIGINT,
    p_language VARCHAR(255),
    p_subtitle_location VARCHAR(255),
    OUT p_subtitle_id BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM public.movie WHERE movie_id = p_movie_id) THEN
        RAISE EXCEPTION 'Movie not found with ID: %', p_movie_id;
    END IF;

    INSERT INTO public.subtitle (language, subtitle_location, movie_id)
    VALUES (p_language, p_subtitle_location, p_movie_id)
    RETURNING subtitle_id INTO p_subtitle_id;

    RAISE NOTICE 'Subtitle added successfully  movie ID: %', p_movie_id;
END;
$$;

CREATE OR REPLACE PROCEDURE delete_info_movie(
    p_movie_id BIGINT,
    p_info_id BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM public.infomovie
        WHERE movie_id = p_movie_id AND info_id = p_info_id
    ) THEN
        RAISE EXCEPTION 'InfoMovie not found for movie_id: %, info_id: %', p_movie_id, p_info_id;
    END IF;

    DELETE FROM public.infomovie
    WHERE movie_id = p_movie_id AND info_id = p_info_id;

    IF NOT EXISTS (
        SELECT 1
        FROM public.info
        WHERE info_id = p_info_id
    ) THEN
        RAISE EXCEPTION 'Info not found for info_id: %', p_info_id;
    END IF;

    DELETE FROM public.info
    WHERE info_id = p_info_id;
END;
$$;

CREATE OR REPLACE PROCEDURE update_info_movie(
    p_movie_id BIGINT,
    p_info_id BIGINT,
    p_description VARCHAR(255),
    p_type VARCHAR(255)
)
    LANGUAGE plpgsql
AS $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM public.infomovie
        WHERE movie_id = p_movie_id AND info_id = p_info_id
    ) THEN
        RAISE EXCEPTION 'InfoMovie not found for movie_id: %, info_id: %', p_movie_id, p_info_id;
    END IF;

    UPDATE public.info
    SET description = p_description,
        type = p_type
    WHERE info_id = p_info_id;

    RAISE NOTICE 'InfoMovie updated successfully for movie_id: %, info_id: %', p_movie_id, p_info_id;
END;
$$;


CREATE OR REPLACE PROCEDURE add_preference_movie(
    p_profile_id BIGINT,
    p_movie_id BIGINT,
    OUT p_preference_id BIGINT
)
    LANGUAGE plpgsql
AS $$
DECLARE
    v_movie_exists BOOLEAN;
    v_profile_exists BOOLEAN;
BEGIN
    -- Check if the profile exists
    SELECT EXISTS(SELECT 1 FROM public.profile WHERE profile_id = p_profile_id) INTO v_profile_exists;
    IF NOT v_profile_exists THEN
        RAISE EXCEPTION 'Profile not found with ID: %', p_profile_id;
    END IF;

    -- Check if the movie exists
    SELECT EXISTS(SELECT 1 FROM public.movie WHERE movie_id = p_movie_id) INTO v_movie_exists;
    IF NOT v_movie_exists THEN
        RAISE EXCEPTION 'Movie not found with ID: %', p_movie_id;
    END IF;

    -- Create a new preference
    INSERT INTO public.preference (profile_id)
    VALUES (p_profile_id)
    RETURNING preference_id INTO p_preference_id;

    -- Create a media preference for the movie
    INSERT INTO public.mediapreferences (movie_id, preference_id)
    VALUES (p_movie_id, p_preference_id);

    RAISE NOTICE 'Preference added successfully with ID: %', p_preference_id;
END;
$$;

CREATE OR REPLACE PROCEDURE delete_preference_movie(
    p_preference_id BIGINT,
    p_movie_id BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM public.mediapreferences
    WHERE movie_id = p_movie_id AND preference_id = p_preference_id;

    DELETE FROM public.preference
    WHERE preference_id = p_preference_id;

    RAISE NOTICE 'Preference with ID % for movie ID % deleted successfully', p_preference_id, p_movie_id;
END;
$$;

CREATE OR REPLACE PROCEDURE delete_preference_series(
    p_preference_id BIGINT,
    p_series_id BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM public.mediapreferences
    WHERE series_id = p_series_id AND preference_id = p_preference_id;

    DELETE FROM public.preference
    WHERE preference_id = p_preference_id;

    RAISE NOTICE 'Preference with ID % for series ID % deleted successfully', p_preference_id, p_series_id;
END;
$$;

CREATE OR REPLACE PROCEDURE add_account_subscription(
    p_account_id BIGINT,
    p_subscription_id BIGINT,
    p_date_of_purchase DATE,
    p_date_of_expire DATE
)
    LANGUAGE plpgsql
AS $$
BEGIN
    -- Check if the account exists
    IF NOT EXISTS (
        SELECT 1
        FROM public.account
        WHERE accountid = p_account_id
    ) THEN
        RAISE EXCEPTION 'Account not found with ID: %', p_account_id;
    END IF;

    IF NOT EXISTS (
        SELECT 1
        FROM public.subscription
        WHERE subscription_id = p_subscription_id
    ) THEN
        RAISE EXCEPTION 'Subscription not found with ID: %', p_subscription_id;
    END IF;

    IF EXISTS (
        SELECT 1
        FROM public.account_subscription
        WHERE account_id = p_account_id AND subscription_id = p_subscription_id
    ) THEN
        RAISE EXCEPTION 'Account already has this subscription: account_id = %, subscription_id = %',
            p_account_id, p_subscription_id;
    END IF;

    INSERT INTO public.account_subscription (
        account_id,
        subscription_id,
        date_of_purchase,
        date_of_expire
    )
    VALUES (
               p_account_id,
               p_subscription_id,
               p_date_of_purchase,
               p_date_of_expire
           );

    -- Raise a notice for successful insertion
    RAISE NOTICE 'Subscription added successfully for account_id: %, subscription_id: %',
        p_account_id, p_subscription_id;
END;
$$;


CREATE OR REPLACE PROCEDURE add_subscription(
    IN p_account_id BIGINT,
    IN p_subscription_id BIGINT,
    IN p_date_of_purchase DATE,
    IN p_date_of_expire DATE,
    OUT p_returned_subscription_id BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM public.account WHERE accountid = p_account_id) THEN
        RAISE EXCEPTION 'Account not found with ID: %', p_account_id;
    END IF;

    IF NOT EXISTS (SELECT 1 FROM public.subscription WHERE subscription_id = p_subscription_id) THEN
        RAISE EXCEPTION 'Subscription not found with ID: %', p_subscription_id;
    END IF;

    INSERT INTO public.account_subscription (
        account_id,
        subscription_id,
        date_of_purchase,
        date_of_expire
    )
    VALUES (
               p_account_id,
               p_subscription_id,
               p_date_of_purchase,
               p_date_of_expire
           )
    RETURNING subscription_id INTO p_returned_subscription_id;

    RAISE NOTICE 'Subscription added successfully for account ID: %, subscription ID: %', p_account_id, p_subscription_id;
END;
$$;

CREATE OR REPLACE PROCEDURE delete_subscription(
    IN p_account_id BIGINT,
    IN p_subscription_id BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM public.account WHERE accountid = p_account_id) THEN
        RAISE EXCEPTION 'Account not found with ID: %', p_account_id;
    END IF;

    IF NOT EXISTS (SELECT 1 FROM public.subscription WHERE subscription_id = p_subscription_id) THEN
        RAISE EXCEPTION 'Subscription not found with ID: %', p_subscription_id;
    END IF;

    IF NOT EXISTS (
        SELECT 1
        FROM public.account_subscription
        WHERE account_id = p_account_id AND subscription_id = p_subscription_id
    ) THEN
        RAISE EXCEPTION 'AccountSubscription not found for account ID: % and subscription ID: %', p_account_id, p_subscription_id;
    END IF;

    DELETE FROM public.account_subscription
    WHERE account_id = p_account_id AND subscription_id = p_subscription_id;

    RAISE NOTICE 'AccountSubscription deleted successfully for account ID: % and subscription ID: %', p_account_id, p_subscription_id;
END;
$$;

CREATE OR REPLACE PROCEDURE update_subscription(
    IN p_account_id BIGINT,
    IN p_subscription_id BIGINT,
    IN p_date_of_purchase DATE,
    IN p_date_of_expire DATE
)
    LANGUAGE plpgsql
AS $$
BEGIN
    -- Check if the account exists
    IF NOT EXISTS (SELECT 1 FROM public.account WHERE accountid = p_account_id) THEN
        RAISE EXCEPTION 'Account not found with ID: %', p_account_id;
    END IF;

    -- Check if the subscription exists
    IF NOT EXISTS (SELECT 1 FROM public.subscription WHERE subscription_id = p_subscription_id) THEN
        RAISE EXCEPTION 'Subscription not found with ID: %', p_subscription_id;
    END IF;

    -- Check if the AccountSubscription exists
    IF NOT EXISTS (
        SELECT 1
        FROM public.account_subscription
        WHERE account_id = p_account_id AND subscription_id = p_subscription_id
    ) THEN
        RAISE EXCEPTION 'AccountSubscription not found for account ID: % and subscription ID: %', p_account_id, p_subscription_id;
    END IF;

    -- Update the fields in account_subscription
    UPDATE public.account_subscription
    SET
        date_of_purchase = COALESCE(p_date_of_purchase, date_of_purchase),
        date_of_expire = COALESCE(p_date_of_expire, date_of_expire)
    WHERE account_id = p_account_id AND subscription_id = p_subscription_id;

    -- Raise a notice for successful update (optional for debugging)
    RAISE NOTICE 'AccountSubscription updated successfully for account ID: % and subscription ID: %', p_account_id, p_subscription_id;
END;
$$;

-- Series

CREATE OR REPLACE PROCEDURE add_season_to_series(
    IN p_series_id BIGINT,
    IN p_season_id BIGINT,
    OUT o_season_id BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    -- Check if the series exists
    IF NOT EXISTS (SELECT 1 FROM series WHERE series_id = p_series_id) THEN
        RAISE EXCEPTION 'Series with ID % does not exist', p_series_id;
    END IF;

    -- Check if the season already exists
    IF EXISTS (SELECT 1 FROM season WHERE season_id = p_season_id) THEN
        RAISE EXCEPTION 'Season with ID % already exists', p_season_id;
    END IF;

    -- Insert the season into the 'season' table
    INSERT INTO season (season_id, series_id)
    VALUES (p_season_id, p_series_id)
    RETURNING season_id INTO o_season_id;

    RAISE NOTICE 'Season with ID % successfully added to series with ID %', o_season_id, p_series_id;
END;
$$;

CREATE OR REPLACE PROCEDURE add_info_to_series(
    IN p_series_id BIGINT,
    IN p_info_id BIGINT,
    OUT o_info_series_id BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    -- Check if the series exists
    IF NOT EXISTS (SELECT 1 FROM series WHERE series_id = p_series_id) THEN
        RAISE EXCEPTION 'Series with ID % does not exist', p_series_id;
    END IF;

    -- Check if the information already exists
    IF EXISTS (SELECT 1 FROM infoseries WHERE series_id = p_series_id AND info_id = p_info_id) THEN
        RAISE EXCEPTION 'Information with ID % is already associated with series %', p_info_id, p_series_id;
    END IF;

    -- Insert the information into the info_series table
    INSERT INTO infoseries (series_id, info_id)
    VALUES (p_series_id, p_info_id)
    RETURNING infoseries.info_id INTO o_info_series_id;

    RAISE NOTICE 'Information with ID % successfully added to series with ID %', p_info_id, p_series_id;
END;
$$;


-- Watchlist

CREATE OR REPLACE PROCEDURE add_series_to_watchlist(
    IN p_profile_id BIGINT,
    IN p_series_id BIGINT,
    OUT o_watchlist_id BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    -- Check if the profile exists
    IF NOT EXISTS (SELECT 1 FROM profile WHERE profile_id = p_profile_id) THEN
        RAISE EXCEPTION 'Profile with ID % does not exist', p_profile_id;
    END IF;

    -- Check if the series exists
    IF NOT EXISTS (SELECT 1 FROM series WHERE series_id = p_series_id) THEN
        RAISE EXCEPTION 'Series with ID % does not exist', p_series_id;
    END IF;

    -- Check if the combination of profile_id and series_id already exists in the watchlist
    IF EXISTS (SELECT 1 FROM watchlist WHERE profile_id = p_profile_id AND series_id = p_series_id) THEN
        RAISE EXCEPTION 'This series is already in the watchlist for profile ID %', p_profile_id;
    END IF;

    -- Insert into the watchlist table
    INSERT INTO watchlist (profile_id, series_id)
    VALUES (p_profile_id, p_series_id)
    RETURNING watchlist_id INTO o_watchlist_id;

    RAISE NOTICE 'Series with ID % successfully added to the watchlist for profile ID %', p_series_id, p_profile_id;
END;
$$;

-- Account

CREATE OR REPLACE PROCEDURE block_account(
    IN p_account_id BIGINT,
    IN p_is_permanent BOOLEAN,
    IN p_date_of_expire DATE,
    OUT o_blocked_account_id BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    -- Check if the account exists
    IF NOT EXISTS (SELECT 1 FROM account WHERE accountid = p_account_id) THEN
        RAISE EXCEPTION 'Account with ID % does not exist', p_account_id;
    END IF;

    -- Insert into blocked_account table
    INSERT INTO blocked_account (account_id, is_permanent, date_of_expire)
    VALUES (p_account_id, p_is_permanent, p_date_of_expire)
    RETURNING blocked_accountid INTO o_blocked_account_id;
END;
$$;

CREATE OR REPLACE PROCEDURE unblock_account(
    IN p_account_id BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    -- Check if the account exists
    IF NOT EXISTS (SELECT 1 FROM account WHERE accountid = p_account_id) THEN
        RAISE EXCEPTION 'Account with ID % does not exist', p_account_id;
    END IF;

    -- Delete from blocked_account table
    DELETE FROM blocked_account
    WHERE account_id = p_account_id;

    -- Check if the account was blocked
    IF NOT FOUND THEN
        RAISE EXCEPTION 'No block record found for account ID %', p_account_id;
    END IF;
END;
$$;



