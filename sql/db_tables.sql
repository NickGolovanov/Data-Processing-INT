--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2 (Debian 17.2-1.pgdg120+1)
-- Dumped by pg_dump version 17.2

-- Started on 2024-12-25 17:40:59

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

-- CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 3567 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 218 (class 1259 OID 16385)
-- Name: account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.account (
    accountid bigint NOT NULL,
    email character varying(255),
    password character varying(255),
    payment_method character varying(255),
    referral_discount_id bigint,
    CONSTRAINT account_payment_method_check CHECK (((payment_method)::text = ANY ((ARRAY['VISA'::character varying, 'MASTERCARD'::character varying, 'IDEAL'::character varying])::text[])))
);


ALTER TABLE public.account OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16384)
-- Name: account_accountid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.account ALTER COLUMN accountid ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.account_accountid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 219 (class 1259 OID 16393)
-- Name: account_subscription; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.account_subscription (
    date_of_expire date,
    date_of_purchase date,
    account_id bigint NOT NULL,
    subscription_id bigint NOT NULL
);


ALTER TABLE public.account_subscription OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16399)
-- Name: blocked_account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.blocked_account (
    blocked_accountid bigint NOT NULL,
    date_of_expire date,
    is_permanent boolean,
    account_id bigint NOT NULL
);


ALTER TABLE public.blocked_account OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16398)
-- Name: blocked_account_blocked_accountid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.blocked_account ALTER COLUMN blocked_accountid ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.blocked_account_blocked_accountid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 223 (class 1259 OID 16405)
-- Name: episode; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.episode (
    episode_id bigint NOT NULL,
    hd boolean DEFAULT false,
    sd boolean DEFAULT false,
    uhd boolean DEFAULT false,
    duration double precision,
    title character varying(255),
    views integer DEFAULT 0,
    season_id bigint NOT NULL
);


ALTER TABLE public.episode OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16404)
-- Name: episode_episode_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.episode ALTER COLUMN episode_id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.episode_episode_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 225 (class 1259 OID 16415)
-- Name: info; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.info (
    info_id bigint NOT NULL,
    description character varying(255),
    type smallint,
    CONSTRAINT info_type_check CHECK (((type >= 0) AND (type <= 1)))
);


ALTER TABLE public.info OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16414)
-- Name: info_info_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.info ALTER COLUMN info_id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.info_info_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 226 (class 1259 OID 16421)
-- Name: infomovie; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.infomovie (
    infoid bigint NOT NULL,
    movieid bigint NOT NULL,
    info_id bigint NOT NULL,
    movie_id bigint NOT NULL
);


ALTER TABLE public.infomovie OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 16426)
-- Name: infoseries; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.infoseries (
    infoid bigint NOT NULL,
    seriesid bigint NOT NULL,
    info_id bigint,
    series_id bigint
);


ALTER TABLE public.infoseries OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 16432)
-- Name: liveinfo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.liveinfo (
    liveinfo_id bigint NOT NULL,
    watched_time character varying(255),
    episode_id bigint,
    movie_id bigint NOT NULL,
    profile_id bigint
);


ALTER TABLE public.liveinfo OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 16431)
-- Name: liveinfo_liveinfo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.liveinfo ALTER COLUMN liveinfo_id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.liveinfo_liveinfo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 231 (class 1259 OID 16438)
-- Name: mediapreferences; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mediapreferences (
    media_preference_id bigint NOT NULL,
    movie_id bigint,
    preference_id bigint,
    series_id bigint
);


ALTER TABLE public.mediapreferences OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 16437)
-- Name: mediapreferences_media_preference_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.mediapreferences ALTER COLUMN media_preference_id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.mediapreferences_media_preference_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 233 (class 1259 OID 16444)
-- Name: movie; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.movie (
    movie_id bigint NOT NULL,
    hd boolean,
    sd boolean,
    uhd boolean,
    duration integer,
    title character varying(255),
    views integer
);


ALTER TABLE public.movie OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 16443)
-- Name: movie_movie_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.movie ALTER COLUMN movie_id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.movie_movie_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 235 (class 1259 OID 16450)
-- Name: preference; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.preference (
    preference_id bigint NOT NULL,
    profile_id bigint NOT NULL
);


