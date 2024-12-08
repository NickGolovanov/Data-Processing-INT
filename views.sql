
CREATE VIEW view_account_subscriptions AS
SELECT
    a.accountid,
    a.email,
    a.paymentmethod,
    s.subscriptionid,
    s.description AS subscription_description,
    s.subscriptionprice AS subscription_price,
    asub.dateofpurchase,
    asub.dateofexpire
FROM public.account a
         LEFT JOIN public.accountsubscription asub ON a.accountid = asub.accountid
         LEFT JOIN public.subscription s ON asub.subscriptionid = s.subscriptionid;


CREATE VIEW view_profile_preferences AS
SELECT
    p.profileid,
    p.profilename,
    p.language,
    mp.movieid,
    mp.seriesid
FROM public.profile p
         LEFT JOIN public.mediapreferences mp ON p.profileid = mp.preferenceid;

CREATE VIEW view_blocked_accounts AS
SELECT
    b.blockedaccountid,
    b.account_id,
    a.email AS account_email,
    b.ispermanent,
    b.dateofexpire
FROM public.blockedaccount b
         LEFT JOIN public.account a ON b.account_id = a.accountid;

CREATE VIEW view_movie_info AS
SELECT
    m.movieid,
    m.title,
    m.hd,
    m.sd,
    m.uhd,
    m.duration,
    m.views,
    i.description AS info_description
FROM public.movie m
         LEFT JOIN public.infomovie im ON m.movieid = im.movieid
         LEFT JOIN public.info i ON im.info_id = i.infoid;

CREATE VIEW view_season_episodes AS
SELECT
    sn.seasonid,
    sn.seasonnumber,
    e.episodeid,
    e.title AS episode_title,
    e.duration AS episode_duration,
    e.hd,
    e.sd,
    e.uhd,
    e.views
FROM public.season sn
         LEFT JOIN public.episode e ON sn.seasonid = e.season_id;

CREATE VIEW view_watchlist_details AS
SELECT
    w.watchlistid,
    w.profile_id,
    p.profilename,
    w.movie_id,
    m.title AS movie_title,
    w.series_id,
    s.title AS series_title
FROM public.watchlist w
         LEFT JOIN public.profile p ON w.profile_id = p.profileid
         LEFT JOIN public.movie m ON w.movie_id = m.movieid
         LEFT JOIN public.series s ON w.series_id = s.seriesid;

CREATE VIEW view_subtitles AS
SELECT
    sub.subtitleid,
    sub.language,
    sub.subtitlelocation,
    sub.episodeid,
    e.title AS episode_title,
    sub.movieid,
    m.title AS movie_title
FROM public.subtitle sub
         LEFT JOIN public.episode e ON sub.episodeid = e.episodeid
         LEFT JOIN public.movie m ON sub.movieid = m.movieid;