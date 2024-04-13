DROP DATABASE IF EXISTS torneiscuola;
CREATE DATABASE torneiscuola;

USE torneiscuola;

CREATE TABLE utenti(
    email_utente VARCHAR(100) PRIMARY KEY NOT NULL,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL
);

CREATE TABLE account(
    email_utente VARCHAR(100) PRIMARY KEY NOT NULL,
    password VARCHAR(300) NOT NULL,
    data_creazione DATETIME NOT NULL,

    FOREIGN KEY(email_utente) REFERENCES utenti(email_utente)
        ON DELETE CASCADE
);
