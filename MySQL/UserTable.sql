CREATE TABLE `users` (
  `net_id` varchar(200), /* ADD IN UNIQUE ONCE FINISHED... "DEFAULT NULL UNIQUE" */
  `first_name` varchar(200) DEFAULT NULL,
  `last_name` varchar(200) DEFAULT NULL,
  `phone_number` varchar(200) DEFAULT NULL,
  `gender` varchar(200) DEFAULT NULL,
  `major` varchar(200) DEFAULT NULL,
  `user_password` varchar(200) DEFAULT NULL,
  `classification` varchar(200) DEFAULT NULL,
  `age` int(10) DEFAULT NULL,
  `code` int(10) DEFAULT NULL,
  `entered_code` int(10) DEFAULT NULL,
  `verified` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`net_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