ALTER TABLE public.preference OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 16449)
-- Name: preference_preference_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.preference ALTER COLUMN preference_id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.preference_preference_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 237 (class 1259 OID 16456)
-- Name: profile; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.profile (
    profile_id bigint NOT NULL,
    age integer,
    film boolean,
    language character varying(255),
    minimum_age integer,
    profile_child boolean,
    profile_image character varying(255),
    profile_name character varying(255),
    series boolean,
    account_id bigint NOT NULL
);


ALTER TABLE public.profile OWNER TO postgres;

--
-- TOC entry 236 (class 1259 OID 16455)
-- Name: profile_profile_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.profile ALTER COLUMN profile_id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.profile_profile_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 239 (class 1259 OID 16464)
-- Name: referraldiscount; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.referraldiscount (
    referral_discount_id bigint NOT NULL,
    link character varying(255)
);


ALTER TABLE public.referraldiscount OWNER TO postgres;

--
-- TOC entry 238 (class 1259 OID 16463)
-- Name: referraldiscount_referral_discount_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.referraldiscount ALTER COLUMN referral_discount_id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.referraldiscount_referral_discount_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 241 (class 1259 OID 16470)
-- Name: season; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.season (
    season_id bigint NOT NULL,
    season_number integer,
    series_id bigint NOT NULL
);


ALTER TABLE public.season OWNER TO postgres;

--
-- TOC entry 240 (class 1259 OID 16469)
-- Name: season_season_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.season ALTER COLUMN season_id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.season_season_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 243 (class 1259 OID 16476)
-- Name: series; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.series (
    series_id bigint NOT NULL,
    minimum_age integer,
    title character varying(255),
    views integer DEFAULT 0
);


ALTER TABLE public.series OWNER TO postgres;

--
-- TOC entry 242 (class 1259 OID 16475)
-- Name: series_series_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.series ALTER COLUMN series_id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.series_series_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 245 (class 1259 OID 16483)
-- Name: subscription; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.subscription (
    subscription_id bigint NOT NULL,
    description character varying(255),
    subscription_price double precision
);


ALTER TABLE public.subscription OWNER TO postgres;

--
-- TOC entry 246 (class 1259 OID 16488)
-- Name: subscription_accounts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.subscription_accounts (
    subscription_subscription_id bigint NOT NULL,
    accounts_account_id bigint NOT NULL,
    accounts_subscription_id bigint NOT NULL
);


ALTER TABLE public.subscription_accounts OWNER TO postgres;

--
-- TOC entry 244 (class 1259 OID 16482)
-- Name: subscription_subscription_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.subscription ALTER COLUMN subscription_id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.subscription_subscription_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 248 (class 1259 OID 16494)
-- Name: subtitle; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.subtitle (
    subtitle_id bigint NOT NULL,
    language character varying(255),
    subtitle_location character varying(255),
    episode_id bigint,
    movie_id bigint
);


ALTER TABLE public.subtitle OWNER TO postgres;

--
-- TOC entry 247 (class 1259 OID 16493)
-- Name: subtitle_subtitle_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.subtitle ALTER COLUMN subtitle_id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.subtitle_subtitle_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 249 (class 1259 OID 16501)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    email character varying(255),
    firstname character varying(255),
    lastname character varying(255),
    password character varying(255)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 252 (class 1259 OID 16522)
-- Name: users_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_seq OWNER TO postgres;

--
-- TOC entry 251 (class 1259 OID 16509)
-- Name: watchlist; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.watchlist (
    watchlist_id bigint NOT NULL,
    movie_id bigint,
    profile_id bigint NOT NULL,
    series_id bigint
);


ALTER TABLE public.watchlist OWNER TO postgres;

--
-- TOC entry 250 (class 1259 OID 16508)
-- Name: watchlist_watchlist_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.watchlist ALTER COLUMN watchlist_id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.watchlist_watchlist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3527 (class 0 OID 16385)
-- Dependencies: 218
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.account (accountid, email, password, payment_method, referral_discount_id) FROM stdin;
\.


