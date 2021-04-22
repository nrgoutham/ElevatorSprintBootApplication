CREATE SCHEMA IF NOT EXISTS elevator_schema;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp" SCHEMA elevator_schema;
ALTER ROLE elevator SET search_path to elevator_schema;
