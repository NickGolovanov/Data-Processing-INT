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
    p_info_type varchar(255)
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
    VALUES (v_info_id, p_movie_id);

    RAISE NOTICE 'InfoMovie successfully added for movie id % with info id %', p_movie_id, v_info_id;
END;
$$;

CREATE OR REPLACE PROCEDURE add_subtitle(
    p_movie_id BIGINT,
    p_language VARCHAR(255),
    p_subtitle_location VARCHAR(255)
)
    LANGUAGE plpgsql
AS $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM public.movie WHERE movie_id = p_movie_id) THEN
        RAISE EXCEPTION 'Movie not found with ID: %', p_movie_id;
    END IF;

    INSERT INTO public.subtitle (language, subtitle_location, movie_id)
    VALUES (p_language, p_subtitle_location, p_movie_id);

    RAISE NOTICE 'Subtitle added successfully  movie ID: %', p_movie_id;
END;
$$;

call add_subtitle(1, 'English', 'https://www.youtube.com/watch?v=1');