--
-- TOC entry 3528 (class 0 OID 16393)
-- Dependencies: 219
-- Data for Name: account_subscription; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.account_subscription (date_of_expire, date_of_purchase, account_id, subscription_id) FROM stdin;
\.


--
-- TOC entry 3530 (class 0 OID 16399)
-- Dependencies: 221
-- Data for Name: blocked_account; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.blocked_account (blocked_accountid, date_of_expire, is_permanent, account_id) FROM stdin;
\.


--
-- TOC entry 3532 (class 0 OID 16405)
-- Dependencies: 223
-- Data for Name: episode; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.episode (episode_id, hd, sd, uhd, duration, title, views, season_id) FROM stdin;
\.


--
-- TOC entry 3534 (class 0 OID 16415)
-- Dependencies: 225
-- Data for Name: info; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.info (info_id, description, type) FROM stdin;
\.


--
-- TOC entry 3535 (class 0 OID 16421)
-- Dependencies: 226
-- Data for Name: infomovie; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.infomovie (infoid, movieid, info_id, movie_id) FROM stdin;
\.


--
-- TOC entry 3536 (class 0 OID 16426)
-- Dependencies: 227
-- Data for Name: infoseries; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.infoseries (infoid, seriesid, info_id, series_id) FROM stdin;
\.


--
-- TOC entry 3538 (class 0 OID 16432)
-- Dependencies: 229
-- Data for Name: liveinfo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.liveinfo (liveinfo_id, watched_time, episode_id, movie_id, profile_id) FROM stdin;
\.


--
-- TOC entry 3540 (class 0 OID 16438)
-- Dependencies: 231
-- Data for Name: mediapreferences; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.mediapreferences (media_preference_id, movie_id, preference_id, series_id) FROM stdin;
\.


--
-- TOC entry 3542 (class 0 OID 16444)
-- Dependencies: 233
-- Data for Name: movie; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.movie (movie_id, hd, sd, uhd, duration, title, views) FROM stdin;
\.


--
-- TOC entry 3544 (class 0 OID 16450)
-- Dependencies: 235
-- Data for Name: preference; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.preference (preference_id, profile_id) FROM stdin;
\.


--
-- TOC entry 3546 (class 0 OID 16456)
-- Dependencies: 237
-- Data for Name: profile; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.profile (profile_id, age, film, language, minimum_age, profile_child, profile_image, profile_name, series, account_id) FROM stdin;
\.


--
-- TOC entry 3548 (class 0 OID 16464)
-- Dependencies: 239
-- Data for Name: referraldiscount; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.referraldiscount (referral_discount_id, link) FROM stdin;
\.


--
-- TOC entry 3550 (class 0 OID 16470)
-- Dependencies: 241
-- Data for Name: season; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.season (season_id, season_number, series_id) FROM stdin;
\.


--
-- TOC entry 3552 (class 0 OID 16476)
-- Dependencies: 243
-- Data for Name: series; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.series (series_id, minimum_age, title, views) FROM stdin;
\.


--
-- TOC entry 3554 (class 0 OID 16483)
-- Dependencies: 245
-- Data for Name: subscription; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.subscription (subscription_id, description, subscription_price) FROM stdin;
\.


--
-- TOC entry 3555 (class 0 OID 16488)
-- Dependencies: 246
-- Data for Name: subscription_accounts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.subscription_accounts (subscription_subscription_id, accounts_account_id, accounts_subscription_id) FROM stdin;
\.


--
-- TOC entry 3557 (class 0 OID 16494)
-- Dependencies: 248
-- Data for Name: subtitle; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.subtitle (subtitle_id, language, subtitle_location, episode_id, movie_id) FROM stdin;
\.


--
-- TOC entry 3558 (class 0 OID 16501)
-- Dependencies: 249
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, email, firstname, lastname, password) FROM stdin;
\.


--
-- TOC entry 3560 (class 0 OID 16509)
-- Dependencies: 251
-- Data for Name: watchlist; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.watchlist (watchlist_id, movie_id, profile_id, series_id) FROM stdin;
\.


