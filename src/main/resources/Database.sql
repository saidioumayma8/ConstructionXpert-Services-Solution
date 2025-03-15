CREATE DATABASE Construction;
USE Construction;
CREATE TABLE user (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      nom VARCHAR(100) NOT NULL,
                      email VARCHAR(150) UNIQUE NOT NULL,
                      motdepasse VARCHAR(255) NOT NULL
);
CREATE TABLE Projet (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        nom VARCHAR(255) NOT NULL,
                        description TEXT,
                        date_debut DATE NOT NULL,
                        date_fin DATE NOT NULL,
                        budget DOUBLE NOT NULL
);
CREATE TABLE Tache (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       description TEXT NOT NULL,
                       date_debut DATE NOT NULL,
                       date_fin DATE NOT NULL,
                       id_projet INT NOT NULL,
                       FOREIGN KEY (id_projet) REFERENCES Projet(id) ON DELETE CASCADE
);
CREATE TABLE Ressource (
                           id INT PRIMARY KEY AUTO_INCREMENT,
                           nom VARCHAR(255) NOT NULL,
                           type VARCHAR(100) NOT NULL,
                           quantite INT NOT NULL,
                           fournisseur VARCHAR(255),
                           id_tache INT NOT NULL,
                           FOREIGN KEY (id_tache) REFERENCES Tache(id) ON DELETE CASCADE
);
INSERT INTO user (nom, email, motdepasse)
VALUES ('Admin', 'admin@gmail.com', 'admin123');




