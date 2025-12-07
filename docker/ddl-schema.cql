-- Create a keyspace
CREATE KEYSPACE IF NOT EXISTS keyspaceboottech
    WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 }
    AND DURABLE_WRITES = true;

USE keyspaceboottech;


-- Create a tables

CREATE TABLE IF NOT EXISTS author(
                       id uuid PRIMARY KEY,
                       lastname text,
                       firstname text
);

CREATE TABLE IF NOT EXISTS book(
                     id uuid PRIMARY KEY,
                     title text,
                     isbn text,
                     description text,
                     language text,
                     publication date,
                     page int,
                     price double
);
