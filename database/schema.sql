CREATE DATABASE hors_jeu;
\c hors_jeu;

CREATE TABLE equipe(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100)
);

CREATE TABLE match(
    id SERIAL PRIMARY KEY,
    daty DATE
);

CREATE TABLE score(
    id_equipe INTEGER,
    points INTEGER, 
    id_match INTEGER,
    FOREIGN KEY(id_equipe) REFERENCES equipe(id),
    FOREIGN KEY(id_match) REFERENCES match(id)
);

INSERT INTO equipe (id,nom) VALUES (1,'Rouge');
INSERT INTO equipe (id,nom) VALUES (2,'Bleu');

INSERT INTO match (id,daty) VALUES (1,'2025-01-17');

/* Requête */
SELECT id_equipe , SUM(points) as score FROM score WHERE id_match = 1 GROUP BY id_equipe having id_equipe = 1;

DELETE FROM score;