--
-- TOC entry 3568 (class 0 OID 0)
-- Dependencies: 217
-- Name: account_accountid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.account_accountid_seq', 1, false);


--
-- TOC entry 3569 (class 0 OID 0)
-- Dependencies: 220
-- Name: blocked_account_blocked_accountid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.blocked_account_blocked_accountid_seq', 1, false);


--
-- TOC entry 3570 (class 0 OID 0)
-- Dependencies: 222
-- Name: episode_episode_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.episode_episode_id_seq', 1, false);


--
-- TOC entry 3571 (class 0 OID 0)
-- Dependencies: 224
-- Name: info_info_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.info_info_id_seq', 1, false);


--
-- TOC entry 3572 (class 0 OID 0)
-- Dependencies: 228
-- Name: liveinfo_liveinfo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.liveinfo_liveinfo_id_seq', 1, false);


--
-- TOC entry 3573 (class 0 OID 0)
-- Dependencies: 230
-- Name: mediapreferences_media_preference_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.mediapreferences_media_preference_id_seq', 1, false);


--
-- TOC entry 3574 (class 0 OID 0)
-- Dependencies: 232
-- Name: movie_movie_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.movie_movie_id_seq', 1, false);


--
-- TOC entry 3575 (class 0 OID 0)
-- Dependencies: 234
-- Name: preference_preference_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.preference_preference_id_seq', 1, false);


--
-- TOC entry 3576 (class 0 OID 0)
-- Dependencies: 236
-- Name: profile_profile_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.profile_profile_id_seq', 1, false);


--
-- TOC entry 3577 (class 0 OID 0)
-- Dependencies: 238
-- Name: referraldiscount_referral_discount_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.referraldiscount_referral_discount_id_seq', 1, false);


--
-- TOC entry 3578 (class 0 OID 0)
-- Dependencies: 240
-- Name: season_season_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.season_season_id_seq', 1, false);


--
-- TOC entry 3579 (class 0 OID 0)
-- Dependencies: 242
-- Name: series_series_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.series_series_id_seq', 1, false);


--
-- TOC entry 3580 (class 0 OID 0)
-- Dependencies: 244
-- Name: subscription_subscription_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.subscription_subscription_id_seq', 1, false);


--
-- TOC entry 3581 (class 0 OID 0)
-- Dependencies: 247
-- Name: subtitle_subtitle_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.subtitle_subtitle_id_seq', 1, false);


--
-- TOC entry 3582 (class 0 OID 0)
-- Dependencies: 252
-- Name: users_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_seq', 1, false);


--
-- TOC entry 3583 (class 0 OID 0)
-- Dependencies: 250
-- Name: watchlist_watchlist_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.watchlist_watchlist_id_seq', 1, false);


--
-- TOC entry 3309 (class 2606 OID 16392)
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (accountid);


--
-- TOC entry 3313 (class 2606 OID 16397)
-- Name: account_subscription account_subscription_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_subscription
    ADD CONSTRAINT account_subscription_pkey PRIMARY KEY (account_id, subscription_id);


--
-- TOC entry 3315 (class 2606 OID 16403)
-- Name: blocked_account blocked_account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.blocked_account
    ADD CONSTRAINT blocked_account_pkey PRIMARY KEY (blocked_accountid);


--
-- TOC entry 3317 (class 2606 OID 16413)
-- Name: episode episode_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.episode
    ADD CONSTRAINT episode_pkey PRIMARY KEY (episode_id);


--
-- TOC entry 3319 (class 2606 OID 16420)
-- Name: info info_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.info
    ADD CONSTRAINT info_pkey PRIMARY KEY (info_id);


--
-- TOC entry 3321 (class 2606 OID 16425)
-- Name: infomovie infomovie_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.infomovie
    ADD CONSTRAINT infomovie_pkey PRIMARY KEY (infoid, movieid);


--
-- TOC entry 3323 (class 2606 OID 16430)
-- Name: infoseries infoseries_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.infoseries
    ADD CONSTRAINT infoseries_pkey PRIMARY KEY (infoid, seriesid);


