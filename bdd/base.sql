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