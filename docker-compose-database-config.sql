\connect ez_db;
create SCHEMA IF NOT EXISTS ez_schema;
GRANT ALL PRIVILEGES ON SCHEMA ez_schema TO GROUP ez_user;
create SCHEMA IF NOT EXISTS ez_schema_test;
GRANT ALL PRIVILEGES ON SCHEMA ez_schema_test TO GROUP ez_user;