--
-- TOC entry 3325 (class 2606 OID 16436)
-- Name: liveinfo liveinfo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.liveinfo
    ADD CONSTRAINT liveinfo_pkey PRIMARY KEY (liveinfo_id);


--
-- TOC entry 3327 (class 2606 OID 16442)
-- Name: mediapreferences mediapreferences_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mediapreferences
    ADD CONSTRAINT mediapreferences_pkey PRIMARY KEY (media_preference_id);


--
-- TOC entry 3329 (class 2606 OID 16448)
-- Name: movie movie_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movie
    ADD CONSTRAINT movie_pkey PRIMARY KEY (movie_id);


--
-- TOC entry 3331 (class 2606 OID 16454)
-- Name: preference preference_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.preference
    ADD CONSTRAINT preference_pkey PRIMARY KEY (preference_id);


--
-- TOC entry 3335 (class 2606 OID 16462)
-- Name: profile profile_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile
    ADD CONSTRAINT profile_pkey PRIMARY KEY (profile_id);


--
-- TOC entry 3337 (class 2606 OID 16468)
-- Name: referraldiscount referraldiscount_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.referraldiscount
    ADD CONSTRAINT referraldiscount_pkey PRIMARY KEY (referral_discount_id);


--
-- TOC entry 3339 (class 2606 OID 16474)
-- Name: season season_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_pkey PRIMARY KEY (season_id);


--
-- TOC entry 3341 (class 2606 OID 16481)
-- Name: series series_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.series
    ADD CONSTRAINT series_pkey PRIMARY KEY (series_id);


--
-- TOC entry 3345 (class 2606 OID 16492)
-- Name: subscription_accounts subscription_accounts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subscription_accounts
    ADD CONSTRAINT subscription_accounts_pkey PRIMARY KEY (subscription_subscription_id, accounts_account_id, accounts_subscription_id);


--
-- TOC entry 3343 (class 2606 OID 16487)
-- Name: subscription subscription_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subscription
    ADD CONSTRAINT subscription_pkey PRIMARY KEY (subscription_id);


--
-- TOC entry 3349 (class 2606 OID 16500)
-- Name: subtitle subtitle_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subtitle
    ADD CONSTRAINT subtitle_pkey PRIMARY KEY (subtitle_id);


--
-- TOC entry 3347 (class 2606 OID 16519)
-- Name: subscription_accounts uk73j9t86qwapcesjkldsxdr1yk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subscription_accounts
    ADD CONSTRAINT uk73j9t86qwapcesjkldsxdr1yk UNIQUE (accounts_account_id, accounts_subscription_id);


--
-- TOC entry 3311 (class 2606 OID 16515)
-- Name: account uk8w3a2tpfnkyd0lf24iacqtbw; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT uk8w3a2tpfnkyd0lf24iacqtbw UNIQUE (referral_discount_id);


--
-- TOC entry 3333 (class 2606 OID 16517)
-- Name: preference uknorxivhshi62qjdmqn76d3q7o; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.preference
    ADD CONSTRAINT uknorxivhshi62qjdmqn76d3q7o UNIQUE (profile_id);


--
-- TOC entry 3353 (class 2606 OID 16521)
-- Name: watchlist uktlg3xry37956q1bdpvl9u2qbl; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.watchlist
    ADD CONSTRAINT uktlg3xry37956q1bdpvl9u2qbl UNIQUE (profile_id, series_id, movie_id);


--
-- TOC entry 3351 (class 2606 OID 16507)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 3355 (class 2606 OID 16513)
-- Name: watchlist watchlist_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.watchlist
    ADD CONSTRAINT watchlist_pkey PRIMARY KEY (watchlist_id);


--
-- TOC entry 3368 (class 2606 OID 16588)
-- Name: mediapreferences fk1xyveh0pefr4nuleldw6pksy4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mediapreferences
    ADD CONSTRAINT fk1xyveh0pefr4nuleldw6pksy4 FOREIGN KEY (preference_id) REFERENCES public.preference(preference_id);


