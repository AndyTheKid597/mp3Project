DROP DATABASE IF EXISTS mp3;
CREATE DATABASE mp3; 
USE mp3;
GRANT ALL PRIVILEGES ON mp3.* TO 'mp3user'@'localhost' IDENTIFIED BY 'motherofsarcasm';
CREATE TABLE `bibliotheque` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nomfichier` varchar(200) NOT NULL DEFAULT '',
  `titre` varchar(60) DEFAULT NULL,
   `d_up` TEXT ,
`d_down` TEXT ,
`auteur` varchar(100) ,
`album` varchar(60) ,
`date` varchar(30) ,
`commentaire` varchar(120) ,
`genre` varchar(60) ,
`track` varchar(120) ,
`duration` BIGINT DEFAULT 0 ,
`image` varchar(200),
`counter` int default 0,
`pending` boolean not null default false,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE TABLE `Config`(
`id` int NOT NULL AUTO_INCREMENT,
`cle` varchar(200) NOT NULL DEFAULT '',
`valeur` varchar(200) NOT NULL DEFAULT '',
PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE UNIQUE INDEX cle_unique
 ON Config(cle);
alter table bibliotheque add column `pending` boolean not null default false;

CREATE TABLE `Client`(
`id` int NOT NULL AUTO_INCREMENT,
`nom` varchar(200) NOT NULL DEFAULT '',
`login` varchar(60) NOT NULL DEFAULT '',
`mdp` varchar(60) NOT NULL DEFAULT '',
`email` varchar(60) NOT NULL DEFAULT '',
`est_admin` boolean default false,
PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
commit;
CREATE UNIQUE INDEX login_unique
 ON Client(login);
CREATE UNIQUE INDEX email_unique
 ON Client(email);



INSERT INTO CLIENT(nom,login,mdp,email) VALUES('Rakototest','admin','admin','Attention@gmail.com');

CREATE TABLE `playlist`(
`id` int NOT NULL AUTO_INCREMENT,
`idclient` int,
`nom` varchar(60),
PRIMARY KEY(`id`)
);
CREATE UNIQUE INDEX np_unique
 ON playlist(nom);
CREATE TABLE `details_pl`(
`id` int NOT NULL AUTO_INCREMENT,
`idchanson` int NOT NULL ,
`idplaylist` int ,
    FOREIGN KEY (idplaylist)
        REFERENCES playlist (id)
        ON DELETE CASCADE,
    FOREIGN KEY (idchanson)
        REFERENCES bibliotheque (id)
        ON DELETE CASCADE,
PRIMARY KEY(`id`)
);
select count(*) as col_0_0_ from bibliotheque chanson0_ where chanson0_.nomfichier like '%under again%' or chanson0_.titre like '%under again%' or chanson0_.commentaire like '%under again%' or chanson0_.genre like '%under again%' or chanson0_.auteur like '%under again%' or chanson0_.album like '%under again%' or chanson0_.date like '%under again%'