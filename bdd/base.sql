DROP DATABASE IF EXISTS mp3;
CREATE DATABASE mp3; 
USE mp3;
GRANT ALL PRIVILEGES ON mp3.* TO 'mp3user'@'localhost' IDENTIFIED BY 'motherofsarcasm';
CREATE TABLE `bibliotheque` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nomfichier` varchar(200) NOT NULL DEFAULT '',
  `titre` varchar(60) DEFAULT NULL,
   `d_up` varchar(200) ,
`d_down` varchar(200) ,
`auteur` varchar(100) ,
`album` varchar(60) ,
`date` varchar(30) ,
`commentaire` varchar(120) ,
`genre` varchar(60) ,
`track` varchar(120) ,
`duration` BIGINT DEFAULT 0 ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
commit;