--
-- TOC entry 3363 (class 2606 OID 16563)
-- Name: infoseries fk4clx2mkc7tnawdg3h986w59s2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.infoseries
    ADD CONSTRAINT fk4clx2mkc7tnawdg3h986w59s2 FOREIGN KEY (series_id) REFERENCES public.series(series_id);


--
-- TOC entry 3376 (class 2606 OID 16623)
-- Name: subtitle fk4hv1j0s5xnlneewvchkx2q0xa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subtitle
    ADD CONSTRAINT fk4hv1j0s5xnlneewvchkx2q0xa FOREIGN KEY (episode_id) REFERENCES public.episode(episode_id);


--
-- TOC entry 3361 (class 2606 OID 16553)
-- Name: infomovie fk5ycx721r4uep4krb6rr7adt3r; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.infomovie
    ADD CONSTRAINT fk5ycx721r4uep4krb6rr7adt3r FOREIGN KEY (movie_id) REFERENCES public.movie(movie_id);


--
-- TOC entry 3371 (class 2606 OID 16598)
-- Name: preference fk6c3ip1amhngfcbuclmk5rjb6a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.preference
    ADD CONSTRAINT fk6c3ip1amhngfcbuclmk5rjb6a FOREIGN KEY (profile_id) REFERENCES public.profile(profile_id);


--
-- TOC entry 3373 (class 2606 OID 16608)
-- Name: season fk6po47602227t0gdumllj7hdqp; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.season
    ADD CONSTRAINT fk6po47602227t0gdumllj7hdqp FOREIGN KEY (series_id) REFERENCES public.series(series_id);


--
-- TOC entry 3374 (class 2606 OID 16618)
-- Name: subscription_accounts fk8ca1yy1hwkcg2vf03de58qw67; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subscription_accounts
    ADD CONSTRAINT fk8ca1yy1hwkcg2vf03de58qw67 FOREIGN KEY (subscription_subscription_id) REFERENCES public.subscription(subscription_id);


--
-- TOC entry 3362 (class 2606 OID 16548)
-- Name: infomovie fk8fjmlfsnsjg0qmwivm512o7ew; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.infomovie
    ADD CONSTRAINT fk8fjmlfsnsjg0qmwivm512o7ew FOREIGN KEY (info_id) REFERENCES public.info(info_id);


--
-- TOC entry 3357 (class 2606 OID 16528)
-- Name: account_subscription fk8s2ivuu4obkrhllcr61ohh3ih; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_subscription
    ADD CONSTRAINT fk8s2ivuu4obkrhllcr61ohh3ih FOREIGN KEY (account_id) REFERENCES public.account(accountid);


--
-- TOC entry 3369 (class 2606 OID 16583)
-- Name: mediapreferences fk97sv67kesp3n9qj39g1o2a4ko; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mediapreferences
    ADD CONSTRAINT fk97sv67kesp3n9qj39g1o2a4ko FOREIGN KEY (movie_id) REFERENCES public.movie(movie_id);


--
-- TOC entry 3365 (class 2606 OID 16573)
-- Name: liveinfo fkc03jgbi8t87rbwnvlq2qt88nu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.liveinfo
    ADD CONSTRAINT fkc03jgbi8t87rbwnvlq2qt88nu FOREIGN KEY (movie_id) REFERENCES public.movie(movie_id);


--
-- TOC entry 3378 (class 2606 OID 16633)
-- Name: watchlist fkc5r8ba0137hmtayrspsr7mpf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.watchlist
    ADD CONSTRAINT fkc5r8ba0137hmtayrspsr7mpf FOREIGN KEY (movie_id) REFERENCES public.movie(movie_id);


--
-- TOC entry 3366 (class 2606 OID 16578)
-- Name: liveinfo fkcu3mvltbakublnwkjnysbeifq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.liveinfo
    ADD CONSTRAINT fkcu3mvltbakublnwkjnysbeifq FOREIGN KEY (profile_id) REFERENCES public.profile(profile_id);


--
-- TOC entry 3379 (class 2606 OID 16638)
-- Name: watchlist fkf03n8w5adddivd3i8tiei72uj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.watchlist
    ADD CONSTRAINT fkf03n8w5adddivd3i8tiei72uj FOREIGN KEY (profile_id) REFERENCES public.profile(profile_id);


