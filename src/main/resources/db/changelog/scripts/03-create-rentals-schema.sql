CREATE TABLE IF NOT EXISTS `rentals` (
                           `actual_return_date` datetime(6) DEFAULT NULL,
                           `car_id` bigint DEFAULT NULL,
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `rental_date` datetime(6) DEFAULT NULL,
                           `return_date` datetime(6) DEFAULT NULL,
                           `user_id` bigint DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `FKb3vpbdnk78p1epicm7a7urvfh` (`car_id`),
                           KEY `FKtnhd1objf2mlb6ag6k726u269` (`user_id`),
                           CONSTRAINT `FKb3vpbdnk78p1epicm7a7urvfh` FOREIGN KEY (`car_id`) REFERENCES `cars` (`id`),
                           CONSTRAINT `FKtnhd1objf2mlb6ag6k726u269` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);
