CREATE ROLE admin_role;
CREATE ROLE editor_role;
CREATE ROLE viewer_role;

GRANT ALL PRIVILEGES ON DATABASE postgres TO admin_role;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO admin_role;

GRANT SELECT, INSERT, UPDATE ON ALL TABLES IN SCHEMA public TO editor_role;

GRANT SELECT ON ALL TABLES IN SCHEMA public TO viewer_role;

-- This part should be done manually because otherwise the password will be stored in plaintext in the
-- docker-entrypoint-initdb.d/01_init_users.sql file, which is not good =(.
CREATE USER admin_user WITH PASSWORD 'securepassword';
CREATE USER editor_user WITH PASSWORD 'securepassword';
CREATE USER viewer_user WITH PASSWORD 'securepassword';

GRANT admin_role TO admin_user;
GRANT editor_role TO editor_user;
GRANT viewer_role TO viewer_user;
