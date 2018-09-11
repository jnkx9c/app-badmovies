CREATE DATABASE IF NOT EXISTS movies;

use movies;

CREATE TABLE `authorities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_q0u5f2cdlshec8tlh6818bhbk` (`authority`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `movie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `actors` varchar(2048) DEFAULT NULL,
  `awards` varchar(2048) DEFAULT NULL,
  `box_office` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `director` varchar(2048) DEFAULT NULL,
  `dvd` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `imdbid` varchar(255) DEFAULT NULL,
  `imdb_rating` varchar(255) DEFAULT NULL,
  `imdb_votes` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `metascore` varchar(255) DEFAULT NULL,
  `plot` longtext,
  `poster` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `production` varchar(255) DEFAULT NULL,
  `rated` varchar(255) DEFAULT NULL,
  `released` varchar(255) DEFAULT NULL,
  `response` varchar(255) DEFAULT NULL,
  `runtime` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `writer` varchar(2048) DEFAULT NULL,
  `year_released` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_55soyoh6n540uund67fhjqn2b` (`imdbid`)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8;




CREATE TABLE `rating` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `source` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `movie_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlqsvmdlh3ep1boo7in23xe86y` (`movie_id`),
  CONSTRAINT `FKlqsvmdlh3ep1boo7in23xe86y` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `zipcode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `users_authorities` (
  `user_id` int(11) NOT NULL,
  `authorities_id` int(11) NOT NULL,
  KEY `FKmfxncv8ke1jjgna64c8kclry5` (`authorities_id`),
  KEY `FKq3lq694rr66e6kpo2h84ad92q` (`user_id`),
  CONSTRAINT `FKmfxncv8ke1jjgna64c8kclry5` FOREIGN KEY (`authorities_id`) REFERENCES `authorities` (`id`),
  CONSTRAINT `FKq3lq694rr66e6kpo2h84ad92q` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_date` datetime DEFAULT NULL,
  `total_order_price` float NOT NULL,
  `total_order_quantity` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`),
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE `orderitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` float NOT NULL,
  `quantity` int(11) NOT NULL,
  `movie_id` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsbpsalu8kt5qu591kk01ucyt0` (`movie_id`),
  KEY `FKhcggihiup2358o98a7uuxqoxb` (`order_id`),
  CONSTRAINT `FKhcggihiup2358o98a7uuxqoxb` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FKsbpsalu8kt5qu591kk01ucyt0` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
