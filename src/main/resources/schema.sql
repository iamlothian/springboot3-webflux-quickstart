
DROP TABLE IF EXISTS notes;
CREATE TABLE IF NOT EXISTS notes (
    id                  BIGSERIAL          NOT NULL PRIMARY KEY
    ,version            INT             NOT NULL DEFAULT 0
    ,note               VARCHAR(255)    NOT NULL
    ,created_at         TIMESTAMPTZ     NOT NULL
    ,last_updated_at    TIMESTAMPTZ     NOT NULL
);

INSERT INTO notes (note, created_at, last_updated_at) VALUES
('This is a note', '2020-01-01 00:00:00+00', '2020-01-01 00:00:00+00');