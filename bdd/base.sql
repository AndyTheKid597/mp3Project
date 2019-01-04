DROP DATABASE IF EXISTS mp3;
CREATE DATABASE mp3; 
USE mp3;
GRANT ALL PRIVILEGES ON mp3.* TO 'mp3user'@'localhost' IDENTIFIED BY 'motherofsarcasm';
CREATE TABLE `bibliotheque` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE TABLE `Config`(
`id` int unsigned NOT NULL AUTO_INCREMENT,
`cle` varchar(200) NOT NULL DEFAULT '',
`valeur` varchar(200) NOT NULL DEFAULT '',
PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE UNIQUE INDEX cle_unique
 ON Config(cle);
commit;
