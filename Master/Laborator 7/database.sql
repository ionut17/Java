CREATE DATABASE laborator4 with owner = postgres;

CREATE TABLE IF NOT EXISTS students (
    id int PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS projects (
    id int PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS skills (
    id int PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS student_skills (
    student int references students(id),
    skill int REFERENCES skills(id),
    PRIMARY KEY (student, skill)
);

CREATE TABLE IF NOT EXISTS student_preferences (
    student int references students(id),
    project int REFERENCES projects(id),
    PRIMARY KEY (student, project)
);

CREATE TABLE IF NOT EXISTS project_skills (
    project int references projects(id),
    skill int REFERENCES skills(id),
    PRIMARY KEY (project, skill)
);

INSERT INTO skills(id, name) VALUES(1, 'Programming');
INSERT INTO skills(id, name) VALUES(2, 'Design');
INSERT INTO skills(id, name) VALUES(3, 'Architecture');
INSERT INTO skills(id, name) VALUES(4, 'Problem Solving');

INSERT INTO students(id, name) VALUES(1, 'Iacob Ionut');
INSERT INTO students(id, name) VALUES(2, 'Adascalitei Anca');
INSERT INTO students(id, name) VALUES(3, 'Gordin Stefan');

INSERT INTO student_skills(student, skill) VALUES(1, 1);
INSERT INTO student_skills(student, skill) VALUES(1, 2);
INSERT INTO student_skills(student, skill) VALUES(1, 3);
INSERT INTO student_skills(student, skill) VALUES(1, 4);

INSERT INTO student_skills(student, skill) VALUES(2, 1);
INSERT INTO student_skills(student, skill) VALUES(2, 3);
INSERT INTO student_skills(student, skill) VALUES(2, 4);

INSERT INTO student_skills(student, skill) VALUES(3, 1);

INSERT INTO student_preferences(student, project) VALUES(1, 1);
INSERT INTO student_preferences(student, project) VALUES(1, 2);

INSERT INTO student_preferences(student, project) VALUES(2, 1)
INSERT INTO student_preferences(student, project) VALUES(2, 3);

INSERT INTO student_preferences(student, project) VALUES(3, 1);

INSERT INTO projects(id, name) VALUES(1, 'TAIP');
INSERT INTO projects(id, name) VALUES(2, 'Java EE');

INSERT INTO project_skills(project, skill) VALUES(1, 1);
INSERT INTO project_skills(project, skill) VALUES(2, 1);


