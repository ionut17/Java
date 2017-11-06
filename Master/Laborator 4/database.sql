CREATE DATABASE laborator4 with owner = postgres;

CREATE TABLE IF NOT EXISTS students (
    id int PRIMARY KEY,
    name VARCHAR(25)
);

INSERT INTO students(id, name) VALUES(1, 'Iacob Ionut');
INSERT INTO students(id, name) VALUES(2, 'Adascalitei Anca');
INSERT INTO students(id, name) VALUES(3, 'Gordin Stefan');
