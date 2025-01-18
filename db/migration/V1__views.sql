CREATE OR REPLACE VIEW view_account_subscriptions AS
SELECT a.accountid,
       a.email,
       a.payment_method,
       s.subscription_id,
       s.description        AS subscription_description,
       s.subscription_price AS subscription_price,
       asub.date_of_purchase,
       asub.date_of_expire
FROM public.account a
         LEFT JOIN public.account_subscription asub ON a.accountid = asub.account_id
         LEFT JOIN public.subscription s ON asub.subscription_id = s.subscription_id;


CREATE OR REPLACE VIEW view_profile_preferences AS
SELECT p.profile_id,
       p.profile_name,
       p.language,
       mp.movie_id,
       mp.series_id
FROM public.profile p
         LEFT JOIN public.mediapreferences mp ON p.profile_id = mp.preference_id;

CREATE OR REPLACE VIEW view_blocked_accounts AS
SELECT b.blocked_accountid,
       b.account_id,
       a.email AS account_email,
       b.is_permanent,
       b.date_of_expire
FROM public.blocked_account b
         LEFT JOIN public.account a ON b.account_id = a.accountid;
--
-- CREATE OR REPLACE VIEW view_movie_info AS
-- SELECT
--     m.movie_id,
--     m.title,
--     m.hd,
--     m.sd,
--     m.uhd,
--     m.duration,
--     m.views,
--     i.description AS info_description
-- FROM public.movie m
--          LEFT JOIN public.infomovie im ON m.movie_id = im.movieid
--          LEFT JOIN public.info i ON im.info_id = i.info_id;


CREATE OR REPLACE VIEW view_season_episodes AS
SELECT sn.season_id,
       sn.season_number,
       e.episode_id,
       e.title    AS episode_title,
       e.duration AS episode_duration,
       e.hd,
       e.sd,
       e.uhd,
       e.views
FROM public.season sn
         LEFT JOIN public.episode e ON sn.season_id = e.season_id;

CREATE OR REPLACE VIEW view_watchlist_details AS
SELECT w.watchlist_id,
       w.profile_id,
       p.profile_name,
       w.movie_id,
       m.title AS movie_title,
       w.series_id,
       s.title AS series_title
FROM public.watchlist w
         LEFT JOIN public.profile p ON w.profile_id = p.profile_id
         LEFT JOIN public.movie m ON w.movie_id = m.movie_id
         LEFT JOIN public.series s ON w.series_id = s.series_id;

CREATE OR REPLACE VIEW view_subtitles AS
SELECT sub.subtitle_id,
       sub.language,
       sub.subtitle_location,
       sub.episode_id,
       e.title AS episode_title,
       sub.movie_id,
       m.title AS movie_title
FROM public.subtitle sub
         LEFT JOIN public.episode e ON sub.episode_id = e.episode_id
         LEFT JOIN public.movie m ON sub.movie_id = m.movie_id;