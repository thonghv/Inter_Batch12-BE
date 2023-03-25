CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `contract_id` int(11) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `department_id` int(11) NOT NULL,
  `dob` datetime(6) DEFAULT NULL,
  `education_id` int(11) NOT NULL,
  `first_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gender` int(11) NOT NULL,
  `last_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `position_id` int(11) NOT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci