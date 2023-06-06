CREATE TABLE IF NOT EXISTS `payments` (
                            `amount` decimal(38,2) DEFAULT NULL,
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `rental_id` bigint DEFAULT NULL,
                            `session_id` varchar(255) DEFAULT NULL,
                            `session_url` longtext DEFAULT NULL,
                            `status` varchar(255) DEFAULT NULL,
                            `type` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `UK_6b9oj6np97hxx2k68lir605ml` (`rental_id`),
                            CONSTRAINT `FK55tlwg2o3718m5fjunw4omev7` FOREIGN KEY (`rental_id`) REFERENCES `rentals` (`id`)
);
