CREATE ROLE senior_role;
CREATE ROLE medior_role;
CREATE ROLE junior_role;

ALTER ROLE senior_role LOGIN;
ALTER ROLE medior_role LOGIN;
ALTER ROLE junior_role LOGIN;


--- Senior
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO senior_role;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO senior_role;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO senior_role;

--- Medior
REVOKE ALL ON ALL TABLES IN SCHEMA public FROM medior_role;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO medior_role;

REVOKE ALL PRIVILEGES ON public.subscription FROM medior_role;
REVOKE ALL PRIVILEGES ON public.subscription_accounts FROM medior_role;
REVOKE ALL PRIVILEGES ON public.referraldiscount FROM medior_role;


--- Junior
REVOKE ALL ON ALL TABLES IN SCHEMA public FROM junior_role;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO junior_role;

REVOKE ALL PRIVILEGES ON public.subscription FROM junior_role;
REVOKE ALL PRIVILEGES ON public.subscription_accounts FROM junior_role;
REVOKE ALL PRIVILEGES ON public.referraldiscount FROM junior_role;

CREATE VIEW public.account_junior AS
SELECT accountid, payment_method
FROM public.account;
GRANT SELECT ON public.account_junior TO junior_role;

REVOKE ALL PRIVILEGES ON public.account FROM junior_role;

--- Assigned roles
CREATE USER senior_user WITH PASSWORD 'securepassword';
CREATE USER medior_user WITH PASSWORD 'securepassword';
CREATE USER junior_user WITH PASSWORD 'securepassword';

GRANT senior_role TO senior_user;
GRANT medior_role TO medior_user;
GRANT junior_role TO junior_user;
