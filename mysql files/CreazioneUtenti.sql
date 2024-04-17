DROP DATABASE IF EXISTS torneiscuola;
CREATE DATABASE torneiscuola;

USE torneiscuola;

CREATE TABLE utenti(
    email_utente VARCHAR(100) PRIMARY KEY NOT NULL,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    password VARCHAR(300) NOT NULL,
    data_creazione DATETIME NOT NULL
);

CREATE TABLE temp_utenti(

    email_utente VARCHAR(100) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    password VARCHAR(300) NOT NULL,
    otp VARCHAR(300) NOT NULL,
    expire_at DATETIME NOT NULL,
    PRIMARY KEY(email_utente,otp)

);

CREATE TABLE account(
    email_utente VARCHAR(100) PRIMARY KEY NOT NULL,
    password VARCHAR(300) NOT NULL,
    data_creazione DATETIME NOT NULL,

    FOREIGN KEY(email_utente) REFERENCES utenti(email_utente)
        ON DELETE CASCADE
);
