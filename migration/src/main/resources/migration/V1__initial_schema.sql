------------------------------------------------------------
--Tables
------------------------------------------------------------

CREATE TABLE elevator
(
    identifier    UUID PRIMARY KEY,
    name          varchar(100) NOT NULL,
    current_Floor int          NOT NULL,
    total_Floors  int[] NOT NULL,
    state         VARCHAR(50)  NOT NULL
);

CREATE TABLE building
(
    identifier   UUID PRIMARY KEY,
    name         varchar(100) NOT NULL,
    location     varchar(100) NOT NULL,
    elevator_Ids UUID[] NOT NULL
);

CREATE TABLE users
(
    identifier   UUID PRIMARY KEY,
    name         varchar(100) NOT NULL,
    building_Ids UUID[] NOT NULL
);