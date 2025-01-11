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


CREATE OR REPLACE PROCEDURE create_or_update_preferences(
    p_profile_id BIGINT,
    p_preference_id BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM public.profile WHERE profile_id = p_profile_id
    ) THEN
        RAISE EXCEPTION 'Profile not found with ID: %', p_profile_id;
    END IF;

    IF NOT EXISTS (
        SELECT 1 FROM public.preference WHERE preference_id = p_preference_id
    ) THEN
        RAISE EXCEPTION 'Preference not found with ID: %', p_preference_id;
    END IF;

    UPDATE public.profile
    SET preference_id = p_preference_id
    WHERE profile_id = p_profile_id;

    RAISE NOTICE 'Profile ID % updated with Preference ID %', p_profile_id, p_preference_id;
END;
$$;

CREATE OR REPLACE PROCEDURE get_preferences(
    p_profile_id BIGINT,
    p_profile_name VARCHAR,
    OUT p_preference_id BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM public.profile WHERE profile_id = p_profile_id
    ) THEN
        RAISE EXCEPTION 'Profile not found with ID: %', p_profile_id;
    END IF;

    SELECT preference_id, profile_name
    INTO p_preference_id, p_profile_name
    FROM public.profile
    WHERE profile_id = p_profile_id;

    IF p_preference_id IS NULL THEN
        RAISE EXCEPTION 'No preference set for profile with ID: %', p_profile_id;
    END IF;

    RAISE NOTICE 'Retrieved Preference ID % for Profile ID %', p_preference_id, p_profile_id;
END;
$$;

-- Series

CREATE OR REPLACE PROCEDURE add_season_to_series(
    p_series_id BIGINT,
    p_season_id BIGINT,
    p_season_number INTEGER
)
    LANGUAGE plpgsql
AS $$
BEGIN
    -- Check if the series exists
    IF NOT EXISTS (SELECT 1 FROM series WHERE series_id = p_series_id) THEN
        RAISE EXCEPTION 'Series with ID % does not exist', p_series_id;
    END IF;

    -- Check if the season already exists for the series
    IF EXISTS (SELECT 1 FROM season WHERE season_id = p_season_id) THEN
        RAISE EXCEPTION 'Season with ID % already exists', p_season_id;
    END IF;

    -- Insert the season into the 'season' table
    INSERT INTO season (season_id, series_id, season_number)
    VALUES (p_season_id, p_series_id, p_season_number);

    RAISE NOTICE 'Season with ID % successfully added to series with ID %', p_season_id, p_series_id;
END;
$$;

CREATE OR REPLACE PROCEDURE add_season_to_series(
    p_series_id BIGINT,
    p_season_id BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM series WHERE series_id = p_series_id) THEN
        RAISE EXCEPTION 'Series with ID % does not exist', p_series_id;
    END IF;

    IF NOT EXISTS (SELECT 1 FROM season WHERE season_id = p_season_id) THEN
        RAISE EXCEPTION 'Season with ID % does not exist', p_season_id;
    END IF;

    UPDATE season
    SET series_id = p_series_id
    WHERE season_id = p_season_id;

    RAISE NOTICE 'Season % added to Series %', p_season_id, p_series_id;
END;
$$;

-- Watchlist

