-- ===========================
-- KEYSPACE
-- ===========================
CREATE KEYSPACE IF NOT EXISTS keyspaceboottech
    WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 }
    AND DURABLE_WRITES = true;

USE keyspaceboottech;


-- ===========================
-- TABLE: author
-- ===========================
CREATE TABLE IF NOT EXISTS author (
    id uuid PRIMARY KEY,
    lastname text,
    firstname text
);

CREATE INDEX IF NOT EXISTS author_lastname_idx
    ON author (lastname);

-- ===========================
-- TABLE: book
-- ===========================
CREATE TABLE IF NOT EXISTS book (
    id uuid PRIMARY KEY,
    title text,
    isbn text,
    description text,
    language text,
    publication_date date,
    pages int,
    price double
);


CREATE INDEX IF NOT EXISTS book_isbn_idx
    ON book (isbn);

CREATE INDEX IF NOT EXISTS book_title_idx
    ON book (title);
