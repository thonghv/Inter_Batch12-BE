CREATE TABLE `files` (
  `id` varchar(36) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `data` mediumblob,
  `name` varchar(255)  DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `user_id` varchar(36)  DEFAULT NULL,
  PRIMARY KEY (`id`)
)
