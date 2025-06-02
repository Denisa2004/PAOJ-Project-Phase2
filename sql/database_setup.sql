-- Creare tabele

CREATE TABLE autor (
                       id SERIAL PRIMARY KEY,
                       nume VARCHAR(100),
                       email VARCHAR(100),
                       telefon VARCHAR(20),
                       nationalitate VARCHAR(50),
                       an_nastere INT,
                       numar_carti INT
);

CREATE TABLE editura (
                         id SERIAL PRIMARY KEY,
                         nume VARCHAR(100),
                         oras VARCHAR(50),
                         an_infiintare INT
);

CREATE TABLE sectiune (
                          id SERIAL PRIMARY KEY,
                          nume VARCHAR(100),
                          locatie VARCHAR(50),
                          capacitate INT
);

CREATE TABLE carte (
                       id SERIAL PRIMARY KEY,
                       titlu VARCHAR(100),
                       autor_id INT REFERENCES autor(id) ON DELETE CASCADE,
                       gen VARCHAR(50),
                       an INT,
                       sectiune_id INT REFERENCES sectiune(id) ON DELETE SET NULL,
                       editura_id INT REFERENCES editura(id) ON DELETE SET NULL,
                       stoc INT
);

-- Inserare date

INSERT INTO autor (id, nume, email, telefon, nationalitate, an_nastere, numar_carti)
VALUES
(1, 'Ion Creanga', 'ion@email.com', '0722123456', 'Romana', 1837, 5),
(2, 'Mihai Eminescu', 'eminescu@email.com', '0722555666', 'Romana', 1850, 10),
(3, 'J.K. Rowling', 'jk@email.com', '0755333444', 'Britanica', 1965, 7);

INSERT INTO editura (id, nume, oras, an_infiintare)
VALUES
(1, 'Polirom', 'Iasi', 1991),
(2, 'Humanitas', 'Bucuresti', 1990),
(3, 'Bloomsbury', 'Londra', 1986),
(4, 'Tralala', 'Timisoara', 2014);

INSERT INTO sectiune (id, nume, locatie, capacitate)
VALUES
(1, 'Literatura', 'Etaj 1', 300),
(2, 'Fictiune', 'Etaj 2', 150),
(3, 'Poezie', 'Etaj 3', 100);

INSERT INTO carte (id, titlu, autor_id, gen, an, sectiune_id, editura_id, stoc)
VALUES
(1, 'Povestea lui Harap-Alb', 1, 'Fabula', 1877, 1, 1, 5),
(2, 'Luceafarul', 2, 'Poezie', 1883, 3, 2, 2),
(3, 'Harry Potter', 3, 'Fictiune', 1997, 2, 3, 10),
(4, 'Scrisoarea a IIIa', 2, 'Poezie', 1881, 3, 2, 3),
(5, 'Amintiri din copilarie', 1, 'Autobiografie', 1880, 1, 1, 1);
