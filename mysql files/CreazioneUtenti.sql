DROP DATABASE IF EXISTS playsphere;
CREATE DATABASE playsphere;

USE playsphere;

CREATE TABLE utenti(
    email_utente VARCHAR(100) PRIMARY KEY NOT NULL,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    password VARCHAR(300) NOT NULL,
    data_nascita DATETIME NOT NULL,
    sesso char(1) NOT NULL,
    data_creazione DATETIME NOT NULL
);

CREATE TABLE temp_utenti(
    email_utente VARCHAR(100) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    password VARCHAR(300) NOT NULL,
    data_nascita DATETIME NOT NULL,
    sesso char(1) NOT NULL,
    otp VARCHAR(300) NOT NULL,
    expire_at DATETIME NOT NULL,
    PRIMARY KEY(email_utente,otp)
);

CREATE TABLE sports(
    id_sport PRIMARY KEY INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(30) NOT NULL
);

INSERT INTO sports VALUES
    (null, "Calcio"),
    (null, "Basket"),
    (null, "Pallavolo"),
    (null, "Golf"),
    (null, "Tennis"),
    (null, "Corsa"),
    (null, "Fresbee"),
    (null, "Hockey"),
    (null, "Scherma"),
    (null, "Rugby"),
    (null, "Padel"),
    (null, "Baseball"),
    (null, "Dodgeball");

CREATE TABLE tornei(
    nome_torneo VARCHAR(100) NOT NULL,
    data_torneo DATETIME NOT NULL,
    descrizione TEXT,
    eta_minima SMALLINT DEFAULT 5,
    min_partecipanti SMALLINT DEFAULT 0,
    max_partecipanti SMALLINT,
    is_interno BOOLEAN NOT NULL,
    email_organizzatore VARCHAR(100) NOT NULL,
    sport VARCHAR(30),
    PRIMARY KEY(nome_torneo,data_torneo),
    FOREIGN KEY (email_organizzatore) REFERENCES utenti(email_utente) ON DELETE SET NULL,
    FOREIGN KEY (sport) REFERENCES sport(nome) ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE partecipazioni (
    email_partecipante VARCHAR(100) NOT NULL,
    nome_torneo VARCHAR(100) NOT NULL,
    data_torneo DATETIME NOT NULL,
    PRIMARY KEY(email_partecipante, nome_torneo, data_torneo),
    FOREIGN KEY (email_partecipante) REFERENCES utenti(email_utente) ON DELETE CASCADE,
    FOREIGN KEY (nome_torneo, data_torneo) REFERENCES tornei(nome_torneo, data_torneo) ON UPDATE CASCADE ON DELETE CASCADE
)