--
-- TOC entry 3360 (class 2606 OID 16543)
-- Name: episode fkfdosugryyg6r3h9gqonmvo2vx; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.episode
    ADD CONSTRAINT fkfdosugryyg6r3h9gqonmvo2vx FOREIGN KEY (season_id) REFERENCES public.season(season_id);


--
-- TOC entry 3372 (class 2606 OID 16603)
-- Name: profile fkih2p8v6sj55akw9lpcngbxg05; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile
    ADD CONSTRAINT fkih2p8v6sj55akw9lpcngbxg05 FOREIGN KEY (account_id) REFERENCES public.account(accountid);


--
-- TOC entry 3380 (class 2606 OID 16643)
-- Name: watchlist fkjagpb7lea8ui07djfglq014gh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.watchlist
    ADD CONSTRAINT fkjagpb7lea8ui07djfglq014gh FOREIGN KEY (series_id) REFERENCES public.series(series_id);


--
-- TOC entry 3375 (class 2606 OID 16613)
-- Name: subscription_accounts fkjptql0ku347p29uq6o6gtdyk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subscription_accounts
    ADD CONSTRAINT fkjptql0ku347p29uq6o6gtdyk2 FOREIGN KEY (accounts_account_id, accounts_subscription_id) REFERENCES public.account_subscription(account_id, subscription_id);


--
-- TOC entry 3358 (class 2606 OID 16533)
-- Name: account_subscription fkm7lgr9nknfsrfecnyjuwf72s4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_subscription
    ADD CONSTRAINT fkm7lgr9nknfsrfecnyjuwf72s4 FOREIGN KEY (subscription_id) REFERENCES public.subscription(subscription_id);


--
-- TOC entry 3367 (class 2606 OID 16568)
-- Name: liveinfo fkrpu09jfugryeisw3h7s2fi2dt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.liveinfo
    ADD CONSTRAINT fkrpu09jfugryeisw3h7s2fi2dt FOREIGN KEY (episode_id) REFERENCES public.episode(episode_id);


--
-- TOC entry 3377 (class 2606 OID 16628)
-- Name: subtitle fkry2d51ihdc787l53o4jpsb3ut; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subtitle
    ADD CONSTRAINT fkry2d51ihdc787l53o4jpsb3ut FOREIGN KEY (movie_id) REFERENCES public.movie(movie_id);


--
-- TOC entry 3356 (class 2606 OID 16523)
-- Name: account fks7t6lq6u9p58s0amvr56tw1ka; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT fks7t6lq6u9p58s0amvr56tw1ka FOREIGN KEY (referral_discount_id) REFERENCES public.referraldiscount(referral_discount_id);


--
-- TOC entry 3370 (class 2606 OID 16593)
-- Name: mediapreferences fksp2x59kgiwb2gw6lv6fy5jsru; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mediapreferences
    ADD CONSTRAINT fksp2x59kgiwb2gw6lv6fy5jsru FOREIGN KEY (series_id) REFERENCES public.series(series_id);


--
-- TOC entry 3364 (class 2606 OID 16558)
-- Name: infoseries fktd1sgog5hd4ndiped8y1gtomp; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.infoseries
    ADD CONSTRAINT fktd1sgog5hd4ndiped8y1gtomp FOREIGN KEY (info_id) REFERENCES public.info(info_id);


--
-- TOC entry 3359 (class 2606 OID 16538)
-- Name: blocked_account fktnn0iuvlrd93escvecdccu7c5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.blocked_account
    ADD CONSTRAINT fktnn0iuvlrd93escvecdccu7c5 FOREIGN KEY (account_id) REFERENCES public.account(accountid);


-- Completed on 2024-12-25 17:40:59

--
-- PostgreSQL database dump complete
--

CREATE TABLE IF NOT EXISTS public.ROLE (
                                    id INT PRIMARY KEY,
                                    name VARCHAR(10)
);

-- insert initial default roles
INSERT INTO public.ROLE (id,name) VALUES (0,'USER'),(1,'ADMIN');