CREATE TABLE `users` (
  `id` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL unique,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL unique,
  PRIMARY KEY (`id`)
  );

CREATE TABLE `tokens` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `expired` BIT(1) NOT NULL,
    `revoked` BIT(1) NOT NULL,
    `token` VARCHAR(255) DEFAULT NULL UNIQUE,
    `user_id` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
)

